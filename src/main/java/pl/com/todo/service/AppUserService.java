package pl.com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.com.todo.model.AppUser;
import pl.com.todo.model.dto.EditUserDto;
import pl.com.todo.model.dto.RegisterUserDto;
import pl.com.todo.repozytory.AppUserRepozytory;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepozytory appUserRepozytory;

    /**
     * Metoda rejestracji użytkownika - przyjmuje dto, a wewnątrz zamienia go na obiekt
     * usera. Z danych w dto stworzony został użytkownik.
     * @param registerUserDto - dto z danymi z rejestracji
     */
    public void registerUser(RegisterUserDto registerUserDto) {
        // Tworzymy usera
        AppUser createdUser = new AppUser(
                registerUserDto.getRegister_email(),
                registerUserDto.getRegister_password());

        // wykorzystujemy repository żeby zapisać usera w bazie
        appUserRepozytory.save(createdUser);
    }


    public Optional<AppUser> editUser(Long id, EditUserDto dto) {
        Optional<AppUser>searchedUser = appUserRepozytory.findById(id);
        if (searchedUser.isPresent()){
            AppUser user = searchedUser.get();

            user.setAddress(dto.getEdited_address());
            user.setName(dto.getEdited_name());
            if (dto.getEdited_surname() != null){

                user.setSurname(dto.getEdited_surname());
            }

            user = appUserRepozytory.save(user);
        }
        //zwracam zmodyfikowany wpis
        return Optional.empty();
    }
}
