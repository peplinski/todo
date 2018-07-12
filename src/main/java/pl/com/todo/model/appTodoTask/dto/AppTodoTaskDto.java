package pl.com.todo.model.appTodoTask.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.todo.model.appTodoTask.TodoTask;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppTodoTaskDto {

    private long id;

    private String title;
    private String description;
    private LocalDateTime addTime;
    private boolean done;

    public static AppTodoTaskDto createTask(TodoTask task) {
        return new AppTodoTaskDto(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTime(),
                task.isDone());
    }


}
