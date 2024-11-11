package Inc.TechSolutions.dao.Project;

import Inc.TechSolutions.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project getProjectById(long id);
}
