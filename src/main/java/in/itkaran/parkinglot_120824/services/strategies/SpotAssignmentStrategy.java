package in.itkaran.parkinglot_120824.services.strategies;


import in.itkaran.parkinglot_120824.models.Gate;
import in.itkaran.parkinglot_120824.models.ParkingSpot;
import in.itkaran.parkinglot_120824.models.VehicleType;
import in.itkaran.parkinglot_120824.repositories.ParkingSpotRepository;

public interface SpotAssignmentStrategy {
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
