package mish.vlad.tasklist.repository;

import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository {
    Optional<User> findById(Long Id);
    Optional<User> findByUsername(String username);

    void update(User user);
    void create(User user);
    void insertUserRole(Long userId, Role role);
    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long id);
}
