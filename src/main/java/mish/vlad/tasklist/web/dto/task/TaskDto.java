package mish.vlad.tasklist.web.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import mish.vlad.tasklist.model.task.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Getter
public class TaskDto {
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
}
