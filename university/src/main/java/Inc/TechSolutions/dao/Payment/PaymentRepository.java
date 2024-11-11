package Inc.TechSolutions.dao.Payment;

import Inc.TechSolutions.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment getPaymentById(long id);
}
