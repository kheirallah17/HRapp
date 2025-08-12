package HRapp.dtomap;

import lombok.Data;

@Data
public class employeeDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Integer departmentId;
    private String departmentName;
}
