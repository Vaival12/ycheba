package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.EmployeeProject.EmployeeProjectRepository;
import Inc.TechSolutions.dao.EmployeeProject.EmployeeProjectService;
import Inc.TechSolutions.Entity.EmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {
    Scanner scanner = new Scanner(System.in);
    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Override
    public void addEmployeeProject(EmployeeProject employeeProject) {
        employeeProjectRepository.save(employeeProject);
    }

    @Override
    public void deleteEmployeeProject(long id) {
        employeeProjectRepository.deleteById(id);
    }

    @Override
    public EmployeeProject getEmployeeProjectById(long id) {
        return employeeProjectRepository.getEmployeeProjectById(id);
    }

    @Override
    public List<EmployeeProject> readAllEmployeeProjects() {
        return employeeProjectRepository.findAll();
    }

    @Override
    public void update(Long id) {
        EmployeeProject employeeProject = employeeProjectRepository.getEmployeeProjectById(id);
        employeeProjectRepository.save(employeeProject);
    }

}
