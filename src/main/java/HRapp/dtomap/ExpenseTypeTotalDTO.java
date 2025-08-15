package HRapp.dtomap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseTypeTotalDTO {
    private Integer expenseTypeID;
    private BigDecimal amount;
}
