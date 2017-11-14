package ticket.ticket.repositories;

import ticket.ticket.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String>
{
    List<Employee> findAll();
/*
    //login employee
    @Query("SELECT e FROM Event e where e.approval_state=:state")
    public List<Employee> authenticateEmployee(@Param("nic") String nic, @Param("password") String password);*/

    @Transactional
    int deleteEmployeeByEid(int eid);

    Employee findEmployeeByEid(int eid);

  //  Employee findEmployeesByEidAndPassword(int eid, String password);

//    public Employee findEmployeesByNicAndPassword(String nic, String password);

    public Employee findEmployeeByNicAndPasswordAndType(String nic, String password, String type);
}
