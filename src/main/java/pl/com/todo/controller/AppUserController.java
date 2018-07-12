package pl.com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.todo.model.appUser.AppUser;
import pl.com.todo.model.appUser.dto.AppUserDto;
import pl.com.todo.model.appUser.dto.DeleteUserDto;
import pl.com.todo.model.appUser.dto.EditUserDto;
import pl.com.todo.model.appUser.dto.RegisterUserDto;
import pl.com.todo.service.AppUserService;

import java.util.List;
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

        if (editedAppUser.isPresent()) {
            return ResponseEntity.ok(editedAppUser.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<AppUserDto>> listUsers() {
        return ResponseEntity.ok(appUserService.getUserList());
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<AppUser> deleteUser( @RequestBody DeleteUserDto dto) {
        Optional<AppUser> deleteAppUser = appUserService.deleteUser(dto.getId(), dto);
        if (deleteAppUser.isPresent()) {
            return ResponseEntity.ok(deleteAppUser.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
