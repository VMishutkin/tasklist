package mish.vlad.tasklist.web.mapper;

import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "creationDate", source = "creationDate")
    List<TaskDto> toDto(List<Task> task);


    Task toEntity(TaskDto dto);

    TaskDto toDto(Task createdTask);
}
