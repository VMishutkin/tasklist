package mish.vlad.tasklist.service;

import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.web.dto.task.TaskDto;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<? extends TaskDto> getAllByUserId(Long id);

    Task update(TaskDto task);

    Task create(Long userId, Task task);

    void delete(Long id);

    void changeStatus(Long taskId);
}
