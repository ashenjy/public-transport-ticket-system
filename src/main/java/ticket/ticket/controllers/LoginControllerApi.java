package ticket.ticket.controllers;

import ticket.ticket.models.Employee;
import ticket.ticket.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllerApi
{
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/findEmployee", method = RequestMethod.GET)
    public Employee findEmployee(@RequestParam(value = "nic") String nic,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "type") String type)
    {
        return employeeRepository.findEmployeeByNicAndPasswordAndType(nic,password,type);
    }
}
