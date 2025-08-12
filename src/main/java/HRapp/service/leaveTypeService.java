package HRapp.service;

import HRapp.dtomap.leaveTypeDTO;
import HRapp.model.LeaveType;

import java.util.List;
import java.util.Map;

public interface leaveTypeService {
    Integer createLeaveType(leaveTypeDTO dto);
    LeaveType getLeaveTypeById(Integer id);
    List<leaveTypeDTO> getAllLeaveTypes();
    void updateLeaveType(Integer id, Map<String,Object> updateLeaveType);
    void deleteLeaveType(Integer id);
}
