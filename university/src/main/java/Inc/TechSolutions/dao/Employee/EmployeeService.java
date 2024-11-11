package Inc.TechSolutions.dao.Employee;

import Inc.TechSolutions.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    void deleteEmployee(long id);
    Employee getEmployeeById(long id);

    void update(Long id);

    List<Employee> readAllEmployees();
}
