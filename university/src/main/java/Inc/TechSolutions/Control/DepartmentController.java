package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dep")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    // Department
    @PostMapping("/Departments")
    public void addDepartment(Department department) {
        departmentService.addDepartment(department);
    }

    @GetMapping("/Departments/{id}")
    public Department getDepartmentById(long id) {
        return departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/Departments/{id}")
    public void deleteDepartment(long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/Departments")
    public List<Department> readAllDepartment(){
        return departmentService.readAllDepartments();
    }

    @PutMapping("/Departments/{id}")
    public void updateDepartment(Long id) {
        departmentService.update(id);
    }

}