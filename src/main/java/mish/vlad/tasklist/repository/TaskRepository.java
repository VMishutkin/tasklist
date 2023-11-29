package mish.vlad.tasklist.repository;

import mish.vlad.tasklist.model.task.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TaskRepository {
    Optional<Task> findById(Long id);
    List<Task> findAllByUserId(Long userId);

    void assignToUsesrById(Long taskId, Long userId);

    void update(Task task);
    void create(Task task);

    void delete(Long id);

}
