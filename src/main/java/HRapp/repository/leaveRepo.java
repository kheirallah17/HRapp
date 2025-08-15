package HRapp.repository;

import HRapp.model.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface leaveRepo extends JpaRepository<Leave, Integer> {

    // Automatically generated query based on method name
    Page<Leave> findByLeaveTypeIdAndEmployeeId(Integer leaveTypeId, Integer employeeId, Pageable pageable);

    // Custom JPQL query
    @Query("""
    SELECT l 
    FROM Leave l
    WHERE l.employeeId = :employeeId
      AND (:from IS NULL OR l.fromDate >= :from)
      AND (:to IS NULL OR l.toDate <= :to)
""")
    List<Leave> findLeavesByEmployeeAndDateRange(
            @Param("employeeId") Integer employeeId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}

