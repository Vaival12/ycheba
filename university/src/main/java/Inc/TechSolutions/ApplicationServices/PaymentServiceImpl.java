package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.dao.Payment.PaymentRepository;
import Inc.TechSolutions.dao.Payment.PaymentService;
import Inc.TechSolutions.Entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public void addPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentRepository.getPaymentById(id);
    }

    @Override
    public List<Payment> readAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void update(long id) {
        Payment payment = paymentRepository.getPaymentById(id);
        paymentRepository.save(payment);
    }

}
