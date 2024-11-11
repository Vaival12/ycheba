package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procln")
public class ProjectClientController {

    @Autowired
    private ProjectClientServiceImpl projectClientService;

    // Project
    @PostMapping("/ProjectClient")
    public void addProjectClient(ProjectClient projectClient) {
        projectClientService.addProjectClient(projectClient);
    }

    @GetMapping("/ProjectClient/{id}")
    public ProjectClient getProjectClientById(long id) {
        return projectClientService.getProjectClientById(id);
    }

    @DeleteMapping("/ProjectClient/{id}")
    public void deleteProjectClient(long id) {
        projectClientService.deleteProjectClient(id);
    }

    @GetMapping("/ProjectClient")
    public List<ProjectClient> readAllProjectClient(){
        return projectClientService.readAllProjectClients();
    }

    @PutMapping("/ProjectClient/{id}")
    public void updateProjectClient(Long id) {
        projectClientService.update(id);
    }

}