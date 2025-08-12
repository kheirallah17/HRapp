package HRapp.dtomap;

import HRapp.model.Leave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface leaveMapper {
    leaveDTO leaveToLeaveDTO(Leave leave);
    Leave toLeave(leaveDTO leaveDTO);
}
