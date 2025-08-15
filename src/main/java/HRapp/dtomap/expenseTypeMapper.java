package HRapp.dtomap;

import HRapp.model.ExpenseType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface expenseTypeMapper {
    expenseTypeDTO toExpenseTypeDTO(ExpenseType expenseType);
    ExpenseType toExpenseType(expenseTypeDTO dto);
}
