package pl.com.todo.model.appTodoTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTodoTaskDto {
    private String register_title;
    private String register_description;
}
