package mish.vlad.tasklist.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mish.vlad.tasklist.model.task.TaskWithUserDto;

import java.util.List;

public class TaskCustomRepoImpl implements TaskCustomRepo{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TaskWithUserDto> findTaskWithUser() {
        List<TaskWithUserDto> results=em.createNamedQuery("findTaskWithUser").getResultList();
        return results;
    }
}
