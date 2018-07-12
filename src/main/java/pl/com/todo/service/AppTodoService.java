package pl.com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.todo.model.appTodoTask.TodoTask;
import pl.com.todo.model.appTodoTask.dto.DeleteTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.EditTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.AppTodoTaskDto;
import pl.com.todo.model.appTodoTask.dto.RegisterTodoTaskDto;
import pl.com.todo.repozytory.AppTodoTaskRepository;

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

    public Optional<TodoTask> deleteTask(Long id, DeleteTodoTaskDto dto) {
        Optional<TodoTask> searchedTask = appTodoTaskRepository.findById(id);
        if (id != null) {
            TodoTask task = searchedTask.get();
            if (searchedTask.isPresent()) {
                if (task.getTitle().equals(dto.getTitle()) &&
                        task.getDescription().equals(dto.getDescription())) {
                }
            }
             appTodoTaskRepository.delete(task);
            return Optional.of(task);
        }
        return Optional.empty();
    }
}
