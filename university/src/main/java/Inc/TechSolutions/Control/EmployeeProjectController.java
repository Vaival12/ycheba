package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emppro")
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectServiceImpl employeeProjectService;

    // EmployeeProject
    @PostMapping("/EmployeeProject")
    public void addEmployeeProject(EmployeeProject project) {
        employeeProjectService.addEmployeeProject(project);
    }

    @GetMapping("/EmployeeProject/{id}")
    public EmployeeProject getEmployeeProjectById(long id) {
        return employeeProjectService.getEmployeeProjectById(id);
    }

    @DeleteMapping("/EmployeeProject/{id}")
    public void deleteEmployeeProject(long id) {
        employeeProjectService.deleteEmployeeProject(id);
    }

    @GetMapping("/EmployeeProject")
    public List<EmployeeProject> readAllEmployeeProject(){
        return employeeProjectService.readAllEmployeeProjects();
    }

    @PutMapping("/EmployeeProject/{id}")
    public void updateEmployeeProject(Long id) {
        employeeProjectService.update(id);
    }

}