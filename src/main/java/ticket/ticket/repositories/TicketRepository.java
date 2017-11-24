package ticket.ticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ticket.ticket.models.Employee;
import ticket.ticket.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String>
{

}
