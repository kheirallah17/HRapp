package HRapp.repository;

import HRapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface employeeRepo extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.departmentId = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Integer departmentId);
}
