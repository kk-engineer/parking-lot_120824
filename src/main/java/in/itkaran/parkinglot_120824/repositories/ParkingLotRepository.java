package in.itkaran.parkinglot_120824.repositories;


import in.itkaran.parkinglot_120824.models.ParkingLot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();
    private Long previousParkingLotId = 0L;

    public ParkingLot findParkingLotById(Long previousParkingLotId) {
        return parkingLotMap.get(previousParkingLotId);
    }

    public ParkingLot save(ParkingLot parkingLot) {
        if (parkingLot.getId() == null) {
            previousParkingLotId += 1;
            parkingLot.setId(previousParkingLotId);
        }
        parkingLotMap.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }
}
