package mish.vlad.tasklist.service;

import mish.vlad.tasklist.model.user.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    User update(User user);
    User create(User user);
    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long id);

    boolean isUserAdmin(Long id);


    List<String> getAllUsernames();
}
