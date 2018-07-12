package pl.com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.todo.model.appTodoTask.TodoTask;
import pl.com.todo.model.appTodoTask.dto.EditTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.AppTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.RegisterTodoTaskDto;
import pl.com.todo.service.AppTodoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class AppTodoController {


    private AppTodoService appTodoService;

    @Autowired
    public AppTodoController(AppTodoService appTodoService) {
        this.appTodoService = appTodoService;
    }

    @PostMapping("/registerTask")
    public void registerTodoTask(@RequestBody RegisterTodoTaskDto registerTodoTaskDto) {
        appTodoService.registerTodoTask(registerTodoTaskDto);
    }

    @PostMapping("/editTask/{id}")
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

    @GetMapping("/listDailyTask")
    public ResponseEntity<List<AppTodoTaskDto>> listDayTask(LocalDate time) {
        return ResponseEntity.ok(appTodoService.GetDailyTask(time));
    }

    @PostMapping("/deleteTask/{id}")
    public ResponseEntity<TodoTask> deleteTask(@PathVariable(name = "id") Long id) {
        Optional<TodoTask> deleteTodoTask = appTodoService.deleteTask(id);

        if (deleteTodoTask.isPresent()) {
            return ResponseEntity.ok(deleteTodoTask.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/done/{id}/{status}")
    public ResponseEntity<TodoTask> isDone(@PathVariable(name = "id") Long id,
                                           @PathVariable Boolean status) {
        Optional<TodoTask> done = appTodoService.isDone(id, status);
        if (done.isPresent()) {
            return ResponseEntity.ok(done.get());
        }
        return ResponseEntity.badRequest().build();
    }


}
