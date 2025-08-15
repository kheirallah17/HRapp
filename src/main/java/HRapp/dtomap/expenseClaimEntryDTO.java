package HRapp.dtomap;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class expenseClaimEntryDTO {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate entryDate;
    private String description;
    private BigDecimal total;
    private Integer expenseTypeID;
    private Integer expenseClaimId;
}