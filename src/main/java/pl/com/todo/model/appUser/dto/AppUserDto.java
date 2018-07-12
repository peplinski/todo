package pl.com.todo.model.appUser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.todo.model.appUser.AppUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String address;

    public static AppUserDto createDto(AppUser user){
        return new AppUserDto(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getAddress());
    }
}
