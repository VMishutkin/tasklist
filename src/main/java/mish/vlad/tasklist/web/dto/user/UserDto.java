package mish.vlad.tasklist.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.web.dto.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Set;

public class UserDto {
    @NotNull(message = "id must be not null.", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnUpdate.class})
    @Length(max=255, message = "Name length must be small than 255 symbols", groups = {OnUpdate.class, OnUpdate.class})
    private String name;
    @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnUpdate.class})
    @Length(max=255, message = "Username length must be small than 255 symbols", groups = {OnUpdate.class, OnUpdate.class})
    private String username;
    @NotNull(message = "Password must be not null.", groups = {OnUpdate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Password confirmation must be not null.", groups = {OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;
}
