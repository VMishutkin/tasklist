package mish.vlad.tasklist.repository;

import mish.vlad.tasklist.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskCustomRepo{


    @Query(value = """
            SELECT * FROM tasks t
            WHERE user_id = :userId
            """, nativeQuery = true)
    List<Task> findAllByUserId(@Param("userId") Long userId);


/*    @Modifying
    @Query(value = """
            INSERT INTO users_tasks (user_id, task_id)
            VALUES (:userId, :taskId)
            """, nativeQuery = true)
    void assignTask(@Param("userId") Long userId, @Param("taskId") Long taskId);*/


}