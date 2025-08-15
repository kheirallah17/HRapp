package HRapp.service;

import HRapp.dtomap.expenseClaimDTO;
import HRapp.model.Expenseclaim;

import java.util.List;
import java.util.Map;

public interface expenseClaimService {
    Integer createExpenseClaim(expenseClaimDTO dto);
    expenseClaimDTO getExpenseClaimById(Integer id);
    List<expenseClaimDTO> getAllExpenseClaims();
    void updateExpenseClaim(Integer id, Map<String,Object> updateExpenseClaim);
    void deleteExpenseClaim(Integer id);
}
