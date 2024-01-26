package mish.vlad.tasklist.repository;

import mish.vlad.tasklist.model.task.TaskWithUserDto;

import java.util.List;

public interface TaskCustomRepo {
    List<TaskWithUserDto> findTaskWithUser();

}