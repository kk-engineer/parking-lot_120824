package in.itkaran.parkinglot_120824.repositories;


import in.itkaran.parkinglot_120824.models.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {
    private Map<Long, Ticket> ticketMap = new HashMap<>();
    private Long previousTicketId = 0L;

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            previousTicketId += 1;
            ticket.setId(previousTicketId);
        }
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket findTicketById(Long ticketId) {
        return ticketMap.get(ticketId);
    }
}
