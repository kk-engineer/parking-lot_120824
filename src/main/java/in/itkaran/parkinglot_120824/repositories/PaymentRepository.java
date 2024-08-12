package in.itkaran.parkinglot_120824.repositories;


import in.itkaran.parkinglot_120824.models.Payment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PaymentRepository {
    private Map<Long, Payment> paymentMap = new HashMap<>();
    private Long previousPaymentId = 0L;

    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            previousPaymentId += 1;
            payment.setId(previousPaymentId);
        }
        paymentMap.put(payment.getId(), payment);
        return payment;
    }

    public Payment findPaymentById(Long paymentId) {
        return paymentMap.get(paymentId);
    }
}
