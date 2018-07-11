package pl.com.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserDto {
    private Long id;
    private String email;
    private String password;


}
