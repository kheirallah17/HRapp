package HRapp.dtomap;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class    expenseClaimDTO {
    private Integer id;
    private LocalDate claimDate;
    private String description;
    private BigDecimal total;
    private String status;
    private Integer employeeId;
    private String employeeName;
    List<expenseClaimEntryDTO> entries;
}
