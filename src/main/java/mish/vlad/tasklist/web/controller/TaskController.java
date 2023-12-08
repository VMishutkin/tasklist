package mish.vlad.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.service.TaskService;
import mish.vlad.tasklist.web.dto.task.TaskDto;
import mish.vlad.tasklist.web.dto.validation.OnUpdate;
import mish.vlad.tasklist.web.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor

public class TaskController {
    @Autowired
    private final TaskService taskService;
    @Autowired
    private final TaskMapper taskMapper;
    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id){
        Task task = taskService.getById(id);

        return taskMapper.toDto(task);
    }
    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto){
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }

}
