package HRapp.service;

import HRapp.dtomap.ExpenseTypeTotalDTO;
import HRapp.dtomap.expenseClaimDTO;
import HRapp.dtomap.expenseClaimEntryDTO;
import HRapp.dtomap.expenseClaimEntryMapper;
import HRapp.model.Expenseclaim;
import HRapp.model.Expenseclaimentry;
import HRapp.repository.expenseClaimEntryRepo;
import HRapp.repository.expenseClaimRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class expenseClaimServiceEntryImpl implements expenseClaimEntryService {
    private final expenseClaimRepo claimRepo;
    private final expenseClaimEntryRepo entryRepo;
    private final expenseClaimEntryMapper expenseClaimEntryMapper;

    public expenseClaimServiceEntryImpl(expenseClaimRepo claimRepo, expenseClaimEntryRepo entryRepo, expenseClaimEntryMapper expenseClaimEntryMapper) {
        this.claimRepo = claimRepo;
        this.entryRepo = entryRepo;
        this.expenseClaimEntryMapper = expenseClaimEntryMapper;
    }
    @Override
    public void submitExpenseClaim(expenseClaimDTO claimDTO) {
        if (claimDTO.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID is required.");
        }
        // Create and save the ExpenseClaim
        Expenseclaim claim = new Expenseclaim();
        claim.setEmployeeId(claimDTO.getEmployeeId());
        claim.setClaimDate(claimDTO.getClaimDate() != null ? claimDTO.getClaimDate() : LocalDate.now());
        claim.setStatus("PENDING");
        claim.setTotal(BigDecimal.ZERO);

        claim = claimRepo.saveAndFlush(claim);
        BigDecimal totalSum = BigDecimal.ZERO;

        //Loop through each entry
        for (expenseClaimEntryDTO dto : claimDTO.getEntries()) {
            // Validate entry fields
            if (dto.getEntryDate() == null || dto.getTotal() == null || dto.getExpenseTypeID() == null) {
                throw new IllegalArgumentException("Each entry must have entryDate, total, and expenseTypeID.");
            }

            Expenseclaimentry entry = new Expenseclaimentry();
            entry.setExpenseClaimId(claim.getId());
            entry.setExpenseTypeID(dto.getExpenseTypeID());
            entry.setEntryDate(dto.getEntryDate());
            entry.setDescription(dto.getDescription());
            entry.setTotal(dto.getTotal());

            entryRepo.saveAndFlush(entry);

            totalSum = totalSum.add(dto.getTotal());
        }

        //Update claim total
        claim.setTotal(totalSum);
        claimRepo.saveAndFlush(claim);
    }

    @Override
    public Integer createEntry(expenseClaimEntryDTO claimEntryDTO) {
        Expenseclaimentry entry = expenseClaimEntryMapper.toEntity(claimEntryDTO);
        return entryRepo.saveAndFlush(entry).getId();
    }

    @Override
    public List<ExpenseTypeTotalDTO> getTotalsPerTypeForEmployee(Integer employeeId) {
        if (employeeId == null)
            throw new IllegalArgumentException("Employee ID is required.");
        return entryRepo.getTotalPerExpenseTypeByEmployee(employeeId);
    }

    @Override
    public List<expenseClaimEntryDTO> getAllExpenseEntryByTypeId(Integer expenseTypeId) {
        if (expenseTypeId == null) {
            throw new IllegalArgumentException("Expense Type ID is required.");
        }
        List<Expenseclaimentry> entries = entryRepo.findByExpenseTypeID(expenseTypeId);
        return entries.stream()
                .map(expenseClaimEntryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String submitExpenseClaims(List<expenseClaimDTO> claimDTOS) {
        if(claimDTOS == null || claimDTOS.isEmpty())
            throw new IllegalArgumentException("List of expense claims cannot be empty.");
        for (expenseClaimDTO claimDTO : claimDTOS) {
            submitExpenseClaim(claimDTO);
        }
        return "success";
    }
}
