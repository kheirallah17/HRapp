package HRapp.controller;

import HRapp.dtomap.leaveTypeDTO;
import HRapp.model.LeaveType;
import HRapp.service.leaveTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaveTypes")
public class LeaveTypeController {
    private final leaveTypeServiceImpl leaveTypeService;
    public LeaveTypeController(leaveTypeServiceImpl leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }
    @PostMapping
    public Integer addLeaveType(@RequestBody leaveTypeDTO leaveType){
        return leaveTypeService.createLeaveType(leaveType);
    }
    @GetMapping
    public List<leaveTypeDTO> getAllLeaveTypes(){
        return leaveTypeService.getAllLeaveTypes();
    }
    @GetMapping("/{id}")
    public LeaveType getLeaveTypeById(@PathVariable Integer id){
        return leaveTypeService.getLeaveTypeById(id);
    }
    @PatchMapping("/{id}")
    public void updateLeaveType(@PathVariable Integer id, @RequestBody Map<String, Object> updateLeaveType){
        leaveTypeService.updateLeaveType(id, updateLeaveType);
    }
    @DeleteMapping("/{id}")
    public void deleteLeaveTypeById(@PathVariable Integer id){
        leaveTypeService.deleteLeaveType(id);
    }
}
