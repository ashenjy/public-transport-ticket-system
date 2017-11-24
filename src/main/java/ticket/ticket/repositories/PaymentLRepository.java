package ticket.ticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.ticket.models.PaymentL;

/**
 * Created by lahiru on 11/24/2017.
 */
public interface PaymentLRepository extends JpaRepository<PaymentL, String>
{
}
