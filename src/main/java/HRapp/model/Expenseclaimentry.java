package HRapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "expenseclaimentry")
public class Expenseclaimentry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "expensetype_id", nullable = false)
    private Integer expenseTypeID;

    @Column(name = "expenseclaim_id", nullable = false)
    private Integer expenseClaimId;

}