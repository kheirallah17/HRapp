package HRapp.service;

import HRapp.dtomap.leaveDTO;
import HRapp.dtomap.leaveMapper;
import HRapp.model.Leave;
import HRapp.repository.employeeRepo;
import HRapp.repository.leaveRepo;
import HRapp.repository.leaveTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class leaveServiceImpl implements leaveService {
    private final leaveRepo leaveRepo;
    private final leaveMapper leaveMapper;
    private final employeeRepo employeeRepo;
    private final leaveTypeRepo leaveTypeRepo;
    public leaveServiceImpl(leaveRepo leaveRepo, leaveMapper leaveMapper, employeeRepo employeeRepo, leaveTypeRepo leaveTypeRepo) {
        this.leaveRepo = leaveRepo;
        this.leaveMapper = leaveMapper;
        this.employeeRepo = employeeRepo;
        this.leaveTypeRepo = leaveTypeRepo;
    }
    @Override
    public leaveDTO submitLeaveRequest(Integer employeeId, Integer leaveTypeId, LocalDate startDate, LocalDate endDate) {
        //Check for nulls
        if (employeeId == null) throw new IllegalArgumentException("Employee ID is required.");
        if (leaveTypeId == null) throw new IllegalArgumentException("Leave Type ID is required.");
        if (startDate == null) throw new IllegalArgumentException("Start Date is required.");
        if (endDate == null) throw new IllegalArgumentException("End Date is required.");

        //Validate dates
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        //Check if employee exists
        if (!employeeRepo.existsById(employeeId)) {
            throw new EntityNotFoundException("Employee not found.");
        }

        //Check if leave type exists
        if (!leaveTypeRepo.existsById(leaveTypeId)) {
            throw new EntityNotFoundException("Leave type not found.");
        }

        //Calculate number of days
        Long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        //Create leave entity
        Leave leave = Leave.builder()
                .employeeId(employeeId)
                .leaveTypeId(leaveTypeId)
                .fromDate(startDate)
                .toDate(endDate)
                .numberOfDays(numberOfDays)
                .build();

        //Save
        leaveRepo.saveAndFlush(leave);
        return leaveMapper.leaveToLeaveDTO(leave);
    }

    @Override
    public List<leaveDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, LocalDate from, LocalDate to) {
        return leaveRepo.findLeavesByEmployeeAndDateRange(employeeId, from, to)
                .stream().map(leaveMapper::leaveToLeaveDTO).collect(Collectors.toList());
    }

    @Override
    public Page<leaveDTO> getLeavesByTypeAndEmployee(Integer employeeId, Integer leaveTypeId, Pageable pageable) {
        return leaveRepo.findByLeaveTypeIdAndEmployeeId(employeeId, leaveTypeId, pageable).map(leaveMapper::leaveToLeaveDTO);
    }
}
