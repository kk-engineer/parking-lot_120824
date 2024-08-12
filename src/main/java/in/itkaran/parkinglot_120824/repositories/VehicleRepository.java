package in.itkaran.parkinglot_120824.repositories;

import in.itkaran.parkinglot_120824.models.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VehicleRepository {
    private Map<Long, Vehicle> vehicleMap = new HashMap<>();
    private Map<String, Vehicle> vehicleNumberMap = new HashMap<>();
    private Long previousVehicleId = 0L;

    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            previousVehicleId += 1;
            vehicle.setId(previousVehicleId);
        }
        vehicleMap.put(vehicle.getId(), vehicle);
        // add it to vehicle number map
        vehicleNumberMap.put(vehicle.getNumber(), vehicle);
        return vehicle;
    }

    public Optional<Vehicle> findVehicleByNumber(String vehicleNumber) {
        if (vehicleNumberMap.containsKey(vehicleNumber)) {
            return Optional.of(vehicleNumberMap.get(vehicleNumber));
        }
        return Optional.empty();
    }
}
