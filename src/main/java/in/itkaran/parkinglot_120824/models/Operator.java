package in.itkaran.parkinglot_120824.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operator extends BaseModel {
    private String name;
    private int empId;

    public String toString() {
        return name;
    }
}
