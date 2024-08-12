package in.itkaran.parkinglot_120824.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingSpot extends BaseModel {
    private ParkingSpotStatus status;
    private List<VehicleType> supportedVehicleTypes;
    private String spotNumber;

    public String toString() {
        return "" + spotNumber;
    }
}
