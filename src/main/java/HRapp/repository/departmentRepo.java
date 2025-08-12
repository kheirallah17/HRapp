package HRapp.repository;

import HRapp.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface departmentRepo extends JpaRepository<Department, Integer> {
}
