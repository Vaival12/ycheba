package Inc.TechSolutions.dao.Payment;

import Inc.TechSolutions.Entity.Payment;

import java.util.List;

public interface PaymentService {
    void addPayment(Payment payment);
    void deletePayment(long id);
    Payment getPaymentById(long id);

    void update(long id);

    List<Payment> readAllPayments();
}
