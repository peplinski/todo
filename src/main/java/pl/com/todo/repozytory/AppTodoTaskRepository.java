package pl.com.todo.repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.todo.model.appTodoTask.TodoTask;

@Repository
public interface AppTodoTaskRepository extends JpaRepository<TodoTask,Long> {

}
