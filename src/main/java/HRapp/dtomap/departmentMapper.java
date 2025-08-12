package HRapp.dtomap;

import HRapp.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface departmentMapper {
    departmentDTO toDepartmentDTO(Department department);
    Department toDepartment(departmentDTO dto);
}
