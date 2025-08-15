package HRapp.repository;

import HRapp.model.Expenseclaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface expenseClaimRepo extends JpaRepository<Expenseclaim,Integer> {

    // Get all claims by an employee
    List<Expenseclaim> findByEmployeeId(Integer employeeId);

    // Get all claims by employee within a date range
    @Query("SELECT e FROM Expenseclaim e WHERE e.employeeId = :employeeId AND e.claimDate >= :from AND e.claimDate <= :to")
    List<Expenseclaim> findByEmployeeIdAndDateRange(
            @Param("employeeId") Integer employeeId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

//    // Get total claims by type (if linked to ExpenseClaimEntry or type id)
//    @Query("SELECT e.expenseTypeId, SUM(e.total) FROM Expenseclaimentry e WHERE e.employeeId = :employeeId GROUP BY e.expenseTypeId")
//    List<Object[]> getTotalClaimsByTypePerEmployee(@Param("employeeId") Integer employeeId);
}
