package Inc.TechSolutions.dao.Address;

import Inc.TechSolutions.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address getAddressById(long id);
}
