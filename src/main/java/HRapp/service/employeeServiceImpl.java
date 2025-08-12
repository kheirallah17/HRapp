package HRapp.service;

import HRapp.dtomap.departmentDTO;
import HRapp.dtomap.employeeDTO;
import HRapp.dtomap.EmployeeMapper;
import HRapp.model.Department;
import HRapp.model.Employee;
import HRapp.repository.departmentRepo;
import HRapp.repository.employeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class employeeServiceImpl implements employeeService{
    private final employeeRepo employeeRepo;
    private final generalService generalService;
    private final EmployeeMapper employeeMapper;
    private final departmentRepo departmentRepo;
    public employeeServiceImpl(employeeRepo employeeRepo, generalService generalEmployeeService, EmployeeMapper employeeMapper, departmentRepo departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.generalService = generalEmployeeService;
        this.employeeMapper = employeeMapper;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Integer addEmployee(employeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEmployee(employeeDTO);
        return employeeRepo.saveAndFlush(employee).getId();
    }

    @Override
    public void updateEmployee(Integer id, Map<String, Object> updateEmployee) {
        Employee employee = employeeRepo.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
        generalService.updateEntity(updateEmployee,employee,Employee.class);
        employeeRepo.saveAndFlush(employee);
    }

    @Override
    public List<employeeDTO> listEmployeeByDepartment(Integer departmentId) {
        List<Employee> employees = employeeRepo.findByDepartmentId(departmentId);

        Department dept = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return employees.stream()
                .map(employee -> {
                    employeeDTO dto = employeeMapper.toEmployeeDTO(employee);
                    dto.setDepartmentName(dept.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
    }

    @Override
    public List<employeeDTO> getAllEmployee() {
        return employeeRepo.findAll().stream().map(employeeMapper::toEmployeeDTO).collect(Collectors.toList());
    }
    @Override
    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }
}
