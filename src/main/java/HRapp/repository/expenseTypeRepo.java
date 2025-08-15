package HRapp.repository;

import HRapp.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface expenseTypeRepo extends JpaRepository<ExpenseType, Integer> {

}
