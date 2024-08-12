package in.itkaran.parkinglot_120824.services.strategies;

import in.itkaran.parkinglot_120824.models.Gate;
import in.itkaran.parkinglot_120824.models.ParkingSpot;
import in.itkaran.parkinglot_120824.models.VehicleType;
import java.util.Random;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {
    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
        Random random = new Random();
        int spotNumber = random.nextInt(50);
        char spotChar = (char) (random.nextInt(26) + 'A');
        String spotStr = spotChar + String.valueOf(spotNumber);
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotNumber(spotStr);
        return parkingSpot;
    }
}
