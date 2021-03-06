package pl.com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.todo.model.appTodoTask.TodoTask;
import pl.com.todo.model.appTodoTask.dto.DeleteTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.EditTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.AppTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.RegisterTodoTaskDto;
import pl.com.todo.repozytory.AppTodoTaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppTodoService {

    @Autowired
    private AppTodoTaskRepository appTodoTaskRepository;


    public void registerTodoTask(RegisterTodoTaskDto RegisterTodoTaskDto) {
        TodoTask createTodoTask = new TodoTask(
                RegisterTodoTaskDto.getRegister_title(),
                RegisterTodoTaskDto.getRegister_description());

        appTodoTaskRepository.save(createTodoTask);
    }

    public Optional<TodoTask> editTask(Long id, EditTodoTaskDto dto) {
        Optional<TodoTask> editedTask = appTodoTaskRepository.findById(id);
        if (editedTask.isPresent()) {
            TodoTask task = editedTask.get();

            if (dto.getEdited_title() != null) {
                task.setTitle(dto.getEdited_title());
            }
            if (dto.getEdited_description() != null) {
                task.setDescription(dto.getEdited_description());
            }
            task = appTodoTaskRepository.save(task);
            return Optional.of(task);
        }
        return Optional.empty();
    }

    public List<AppTodoTaskDto> getTodoTask() {
        List<TodoTask> taskList = appTodoTaskRepository.findAll();
        return taskList.stream().map(task -> AppTodoTaskDto.createTask(task)).collect(Collectors.toList());
    }

    public List<AppTodoTaskDto> GetDailyTask(LocalDate time) {
        List<TodoTask> taskList = appTodoTaskRepository.findAll();
        return taskList.stream().filter(task ->
        {
            return task.getTime().toLocalDate().equals(time);
        }).map(task -> AppTodoTaskDto.createTask(task)).collect(Collectors.toList());
    }

    public Optional<TodoTask> deleteTask(Long id) {
        Optional<TodoTask> searchedTask = appTodoTaskRepository.findById(id);
        if (id != null) {
            TodoTask task = searchedTask.get();
            if (searchedTask.isPresent()) {
                appTodoTaskRepository.delete(task);
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }

    //oznaczone że zrobione zadanie
    public Optional<TodoTask> isDone(Long id, Boolean status) {
        Optional<TodoTask> searchedTask = appTodoTaskRepository.findById(id);
        if (id != null) {
            if (searchedTask.isPresent()) {
                TodoTask task = searchedTask.get();
                task.setDone(status);

                appTodoTaskRepository.save(task);
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }
}