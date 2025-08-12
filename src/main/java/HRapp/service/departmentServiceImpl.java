package HRapp.service;

import HRapp.dtomap.departmentDTO;
import HRapp.dtomap.departmentMapper;
import HRapp.model.Department;
import HRapp.repository.departmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class departmentServiceImpl implements departmentService {
    private final departmentRepo departmentRepo;
    private final generalService generalService;
    private final departmentMapper departmentMapper;
    public departmentServiceImpl(departmentRepo departmentRepo, generalService generalService, departmentMapper departmentMapper) {
        this.departmentRepo = departmentRepo;
        this.generalService = generalService;
        this.departmentMapper = departmentMapper;
    }
    @Override
    public Integer createDepartment(departmentDTO departmentDTO) {
        Department department = departmentMapper.toDepartment(departmentDTO);
        return departmentRepo.saveAndFlush(department).getId();
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepo.deleteById(id);
    }

    @Override
    public void updateDepartment(Integer id, Map<String, Object> updateDepartment) {
        Department  department = departmentRepo.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
        generalService.updateEntity(updateDepartment,department,Department.class);
        departmentRepo.saveAndFlush(department);
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentRepo.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
    }

    @Override
    public List<departmentDTO> getAllDepartment() {
        return departmentRepo.findAll().stream().map(departmentMapper::toDepartmentDTO).collect(Collectors.toList());
    }
}
