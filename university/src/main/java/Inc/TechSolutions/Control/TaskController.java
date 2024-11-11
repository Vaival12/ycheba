package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tsk")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    // Task
    @PostMapping("/Task")
    public void addTask(Task task) {
        taskService.addTask(task);
    }

    @GetMapping("/Task/{id}")
    public Task getTaskById(long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/Task/{id}")
    public void deleteTask(long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/Task")
    public List<Task> readAllTask(){
        return taskService.readAllTasks();
    }

    @PutMapping("/Task/{id}")
    public void updateTask(Long id) {
        taskService.update(id);
    }

}