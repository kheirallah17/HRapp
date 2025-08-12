package HRapp.service;

import HRapp.dtomap.leaveTypeDTO;
import HRapp.dtomap.leaveTypeMapper;
import HRapp.model.Leave;
import HRapp.model.LeaveType;
import HRapp.repository.leaveTypeRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class leaveTypeServiceImpl implements leaveTypeService {
    private final leaveTypeRepo leaveTypeRepo;
    private final generalService generalService;
    private final leaveTypeMapper leaveTypeMapper;
    public leaveTypeServiceImpl(leaveTypeRepo leaveTypeRepo, generalService generalService, leaveTypeMapper leaveTypeMapper) {
        this.leaveTypeRepo = leaveTypeRepo;
        this.generalService = generalService;
        this.leaveTypeMapper = leaveTypeMapper;
    }
    @Override
    public Integer createLeaveType(leaveTypeDTO dto) {
        LeaveType leaveType = leaveTypeMapper.toLeaveType(dto);
        return leaveTypeRepo.saveAndFlush(leaveType).getId();
    }

    @Override
    public LeaveType getLeaveTypeById(Integer id) {
        return leaveTypeRepo.findById(id).orElseThrow(()->new RuntimeException("LeaveType not found"));
    }

    @Override
    public List<leaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeRepo.findAll().stream().map(leaveTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updateLeaveType(Integer id, Map<String, Object> updateLeaveType) {
        LeaveType leaveType = leaveTypeRepo.findById(id).orElseThrow(()->new RuntimeException("leave not found"));
        generalService.updateEntity(updateLeaveType,leaveType,LeaveType.class);
        leaveTypeRepo.saveAndFlush(leaveType);
    }

    @Override
    public void deleteLeaveType(Integer id) {
        leaveTypeRepo.deleteById(id);
    }
}
