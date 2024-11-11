package Inc.TechSolutions.Control;

import Inc.TechSolutions.ApplicationServices.*;
import Inc.TechSolutions.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    // Payment
    @PostMapping("/Payment")
    public void addPayment(Payment payment) {
        paymentService.addPayment(payment);
    }

    @GetMapping("/Payment/{id}")
    public Payment getPaymentById(long id) {
        return paymentService.getPaymentById(id);
    }

    @DeleteMapping("/Payment/{id}")
    public void deletePayment(long id) {
        paymentService.deletePayment(id);
    }

    @GetMapping("/Payment")
    public List<Payment> readAllPayment(){
        return paymentService.readAllPayments();
    }

    @PutMapping("/Payment/{id}")
    public void updatePayment(Long id) {
        paymentService.update(id);
    }

}