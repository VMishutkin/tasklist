package mish.vlad.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.exception.ResourceNotFoundException;
import mish.vlad.tasklist.model.task.Status;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.repository.TaskRepository;
import mish.vlad.tasklist.service.TaskService;
import mish.vlad.tasklist.service.UserService;
import mish.vlad.tasklist.web.dto.task.TaskDto;
import mish.vlad.tasklist.web.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {

        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<? extends TaskDto> getAllByUserId(Long id) {

        boolean isAdmin = userService.isUserAdmin(id);
        if (isAdmin){
            return taskRepository.findTaskWithUser();
        }else {
            List<Task> tasks = taskRepository.findAllByUserId(id);
            return taskMapper.toDto(tasks);
        }

    }

    @Override
    public void changeStatus(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if(task.getStatus().equals(Status.TODO) || task.getStatus().equals(Status.IN_PROGRESS) ) {
            task.setStatus(Status.DONE);
        }else {
            task.setStatus(Status.IN_PROGRESS);
        }
        task.setModifiedTime(LocalDateTime.now());
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(TaskDto taskDto) {

        Task task = updateFromDto(taskDto);
        taskRepository.save(task);
        return task;

    }
    private Task updateFromDto(TaskDto dto) {
        Task task = taskRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setTitle(dto.getTitle()) ;
        task.setDescription(dto.getDescription());
        task.setModifiedTime(LocalDateTime.now());
        return task;
    }

    @Override
    @Transactional
    public Task create(Long userId, Task task) {
        task.setStatus(Status.TODO);
        task.setCreationDate(LocalDateTime.now());
        task.setUser(userService.getById(userId));
        taskRepository.save(task);
        //taskRepository.assignTask(userId, task.getId());
        return task;
    }


    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }


}
