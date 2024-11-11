package Inc.TechSolutions.dao.Project;

import Inc.TechSolutions.Entity.Project;

import java.util.List;

public interface ProjectService {
    void addProject(Project project);
    void deleteProject(long id);
    Project getProjectById(long id);
    void update(Long id);

    List<Project> readAllProjects();


}
