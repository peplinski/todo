package pl.com.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {

    private String edited_name;
    private String edited_surname;
    private String edited_address;
}
