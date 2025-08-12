package HRapp.service;

import HRapp.dtomap.departmentDTO;
import HRapp.model.Department;

import java.util.List;
import java.util.Map;

public interface departmentService {
    Integer createDepartment(departmentDTO departmentDTO);
    void deleteDepartment(Integer id);
    void updateDepartment(Integer id , Map<String,Object> updateDepartment);
    Department getDepartmentById(Integer id);
    List<departmentDTO> getAllDepartment();
}
