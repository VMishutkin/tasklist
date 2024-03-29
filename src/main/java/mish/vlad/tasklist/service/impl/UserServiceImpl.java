package mish.vlad.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import mish.vlad.tasklist.model.exception.ResourceNotFoundException;
import mish.vlad.tasklist.model.user.Role;
import mish.vlad.tasklist.model.user.User;
import mish.vlad.tasklist.repository.UserRepository;
import mish.vlad.tasklist.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return
                userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        //User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return
                userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User create(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("User already exist");
        }
        if(!user.getPassword().equals(user.getPasswordConfirmation())){
            throw new IllegalStateException("Password and password confirmation do not match");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskOwner(Long userId, Long taskId) {
        return userRepository.isTaskOwner(userId, taskId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
    userRepository.deleteById(id);
    }

    @Override
    public boolean isUserAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        boolean isAdmin = user.getRoles().contains(Role.ROLE_ADMIN);
        return isAdmin;
    }

    @Override
    public List<String> getAllUsernames() {
        List<User> users = userRepository.findAll();
        List<String> usernames = users.stream().map(User::getUsername).toList();
        return usernames;
    }
}
