package HRapp.dtomap;

import HRapp.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    employeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployee(employeeDTO dto);
}
