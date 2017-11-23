package ticket.ticket.repositories;

import ticket.ticket.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String>
{
    @Transactional
    int deleteEmployeeByEid(int eid);

    Employee findEmployeeByEid(int eid);

    Employee findEmployeeByNicAndPasswordAndType(String nic, String password, String type);
}