package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Project.ProjectRepository;
import Inc.TechSolutions.dao.Project.ProjectService;
import Inc.TechSolutions.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ProjectServiceImpl implements ProjectService {
    Scanner scanner = new Scanner(System.in);
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(long id) {
        return projectRepository.getProjectById(id);
    }

    @Override
    public List<Project> readAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void update(Long id) {
        Project project = projectRepository.getProjectById(id);
        projectRepository.save(project);
    }

}
