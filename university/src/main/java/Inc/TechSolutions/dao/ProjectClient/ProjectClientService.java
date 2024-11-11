package Inc.TechSolutions.dao.ProjectClient;

import Inc.TechSolutions.Entity.ProjectClient;

import java.util.List;

public interface ProjectClientService {
    void addProjectClient(ProjectClient projectClient);
    void deleteProjectClient(long id);
    ProjectClient getProjectClientById(long id);

    void update(long id);

    List<ProjectClient> readAllProjectClients();
}
