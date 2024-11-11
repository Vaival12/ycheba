package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    // Employee
    @PostMapping("/Employee")
    public void addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
    }

    @GetMapping("/Employee/{id}")
    public Employee getEmployeeById(long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/Employee/{id}")
    public void deleteEmployee(long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/Employee")
    public List<Employee> readAllEmployee(){
        return employeeService.readAllEmployees();
    }

    @PutMapping("/Employee/{id}")
    public void updateEmployee(Long id) {
        employeeService.update(id);
    }

}