package HRapp.dtomap;

import HRapp.model.LeaveType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface leaveTypeMapper {
    leaveTypeDTO toDTO(LeaveType leaveType);
    LeaveType toLeaveType(leaveTypeDTO dto);
}
