package in.itkaran.parkinglot_120824.dtos;


import in.itkaran.parkinglot_120824.models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueTicketResponseDto {
    private Ticket ticket;
    private ResponseStatus responseStatus;
}
