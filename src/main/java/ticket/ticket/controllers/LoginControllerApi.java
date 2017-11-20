package ticket.ticket.controllers;

import org.springframework.web.bind.annotation.*;
import ticket.ticket.models.Employee;
import ticket.ticket.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class LoginControllerApi
{
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/findEmployee")
    public Employee findEmployee(@RequestParam(value = "nic") String nic,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "type") String type)
    {
        return employeeRepository.findEmployeeByNicAndPasswordAndType(nic,password,type);
    }
}
