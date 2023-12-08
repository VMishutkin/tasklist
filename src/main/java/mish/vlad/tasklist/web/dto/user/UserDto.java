package mish.vlad.tasklist.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import mish.vlad.tasklist.web.dto.validation.OnCreate;
import mish.vlad.tasklist.web.dto.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;

public class UserDto {
    @NotNull(message = "id must be not null.", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max=255, message = "Name length must be small than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String name;
    @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max=255, message = "Username length must be small than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    private String username;
    @NotNull(message = "Password must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Password confirmation must be not null.", groups = {OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;
}
