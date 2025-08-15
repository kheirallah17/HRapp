package HRapp.service;

import HRapp.dtomap.ExpenseTypeTotalDTO;
import HRapp.dtomap.expenseClaimDTO;
import HRapp.dtomap.expenseClaimEntryDTO;

import java.util.List;

public interface expenseClaimEntryService {
    void submitExpenseClaim(expenseClaimDTO claimDTO);
    Integer createEntry(expenseClaimEntryDTO claimEntryDTO);
    List<ExpenseTypeTotalDTO> getTotalsPerTypeForEmployee(Integer employeeId);
    List<expenseClaimEntryDTO> getAllExpenseEntryByTypeId(Integer expenseTypeId);
    String submitExpenseClaims(List<expenseClaimDTO> claimDTOS);
}
