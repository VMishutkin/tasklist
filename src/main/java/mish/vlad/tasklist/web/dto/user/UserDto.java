package mish.vlad.tasklist.web.dto.user;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mish.vlad.tasklist.web.dto.validation.OnCreate;
import mish.vlad.tasklist.web.dto.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "User DTO")
public class UserDto {

    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    @Schema(description = "User id", example = "1")
    private Long id;

    @NotNull(message = "Name must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    @Schema(description = "User name", example = "John Doe")
    @Length(max = 255,
            message = "Name length must be smaller than 255 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Username must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255,
            message = "Username length must be smaller than 255 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null.",
            groups = {OnCreate.class})
    private String passwordConfirmation;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                '}';
    }
}
