package pl.com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.todo.model.appTodoTask.TodoTask;
import pl.com.todo.model.appTodoTask.dto.DeleteTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.EditTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.AppTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.RegisterTodoTaskDto;
import pl.com.todo.service.AppTodoService;

import java.util.List;
import java.util.Optional;

@RestController
public class AppTodoController {

    @Autowired
    private AppTodoService appTodoService;

    @PostMapping("/register")
    public void registerTodoTask(@RequestBody RegisterTodoTaskDto RegisterTodoTaskDto) {
        appTodoService.registerTodoTask(RegisterTodoTaskDto);
    }

    @PostMapping("/editTask")
    public ResponseEntity<TodoTask> editTask(@PathVariable(name = "id") Long id, @RequestBody EditTodoTaskDto dto) {
        Optional<TodoTask> editedTodoTask = appTodoService.editTask(id, dto);

        if (editedTodoTask.isPresent()) {
            return ResponseEntity.ok(editedTodoTask.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/listTask")
    public ResponseEntity<List<AppTodoTaskDto>> listTask() {
        return ResponseEntity.ok(appTodoService.getTodoTask());
    }

    @GetMapping("/deleteTask")
    public ResponseEntity<TodoTask> deleteTask(@RequestBody DeleteTodoTaskDto dto) {
        Optional<TodoTask> deleteTodoTask = appTodoService.deleteTask(dto.getId(), dto);

        if (deleteTodoTask.isPresent()) {
            return ResponseEntity.ok(deleteTodoTask.get());
        }
        return ResponseEntity.badRequest().build();
    }


}
