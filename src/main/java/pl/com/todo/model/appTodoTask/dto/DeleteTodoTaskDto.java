package pl.com.todo.model.appTodoTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTodoTaskDto {
    private Long id;
    private String title;
    private String description;
}
