package Inc.TechSolutions.dao.Department;

import Inc.TechSolutions.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department getDepartmentById(long id);

}
