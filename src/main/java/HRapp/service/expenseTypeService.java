package HRapp.service;

import HRapp.dtomap.expenseTypeDTO;
import HRapp.model.ExpenseType;

import java.util.List;
import java.util.Map;

public interface expenseTypeService {
    Integer createExpenseType(expenseTypeDTO dto);
    ExpenseType getExpenseTypeById(Integer id);
    List<expenseTypeDTO> getAllExpenseTypes();
    void updateExpenseType(Integer id, Map<String,Object> updateExpenseType);
    void deleteExpenseType(Integer id);
}
