package HRapp.controller;

import HRapp.dtomap.departmentDTO;
import HRapp.model.Department;
import HRapp.service.departmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final departmentServiceImpl departmentService;
    public DepartmentController(departmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Integer createDepartment(@RequestBody departmentDTO department) {
        return departmentService.createDepartment(department);
    }
    @GetMapping
    public List<departmentDTO> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Integer id){
        return departmentService.getDepartmentById(id);
    }

    @PatchMapping("/{id}")
    public void updateDepartment(@PathVariable Integer id, @RequestBody Map<String,Object> updateDepartment) {
        departmentService.updateDepartment(id, updateDepartment);
    }
    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable Integer id){
        departmentService.deleteDepartment(id);
    }
}
