package pl.com.todo.model.appTodoTask;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.com.todo.model.appUser.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    private LocalDateTime addTime;
    private boolean done;

    public TodoTask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

}
