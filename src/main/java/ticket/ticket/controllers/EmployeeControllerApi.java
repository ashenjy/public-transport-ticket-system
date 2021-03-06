package ticket.ticket.controllers;

import ticket.ticket.models.Employee;
import ticket.ticket.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeControllerApi
{
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public boolean addEmployee(@RequestBody Employee employee)
    {
        employeeRepository.save(employee);
        return true;
    }


    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
    public List<Employee> getEmployees()
    {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/editEmployee", method = RequestMethod.PUT)
    public String editEmployee(@RequestParam(value="eid") int eid, @RequestBody Employee employee)
    {
        Employee employeeToEdit=employeeRepository.findEmployeeByEid(eid);

        employeeToEdit.setNic(employee.getNic());
        employeeToEdit.setType(employee.getType());
        employeeToEdit.setSalary(employee.getSalary());
        employeeToEdit.setMobile(employee.getMobile());
        employeeToEdit.setEmail(employee.getEmail());
        employeeToEdit.setName(employee.getName());

        employeeRepository.save(employeeToEdit);
        return "employee successfully edited";
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    boolean deleteEmployee(@RequestParam(value="eid") int eid)
    {
        employeeRepository.deleteEmployeeByEid(eid);
        return true;
    }

    //reset employee password
    @RequestMapping(value = "/resetPassword", method = RequestMethod.PUT)
    public String resetPassword(@RequestParam(value="eid") int eid, @RequestBody Employee employee)
    {
        Employee employeeToReset=employeeRepository.findEmployeeByEid(eid);

        employeeToReset.setPassword("hotel_1234");

        employeeRepository.save(employeeToReset);
        return "employee password reset";
    }

}