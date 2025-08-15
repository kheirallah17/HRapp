package HRapp.service;

import HRapp.dtomap.leaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface leaveService {
    // Submit leave request with leave type & days
    leaveDTO submitLeaveRequest(Integer employeeId, Integer leaveTypeId, LocalDate startDate, LocalDate endDate);

    // Get leaves of an employee for a range of dates
    List<leaveDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, LocalDate from, LocalDate to);

    // Get leaves by type and by employee (paginated)
    Page<leaveDTO> getLeavesByTypeAndEmployee(Integer employeeId, Integer leaveTypeId, Pageable pageable);
}
