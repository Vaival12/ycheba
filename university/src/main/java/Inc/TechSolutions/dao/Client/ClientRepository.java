package Inc.TechSolutions.dao.Client;

import Inc.TechSolutions.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client getClientById(long id);
}
