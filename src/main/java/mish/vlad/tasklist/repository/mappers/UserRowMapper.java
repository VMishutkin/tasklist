package mish.vlad.tasklist.repository.mappers;

import lombok.SneakyThrows;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.model.user.User;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRowMapper {
    @SneakyThrows
    public static User mapRow(ResultSet resultSet){
        Set<Role> roles = new HashSet<>();
        while(resultSet.next()) {
            roles.add(Role.valueOf(resultSet.getString("user_role")));
        }
        resultSet.beforeFirst();
        List<Task> tasks = TaskRowMapper.mapRows(resultSet);
        resultSet.beforeFirst();
        if(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setUsername(resultSet.getString("user_username"));
            user.setPassword(resultSet.getString("user_password"));
            user.setTasks(tasks);
            user.setRoles(roles);
            return user;
        }
        return null;
    }
}
