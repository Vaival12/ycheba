package Inc.TechSolutions.dao.Task;

import Inc.TechSolutions.Entity.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task);
    void deleteTask(long id);
    Task getTaskById(long id);
    void update(long id);

    List<Task> readAllTasks();
}
