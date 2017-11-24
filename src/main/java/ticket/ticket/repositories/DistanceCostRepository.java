package ticket.ticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.ticket.models.DistanceCost;

/**
 * Created by lahiru on 11/22/2017.
 */
public interface DistanceCostRepository extends JpaRepository<DistanceCost, String>
{

}
