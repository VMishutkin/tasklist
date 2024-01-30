package mish.vlad.tasklist.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.service.TaskService;
import mish.vlad.tasklist.web.dto.task.TaskDto;
import mish.vlad.tasklist.web.mapper.TaskMapper;
import mish.vlad.tasklist.web.security.JwtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name= "Task controller", description = "Task API")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class TaskController {

    @Autowired
    private final TaskService taskService;
    @Autowired
    private final TaskMapper taskMapper;
    @GetMapping("/{id}")
    //@PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public Pair<TaskDto, Boolean> getById(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity jwt =(JwtEntity)(authentication.getPrincipal());
        Boolean isAdminRequested = jwt.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
        Task task = taskService.getById(id);
        TaskDto dto = taskMapper.toDto(task);
        //return dto;
         return  Pair.of(dto, isAdminRequested);
    }
    @PutMapping("/")
    //@PreAuthorize("@customSecurityExpression.canAccessTask(#dto.id)")
    public TaskDto update(@RequestBody TaskDto dto){

        Task updatedTask = taskService.update(dto);
        return taskMapper.toDto(updatedTask);
    }

    @PutMapping("/{id}/status")
    //@PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public void changeStatus(@PathVariable Long id, @RequestBody String status){
        taskService.changeStatus(id);

    }



    @DeleteMapping("/{id}")
    //@PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    public void deleteById(@PathVariable Long id){
        taskService.delete(id);
    }

}
