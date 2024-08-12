package in.itkaran.parkinglot_120824.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment extends BaseModel {
    private int amount;
    private PaymentMode mode;
    private PaymentStatus status;
    private String transactionId;
    private Date paymentTime;
}
