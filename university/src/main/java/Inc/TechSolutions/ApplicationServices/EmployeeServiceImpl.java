package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Employee.EmployeeRepository;
import Inc.TechSolutions.dao.Employee.EmployeeService;
import Inc.TechSolutions.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<Employee> readAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void update(Long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        employeeRepository.save(employee);
    }

}
