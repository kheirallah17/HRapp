package HRapp.controller;

import HRapp.dtomap.expenseClaimDTO;
import HRapp.dtomap.expenseTypeDTO;
import HRapp.model.ExpenseType;
import HRapp.model.Expenseclaim;
import HRapp.service.expenseTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expense_type")
public class ExpenseTypeController {
    private final expenseTypeServiceImpl expenseTypeService;
    public ExpenseTypeController(expenseTypeServiceImpl expenseTypeService) {
        this.expenseTypeService = expenseTypeService;
    }
    @PostMapping
    public Integer addExpenseType(@RequestBody expenseTypeDTO expenseTypeDTO) {
        return expenseTypeService.createExpenseType(expenseTypeDTO);
    }
    @GetMapping
    public List<expenseTypeDTO> getAllExpenseTypes() {
        return expenseTypeService.getAllExpenseTypes();
    }
    @GetMapping("/{id}")
    public ExpenseType getExpenseTypeById(@PathVariable Integer id) {
        return expenseTypeService.getExpenseTypeById(id);
    }
    @PatchMapping("/{id}")
    public void updateExpenseType(@PathVariable Integer id, @RequestBody Map<String, Object> updateExpenseType) {
        expenseTypeService.updateExpenseType(id, updateExpenseType);
    }
    @DeleteMapping("/{id}")
    public void deleteExpenseType(@PathVariable Integer id) {
        expenseTypeService.deleteExpenseType(id);
    }
}
