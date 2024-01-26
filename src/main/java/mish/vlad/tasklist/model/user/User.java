package mish.vlad.tasklist.model.user;

import jakarta.persistence.*;
import lombok.Data;
import mish.vlad.tasklist.model.task.Task;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;
    @OneToMany(mappedBy= "user")
    private List<Task> tasks;


}