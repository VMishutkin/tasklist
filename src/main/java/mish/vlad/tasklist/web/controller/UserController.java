package mish.vlad.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.model.user.User;
import mish.vlad.tasklist.service.TaskService;
import mish.vlad.tasklist.service.UserService;
import mish.vlad.tasklist.web.dto.task.TaskDto;
import mish.vlad.tasklist.web.dto.user.UserDto;
import mish.vlad.tasklist.web.dto.validation.OnCreate;
import mish.vlad.tasklist.web.dto.validation.OnUpdate;
import mish.vlad.tasklist.web.mapper.TaskMapper;
import mish.vlad.tasklist.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }
    @GetMapping({"/{id}"})
    public UserDto getById(@PathVariable Long id){
        User user = userService.getById(id);
        UserDto userDto = userMapper.toDto(user);
        return userDto;
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.delete(id);
    }
    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id){
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }
    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class)  TaskDto dto){
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create( id, task);
        return taskMapper.toDto(createdTask);
    }


}
