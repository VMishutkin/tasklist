package mish.vlad.tasklist.model.task;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mish.vlad.tasklist.model.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
@Data
@Setter
@Getter
@NamedNativeQuery(name = "findTaskWithUser", query = "SELECT t.*, u.username FROM tasks t\n" +
        "  JOIN users u ON u.id = t.user_id", resultSetMapping = "TaskWithUserDtoMapping")
@SqlResultSetMapping(name = "TaskWithUserDtoMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "id", type = long.class),
                                @ColumnResult(name = "title"),
                                @ColumnResult(name = "description"),
                                @ColumnResult(name = "status", type = Status.class),
                                @ColumnResult(name = "creation_date", type = LocalDateTime.class),
                                @ColumnResult(name = "modified_time", type = LocalDateTime.class),
                                @ColumnResult(name = "username")
                        },
                        targetClass = TaskWithUserDto.class
                )}
)
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}