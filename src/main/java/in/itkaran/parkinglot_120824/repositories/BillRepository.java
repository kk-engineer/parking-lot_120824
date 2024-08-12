package in.itkaran.parkinglot_120824.repositories;



import in.itkaran.parkinglot_120824.models.Bill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BillRepository {
    private Map<Long, Bill> billMap = new HashMap<>();
    private Long previousBillId = 0L;

    public Bill save(Bill bill) {
        if (bill.getId() == null) {
            previousBillId += 1;
            bill.setId(previousBillId);
        }
        billMap.put(bill.getId(), bill);
        return bill;
    }
}
