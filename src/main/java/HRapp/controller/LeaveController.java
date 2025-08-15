package HRapp.controller;

import HRapp.dtomap.leaveDTO;
import HRapp.service.leaveServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor

public class LeaveController {

    private final leaveServiceImpl leaveService;

    //Submit a leave request with JSON body
    @PostMapping("/submit")
    public leaveDTO submitLeaveRequest(@RequestBody leaveDTO request) {
        return leaveService.submitLeaveRequest(
                request.getEmployeeId(),
                request.getLeaveTypeId(),
                request.getFromDate(),
                request.getToDate()
        );
    }

    //Get leaves of an employee for a date range
    @PostMapping("/employee/leavesByRange")
    public List<leaveDTO> getLeavesByEmployeeAndDateRange(@RequestBody leaveDTO request) {
        return leaveService.getLeavesByEmployeeAndDateRange(
                request.getEmployeeId(),
                request.getFromDate(),
                request.getToDate()
        );
    }

    //Get leaves by type and employee
    @PostMapping("/employee/leavesByType")
    public Page<leaveDTO> getLeavesByTypeAndEmployee(@RequestBody leaveDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return leaveService.getLeavesByTypeAndEmployee(
                request.getEmployeeId(),
                request.getLeaveTypeId(),
                pageable
        );
    }
}
