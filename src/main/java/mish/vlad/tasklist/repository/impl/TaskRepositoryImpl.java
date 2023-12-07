package mish.vlad.tasklist.repository.impl;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.exception.ResourceMappingException;
import mish.vlad.tasklist.model.task.Task;
import mish.vlad.tasklist.repository.DataSourceConfig;
import mish.vlad.tasklist.repository.TaskRepository;
import mish.vlad.tasklist.repository.mappers.TaskRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            Select  t.id as task_id,
            t.title as task_title,
            t.description as task_description,
            t.expiration_date as task_expiration_date,
            t.status as task_status
            from tasks t
            where id = ?""";
    private final String FIND_ALL_BY_ID = """
            Select  t.id as task_id,
                    t.title as task_title,
                    t.description as task_description,
                    t.expiration_date as task_expiration_date,
                    t.status as task_status
            from tasks t
            JOIN users_tasks ut on t.id = ut.task_id
            where ut.user_id = ?
            """;
    private final String ASSIGN = """
            INSERT INTO users_tasks (task_id, user_id)
            VALUES (?, ?)""";
    private final String UPDATE = """
            UPDATE tasks
            set title = ?
            description = ?
            expiration_date = ?
            status = ?
            WHERE id = ?""";
    private final String CREATE = """
            INSERT INTO tasks (title, description, expiration_date, status)
            VALUES (?,?,?,?)""";

    private final String DELETE = """
            DELETE from tasks
            where id = ?""";

    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding user");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding taskss by userid");
        }
    }

    @Override
    public void assignToUsesrById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_ID);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while assigning to user");
        }
    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }
            statement.setString(4, task.getStatus().name());
            statement.setLong(5, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while updating by userid");
        }
    }

    /*
        private final String CREATE = """
                INSERT INTO tasks (title, description, expiration_date, status)
                VALUES (?,?,?,?)""";
     */
    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                task.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while creating task");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while deleting task");
        }
    }
}
