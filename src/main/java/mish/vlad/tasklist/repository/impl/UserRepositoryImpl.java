package mish.vlad.tasklist.repository.impl;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.exception.ResourceMappingException;
import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.model.user.User;
import mish.vlad.tasklist.repository.DataSourceConfig;
import mish.vlad.tasklist.repository.UserRepository;
import mish.vlad.tasklist.repository.mappers.TaskRowMapper;
import mish.vlad.tasklist.repository.mappers.UserRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DataSourceConfig dataSourceConfig;
    private final String FIND_BY_ID = """
            Select  u.id as user_id,
                    u."name" as user_name,
                    u.username as user_username,
                    u."password" as user_password,
                    ur.role as user_role,
                    t.id as task_id,
                    t.title as task_title,
                    t.description as task_description,
                    t.expiration_date as task_expiration_date,
                    t.status as task_status
            FROM users u
            LEFT JOIN users_roles ur on u.id = ur.user_id
            LEFT JOIN users_tasks ut on u.id = ut.user_id
            LEFT JOIN tasks t on ut.task_id = t.id
            where u.id = ?""";
    private final String FIND_BY_USERNAME = """
            Select  u.id as user_id,
                    u."name" as user_name,
                    u.username as user_username,
                    u."password" as user_password,
                    ur.role as user_role,
                    t.id as task_id,
                    t.title as task_title,
                    t.description as task_description,
                    t.expiration_date as task_expiration_date,
                    t.status as task_status
            FROM users u
            LEFT JOIN users_roles ur on u.id = ur.user_id
            LEFT JOIN users_tasks ut on u.id = ut.user_id
            LEFT JOIN tasks t on ut.task_id = t.id
            where u.user_name = ?""";
    private final String UPDATE = """
            UPDATE users
            set name = ?,
                username = ?,
                password = ?
            WHERE id = ?""";
    private final String CREATE = """
            INSERT INTO users (name, username, password)
            VALUES (?,?,?)""";

    private final String DELETE = """
            DELETE from users
            where id = ?""";
    private final String INSERT_USER_ROLE = """
            INSERT INTO users_roles(user_id, role)
            VALUES (?, ?)""";

    private final String IS_TASK_OWNER = """
            SELECT exists(
            SELECT 1
            FROM users_tasks
            WHERE user_id = ?
            AND task_id = ?
            )""";

    @Override
    public Optional<User> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding user");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
