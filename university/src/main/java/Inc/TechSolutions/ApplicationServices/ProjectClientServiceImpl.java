package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.ProjectClient.ProjectClientRepository;
import Inc.TechSolutions.dao.ProjectClient.ProjectClientService;
import Inc.TechSolutions.Entity.ProjectClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectClientServiceImpl implements ProjectClientService {
    @Autowired
    ProjectClientRepository projectClientRepository;
    @Override
    public void addProjectClient(ProjectClient projectClient) {
        projectClientRepository.save(projectClient);
    }

    @Override
    public void deleteProjectClient(long id) {
        projectClientRepository.deleteById(id);
    }

    @Override
    public ProjectClient getProjectClientById(long id) {
        return projectClientRepository.getProjectClientById(id);
    }

    @Override
    public List<ProjectClient> readAllProjectClients() {
        return projectClientRepository.findAll();
    }

    @Override
    public void update(long id) {
        ProjectClient projectClient = projectClientRepository.getProjectClientById(id);
        projectClientRepository.save(projectClient);
    }

}
