package Inc.TechSolutions.dao.ProjectClient;

import Inc.TechSolutions.Entity.ProjectClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectClientRepository extends JpaRepository<ProjectClient,Long> {
    ProjectClient getProjectClientById(long id);
}
