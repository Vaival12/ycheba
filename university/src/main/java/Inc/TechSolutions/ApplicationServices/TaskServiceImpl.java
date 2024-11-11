package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Task.TaskRepository;
import Inc.TechSolutions.dao.Task.TaskService;
import Inc.TechSolutions.Entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public List<Task> readAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void update(long id) {
        Task task = taskRepository.getTaskById(id);
        taskRepository.save(task);
    }

}
