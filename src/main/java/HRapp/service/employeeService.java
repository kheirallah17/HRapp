package HRapp.service;

import HRapp.dtomap.employeeDTO;
import HRapp.model.Employee;

import java.util.List;
import java.util.Map;

public interface employeeService {
    Integer addEmployee(employeeDTO employeeDTO);
    void updateEmployee(Integer id, Map<String, Object> updateEmployee);
    List<employeeDTO> listEmployeeByDepartment(Integer departmentId);
    List<employeeDTO> getAllEmployee();
    Employee getEmployeeById(Integer id);
    void deleteEmployee(Integer id);
}
