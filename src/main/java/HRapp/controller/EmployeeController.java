package HRapp.controller;

import HRapp.dtomap.employeeDTO;
import HRapp.model.Employee;
import HRapp.service.employeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final employeeServiceImpl employeeService;
    public EmployeeController(employeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Integer addEmployee(@RequestBody employeeDTO employee) {
        return employeeService.addEmployee(employee);
    }

    @PatchMapping("/{id}")
    public void updateEmployee(@PathVariable Integer id, @RequestBody Map<String,Object> updateemployee) {
        employeeService.updateEmployee(id, updateemployee);
    }

    @GetMapping
    public List<employeeDTO> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
    @GetMapping("/department/{departmentId}")
    public List<employeeDTO> listByDepartment(@PathVariable Integer departmentId) {
        return employeeService.listEmployeeByDepartment(departmentId);
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }
}

