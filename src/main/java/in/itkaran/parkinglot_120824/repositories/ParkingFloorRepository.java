package in.itkaran.parkinglot_120824.repositories;


import in.itkaran.parkinglot_120824.models.ParkingFloor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ParkingFloorRepository {
    private Map<Long, ParkingFloor> parkingFloorMap = new HashMap<>();
    private Long previousParkingFloorId = 0L;

    public ParkingFloor save(ParkingFloor parkingFloor) {
        if (parkingFloor.getId() == null) {
            previousParkingFloorId += 1;
            parkingFloor.setId(previousParkingFloorId);
        }
        parkingFloorMap.put(parkingFloor.getId(), parkingFloor);
        return parkingFloor;
    }

    public ParkingFloor findParkingFloorById(Long parkingFloorId) {

        return parkingFloorMap.get(parkingFloorId);
    }
}
