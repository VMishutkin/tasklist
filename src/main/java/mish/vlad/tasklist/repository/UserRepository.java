package mish.vlad.tasklist.repository;

import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserRepository {
    Optional<User> findById(Long Id);
    Optional<User> findByUsername(String username);

    void update(User user);
    void create(User user);
    void insertUserRole(@Param("userid") Long userId, @Param("role") Role role);
    boolean isTaskOwner(@Param("userid") Long userId, @Param("taskid") Long taskId);

    void delete(Long id);
}
