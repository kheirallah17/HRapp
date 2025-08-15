package HRapp.repository;

import HRapp.dtomap.ExpenseTypeTotalDTO;
import HRapp.model.Expenseclaimentry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface expenseClaimEntryRepo extends JpaRepository<Expenseclaimentry, Integer> {
    @Query("SELECT new HRapp.dtomap.ExpenseTypeTotalDTO(e.expenseTypeID, SUM(e.total)) " +
            "FROM Expenseclaimentry e " +
            "JOIN Expenseclaim c ON e.expenseClaimId = c.id " +
            "WHERE c.employeeId = :employeeId " +
            "GROUP BY e.expenseTypeID")
    List<ExpenseTypeTotalDTO> getTotalPerExpenseTypeByEmployee(@Param("employeeId") Integer employeeId);

    @Query("SELECT e FROM Expenseclaimentry e WHERE e.expenseTypeID = :expenseTypeID")
    List<Expenseclaimentry> findByExpenseTypeID(@Param("expenseTypeID") Integer expenseTypeID);
}
