package mish.vlad.tasklist.service.impl;

import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.repository.TaskRepository;
import mish.vlad.tasklist.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllByUserId(Long id) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Long id, Task task) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }
}
