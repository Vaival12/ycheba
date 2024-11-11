package Inc.TechSolutions.dao.Department;

import Inc.TechSolutions.Entity.Department;

import java.util.List;

public interface DepartmentService {
    void addDepartment(Department department);
    void deleteDepartment(long id);
    Department getDepartmentById(long id);
    void update(long id);

    List<Department> readAllDepartments();
}
