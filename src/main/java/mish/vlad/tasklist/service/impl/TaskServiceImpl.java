package mish.vlad.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.exception.ResourceNotFoundException;
import mish.vlad.tasklist.model.task.Status;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.repository.TaskRepository;
import mish.vlad.tasklist.repository.UserRepository;
import mish.vlad.tasklist.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {

        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long id) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.update(task);
        return task;

    }

    @Override
    @Transactional
    public Task create(Long userId, Task task) {
        task.setStatus(Status.TODO);
        taskRepository.create(task);
        taskRepository.assignToUsesrById(task.getId(), userId);
        return task;
    }


    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }
}
