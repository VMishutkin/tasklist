package mish.vlad.tasklist.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    private Set<Role> roles;
    private List<Task> tasks;
}
