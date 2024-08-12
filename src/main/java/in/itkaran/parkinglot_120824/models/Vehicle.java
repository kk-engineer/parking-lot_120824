package in.itkaran.parkinglot_120824.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle extends BaseModel{
    private String number;
    private VehicleType type;
    private String ownerName;

    public String toString() {
        return number;
    }
}
