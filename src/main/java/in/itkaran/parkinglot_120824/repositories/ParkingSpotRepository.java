package in.itkaran.parkinglot_120824.repositories;


import in.itkaran.parkinglot_120824.models.ParkingSpot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ParkingSpotRepository {
    private Map<Long, ParkingSpot> parkingSpotMap = new HashMap<>();
    private Map<String, ParkingSpot> parkingNumberMap = new HashMap<>();

    private Long previousParkingSpotId = 0L;

    public ParkingSpot save(ParkingSpot parkingSpot) {
        if (parkingSpot.getId() == null) {
            previousParkingSpotId += 1;
            parkingSpot.setId(previousParkingSpotId);
        }
        parkingSpotMap.put(parkingSpot.getId(), parkingSpot);
        parkingNumberMap.put(parkingSpot.getSpotNumber(), parkingSpot);
        return parkingSpot;
    }

    public ParkingSpot delete(ParkingSpot parkingSpot) {
        parkingSpotMap.remove(parkingSpot.getId());
        parkingNumberMap.remove(parkingSpot.getSpotNumber());
        return parkingSpot;
    }

    public Optional<ParkingSpot> findParkingSpotById(Long parkingSpotId) {
        if (parkingSpotMap.containsKey(parkingSpotId)) {
            return Optional.of(parkingSpotMap.get(parkingSpotId));
        }
        return Optional.empty();
    }

    public Optional<ParkingSpot> fingParkingSpotByNumber(String parkingSpotNumber) {
        if (parkingNumberMap.containsKey(parkingSpotNumber)) {
            return Optional.of(parkingNumberMap.get(parkingSpotNumber));
        }
        return Optional.empty();
    }
}
