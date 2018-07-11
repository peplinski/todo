package pl.com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.todo.model.AppUser;
import pl.com.todo.model.dto.AppUserDto;
import pl.com.todo.model.dto.DeleteUserDto;
import pl.com.todo.model.dto.EditUserDto;
import pl.com.todo.model.dto.RegisterUserDto;
import pl.com.todo.repozytory.AppUserRepozytory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService {


    @Autowired
    private AppUserRepozytory appUserRepozytory;

    /**
     * Metoda rejestracji użytkownika - przyjmuje dto, a wewnątrz zamienia go na obiekt
     * usera. Z danych w dto stworzony został użytkownik.
     *
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
        Optional<AppUser> searchedUser = appUserRepozytory.findById(id);
        if (searchedUser.isPresent()) {
            AppUser user = searchedUser.get();

            if (dto.getEdited_address() != null) {

                user.setAddress(dto.getEdited_address());
            }
            if (dto.getEdited_name() != null) {

                user.setName(dto.getEdited_name());
            }
            if (dto.getEdited_surname() != null) {

                user.setSurname(dto.getEdited_surname());
            }

            user = appUserRepozytory.save(user);
            return Optional.of(user);
        }
        //zwracam zmodyfikowany wpis
        return Optional.empty();
    }

    public List<AppUserDto> getUserList() {
        List<AppUser> list = appUserRepozytory.findAll();
        return list.stream()
                .map(user -> AppUserDto.createDto(user))
                .collect(Collectors.toList());
    }


    public Optional<AppUser> deleteUser(Long id, DeleteUserDto dto) {
        Optional<AppUser> searchedUser = appUserRepozytory.findById(id);
if (id != null){
    if (searchedUser.isPresent()){
        AppUser user = searchedUser.get();

        if(user.getEmail().equals(dto.getEmail()) &&
                user.getPassword().equals(dto.getPassword())){
            appUserRepozytory.delete(user);
            return Optional.of(user);
        }
    }

}
        return Optional.empty();
    }


}
