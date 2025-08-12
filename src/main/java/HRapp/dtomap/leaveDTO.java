package HRapp.dtomap;

import lombok.Data;

import java.time.LocalDate;

@Data
public class leaveDTO {
    private int leaveId;
    private Integer employeeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer numberOfDays;
    private String note;
    private Integer leaveTypeId;
}
