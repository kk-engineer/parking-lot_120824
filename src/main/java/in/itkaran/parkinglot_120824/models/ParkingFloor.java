package in.itkaran.parkinglot_120824.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingFloor extends BaseModel {
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;
    private ParkingFloorStatus parkingFloorStatus;

    public String toString() {
        return "ParkingFloor{" +
                "floorId=" + this.getId() +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
