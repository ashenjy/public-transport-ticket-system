package ticket.ticket.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ticket.ticket.models.Passenger;

public interface passengerRep extends JpaRepository<Passenger, Long> {


}