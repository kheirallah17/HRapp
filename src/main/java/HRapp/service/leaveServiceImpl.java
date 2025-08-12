package HRapp.service;

import HRapp.dtomap.leaveDTO;
import HRapp.dtomap.leaveMapper;
import HRapp.model.Leave;
import HRapp.repository.employeeRepo;
import HRapp.repository.leaveRepo;
import HRapp.repository.leaveTypeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class leaveServiceImpl implements leaveService {
    private final leaveRepo leaveRepo;
    private final leaveMapper leaveMapper;
    public leaveServiceImpl(leaveRepo leaveRepo, leaveMapper leaveMapper, employeeRepo employeeRepo, leaveTypeRepo leaveTypeRepo) {
        this.leaveRepo = leaveRepo;
        this.leaveMapper = leaveMapper;
    }
    @Override
    public leaveDTO submitLeaveRequest(Integer employeeId, Integer leaveTypeId, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        Long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        //Create Leave entity
        Leave leave = Leave.builder()
                .employeeId(employeeId)
                .leaveTypeId(leaveTypeId)
                .fromDate(startDate)
                .toDate(endDate)
                .numberOfDays(numberOfDays)
                .build();
        //Save to database
        leaveRepo.saveAndFlush(leave);
        //Convert to DTO
        return leaveMapper.leaveToLeaveDTO(leave);
    }

    @Override
    public List<leaveDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, Date from, Date to) {
        return leaveRepo.findLeavesByEmployeeAndDateRange(employeeId, from, to)
                .stream().map(leaveMapper::leaveToLeaveDTO).collect(Collectors.toList());
    }

    @Override
    public Page<leaveDTO> getLeavesByTypeAndEmployee(Integer employeeId, Integer leaveTypeId, Pageable pageable) {
        return leaveRepo.findByLeaveTypeIdAndEmployeeId(employeeId, leaveTypeId, pageable).map(leaveMapper::leaveToLeaveDTO);
    }
}
