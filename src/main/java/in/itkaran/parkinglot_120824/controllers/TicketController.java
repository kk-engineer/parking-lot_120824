package in.itkaran.parkinglot_120824.controllers;


import in.itkaran.parkinglot_120824.dtos.IssueTicketRequestDto;
import in.itkaran.parkinglot_120824.dtos.IssueTicketResponseDto;
import in.itkaran.parkinglot_120824.dtos.ResponseStatus;
import in.itkaran.parkinglot_120824.exceptions.GateNotFoundException;
import in.itkaran.parkinglot_120824.models.Ticket;
import in.itkaran.parkinglot_120824.services.TicketService;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto issueTicketRequestDto) {
        IssueTicketResponseDto issueTicketResponseDto = new IssueTicketResponseDto();
        try {
            Ticket ticket = ticketService.issueTicket(
                    issueTicketRequestDto.getGateId(),
                    issueTicketRequestDto.getVehicleNumber(),
                    issueTicketRequestDto.getVehicleType(),
                    issueTicketRequestDto.getOwnerName());
            issueTicketResponseDto.setTicket(ticket);
            issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (GateNotFoundException gateNotFoundException) {
            System.out.println("Gate not found reason: " + gateNotFoundException.getMessage());
            issueTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return issueTicketResponseDto;
    }

}
