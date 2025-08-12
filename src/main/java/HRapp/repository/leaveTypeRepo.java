package HRapp.repository;

import HRapp.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface leaveTypeRepo extends JpaRepository<LeaveType, Integer> {
}
