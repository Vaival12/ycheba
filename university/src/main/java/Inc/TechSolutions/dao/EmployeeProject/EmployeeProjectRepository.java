package Inc.TechSolutions.dao.EmployeeProject;

import Inc.TechSolutions.Entity.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject,Long> {
    EmployeeProject getEmployeeProjectById(long id);
}
