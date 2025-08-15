package HRapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "expenseclaim")
public class Expenseclaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "claim_date", nullable = false)
    private LocalDate claimDate;

    @Lob
    @Column(name = "description")
    private String description;

    @ColumnDefault("0.00")
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @ColumnDefault("'PENDING'")
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

}