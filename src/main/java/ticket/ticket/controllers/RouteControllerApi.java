package ticket.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ticket.ticket.models.Route;
import ticket.ticket.repositories.RouteRepository;

import java.util.List;

/**
 * Created by lahiru on 11/20/2017.
 */

@RestController
public class RouteControllerApi
{
    @Autowired
    RouteRepository routeRepository;

    @PostMapping("/createRoute")
    public boolean createRoute(@RequestBody Route route)
    {
        routeRepository.save(route);
        return true;
    }

    @GetMapping("/getRoutes")
    public List<Route> getRoutes()
    {
        return routeRepository.findAll();
    }
}
