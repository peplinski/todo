package pl.com.todo.model.appTodoTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTodoTaskDto {
    private long id;

    private String title;
    private String description;
    private LocalDateTime time;
    private boolean done;
}
