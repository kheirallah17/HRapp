package HRapp.controller;

import HRapp.dtomap.expenseClaimDTO;
import HRapp.model.Expenseclaim;
import HRapp.service.expenseClaimServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expense_claim")
public class ExpenseClaimController {
    private final expenseClaimServiceImpl expenseClaimService;
    public ExpenseClaimController(expenseClaimServiceImpl expenseClaimService) {
        this.expenseClaimService = expenseClaimService;
    }
    @PostMapping
    public Integer addExpenseClaim(@RequestBody expenseClaimDTO expenseClaimDTO) {
        return expenseClaimService.createExpenseClaim(expenseClaimDTO);
    }
    @GetMapping
    public List<expenseClaimDTO> getAllExpenseClaims() {
        return expenseClaimService.getAllExpenseClaims();
    }
    @GetMapping("/{id}")
    public expenseClaimDTO getExpenseClaimById(@PathVariable Integer id) {
        return expenseClaimService.getExpenseClaimById(id);
    }
    @PatchMapping("/{id}")
    public void updateExpenseClaim(@PathVariable Integer id, @RequestBody Map<String, Object> updateExpenseClaim) {
        expenseClaimService.updateExpenseClaim(id, updateExpenseClaim);
    }
    @DeleteMapping("/{id}")
    public void deleteExpenseClaim(@PathVariable Integer id) {
        expenseClaimService.deleteExpenseClaim(id);
    }
}
