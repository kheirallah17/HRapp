package HRapp.service;

import HRapp.dtomap.expenseClaimDTO;
import HRapp.dtomap.expenseClaimMapper;
import HRapp.model.Employee;
import HRapp.model.Expenseclaim;
import HRapp.repository.employeeRepo;
import HRapp.repository.expenseClaimRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class expenseClaimServiceImpl implements expenseClaimService {
    private final expenseClaimRepo expenseClaimRepo;
    private final employeeRepo employeeRepo;
    private final expenseClaimMapper expenseClaimMapper;
    private final generalService generalService;
    public expenseClaimServiceImpl(expenseClaimRepo expenseClaimRepo,expenseClaimMapper expenseClaimMapper,generalService generalService,employeeRepo employeeRepo) {
        this.expenseClaimRepo = expenseClaimRepo;
        this.expenseClaimMapper = expenseClaimMapper;
        this.generalService = generalService;
        this.employeeRepo = employeeRepo;
    }
    @Override
    public Integer createExpenseClaim(expenseClaimDTO dto) {
        Expenseclaim expenseclaim = expenseClaimMapper.toEntity(dto);
        return expenseClaimRepo.saveAndFlush(expenseclaim).getId();
    }

    @Override
    public expenseClaimDTO getExpenseClaimById(Integer id) {
        Expenseclaim expenseclaim = expenseClaimRepo.findById(id).orElseThrow(()-> new RuntimeException("Expense Claim not found"));
        expenseClaimDTO dto = expenseClaimMapper.toDTO(expenseclaim);
        Employee temp = employeeRepo.findById(expenseclaim.getEmployeeId()).orElse(null);
        if (temp != null) {
            dto.setEmployeeName(temp.getName());
        }
        return dto;
    }

    @Override
    public List<expenseClaimDTO> getAllExpenseClaims() {
        return expenseClaimRepo.findAll().stream().map(claim -> {
            expenseClaimDTO dto = expenseClaimMapper.toDTO(claim);
            Employee temp = employeeRepo.findById(claim.getEmployeeId()).orElse(null);
            if (temp != null) {
                dto.setEmployeeName(temp.getName());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateExpenseClaim(Integer id, Map<String, Object> updateExpenseClaim) {
        Expenseclaim expenseclaim = expenseClaimRepo.findById(id).orElseThrow(()-> new RuntimeException("Expense Claim not found"));
        generalService.updateEntity(updateExpenseClaim,expenseclaim,Expenseclaim.class);
        expenseClaimRepo.saveAndFlush(expenseclaim);
    }

    @Override
    public void deleteExpenseClaim(Integer id) {
        expenseClaimRepo.deleteById(id);
    }
}
