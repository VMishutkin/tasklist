package mish.vlad.tasklist.repository;

import mish.vlad.tasklist.model.task.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {
    Optional<Task> findById(Long id);
    List<Task> findAllByUserId(Long userId);

    void assignToUsesrById(@Param("taskid") Long taskId, @Param("userid") Long userId);

    void update(Task task);
    void create(Task task);

    void delete(Long id);

}
