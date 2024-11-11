package Inc.TechSolutions.dao.Task;

import Inc.TechSolutions.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task getTaskById(long id);
}
