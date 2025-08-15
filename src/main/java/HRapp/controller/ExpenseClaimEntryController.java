package HRapp.controller;

import HRapp.dtomap.ExpenseTypeTotalDTO;
import HRapp.dtomap.expenseClaimDTO;
import HRapp.dtomap.expenseClaimEntryDTO;
import HRapp.service.expenseClaimServiceEntryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-entries")
@RequiredArgsConstructor
public class ExpenseClaimEntryController {
    private final expenseClaimServiceEntryImpl entryService;
    @PostMapping
    public Integer createExpenseClaimEntry(@RequestBody expenseClaimEntryDTO expenseClaimEntryDTO){
        return entryService.createEntry(expenseClaimEntryDTO);
    }
    @PostMapping("/submit")
    public String submitExpenseClaims(@RequestBody List<expenseClaimDTO> expenseClaimDTOS) {
        return entryService.submitExpenseClaims(expenseClaimDTOS);
    }
    @GetMapping("/totals/{employeeId}")
    public List<ExpenseTypeTotalDTO> getExpenseTypeTotals(@PathVariable Integer employeeId) {
        return entryService.getTotalsPerTypeForEmployee(employeeId);
    }
    @GetMapping("/types/{expenseTypeId}")
    public List<expenseClaimEntryDTO> getExpenseClaimEntries(@PathVariable Integer expenseTypeId) {
        return entryService.getAllExpenseEntryByTypeId(expenseTypeId);
    }
}
