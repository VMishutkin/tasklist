package mish.vlad.tasklist.model.task;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import mish.vlad.tasklist.web.dto.task.TaskDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskWithUserDto extends TaskDto {
    // @NotNull(message = "id must be not null.", groups = OnUpdate.class)
    private Long id;
    //@NotNull(message = "Title must be not null.", groups = {OnUpdate.class, OnCreate.class})
    //@Length(max=255, message = "Title length must be small than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String title;
    //@Length(max=255, message = "Description length must be small than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String description;
    private Status status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedTime;
    private String Username;
}
