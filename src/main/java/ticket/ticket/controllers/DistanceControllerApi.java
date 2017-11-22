package ticket.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ticket.ticket.models.DistanceCost;
import ticket.ticket.repositories.DistanceCostRepository;

/**
 * Created by lahiru on 11/22/2017.
 */

@RestController
public class DistanceControllerApi
{
    @Autowired
    DistanceCostRepository distanceCostRepository;

    @PostMapping("/createDistanceCost")
    public boolean createDistanceCost(@RequestBody DistanceCost distanceCost)
    {
        try
        {
            distanceCostRepository.save(distanceCost);
            return true;
        }
        catch (Exception exception)
        {
            System.out.println("Failed to save :" + exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }
}
