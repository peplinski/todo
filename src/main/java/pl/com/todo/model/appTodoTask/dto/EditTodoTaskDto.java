package pl.com.todo.model.appTodoTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTodoTaskDto {
    private String edited_title;
    private String edited_description;
}
