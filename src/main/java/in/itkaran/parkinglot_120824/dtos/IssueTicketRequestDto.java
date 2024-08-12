package in.itkaran.parkinglot_120824.dtos;


import in.itkaran.parkinglot_120824.models.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueTicketRequestDto {
    private Long gateId;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private String ownerName;
}
