package Inc.TechSolutions.dao.EmployeeProject;

import Inc.TechSolutions.Entity.EmployeeProject;

import java.util.List;

public interface EmployeeProjectService {
    void addEmployeeProject(EmployeeProject employeeProject);
    void deleteEmployeeProject(long id);
    EmployeeProject getEmployeeProjectById(long id);

    void update(Long id);

    List<EmployeeProject> readAllEmployeeProjects();
}
