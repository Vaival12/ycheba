package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    // Project
    @PostMapping("/Project")
    public void addProject(Project project) {
        projectService.addProject(project);
    }

    @GetMapping("/Project/{id}")
    public Project getProjectById(long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/Project/{id}")
    public void deleteProject(long id) {
        projectService.deleteProject(id);
    }

    @GetMapping("/Project")
    public List<Project> readAllProject(){
        return projectService.readAllProjects();
    }

    @PutMapping("/Project/{id}")
    public void updateProject(Long id) {
        projectService.update(id);
    }

}