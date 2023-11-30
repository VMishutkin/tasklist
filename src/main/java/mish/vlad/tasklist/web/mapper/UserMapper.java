package mish.vlad.tasklist.web.mapper;

import mish.vlad.tasklist.model.user.User;
import mish.vlad.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
