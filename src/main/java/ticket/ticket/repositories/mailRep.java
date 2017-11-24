package ticket.ticket.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ticket.ticket.models.Mail;

public interface mailRep extends JpaRepository<Mail, Long> {


}