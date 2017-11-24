package ticket.ticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.ticket.models.Route;

/**
 * Created by lahiru on 11/20/2017.
 */
public interface RouteRepository extends JpaRepository<Route, String>
{
}
