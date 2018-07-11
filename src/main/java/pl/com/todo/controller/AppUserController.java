package pl.com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.todo.model.AppUser;
import pl.com.todo.model.dto.EditUserDto;
import pl.com.todo.model.dto.RegisterUserDto;
import pl.com.todo.service.AppUserService;

import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterUserDto registerUserDto) {
        appUserService.registerUser(registerUserDto);
    }

    @PostMapping("/editUser/{id}")
    public ResponseEntity<AppUser> editUser(@PathVariable(name = "id") Long id, @RequestBody EditUserDto dto) {
        Optional<AppUser> editedAppUser = appUserService.editUser(id, dto);

        if (editedAppUser.isPresent()){
            return ResponseEntity.ok(editedAppUser.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
