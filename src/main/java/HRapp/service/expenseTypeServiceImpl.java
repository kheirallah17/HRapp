package HRapp.service;

import HRapp.dtomap.expenseTypeDTO;
import HRapp.dtomap.expenseTypeMapper;
import HRapp.model.ExpenseType;
import HRapp.repository.expenseTypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class expenseTypeServiceImpl implements expenseTypeService {
    private final expenseTypeRepo expenseTypeRepo;
    private final generalService generalService;
    private final expenseTypeMapper expenseTypeMapper;
    public expenseTypeServiceImpl(expenseTypeRepo expenseTypeRepo, generalService generalService, expenseTypeMapper expenseTypeMapper) {
        this.expenseTypeRepo = expenseTypeRepo;
        this.generalService = generalService;
        this.expenseTypeMapper = expenseTypeMapper;
    }

    @Override
    public Integer createExpenseType(expenseTypeDTO dto) {
        ExpenseType expenseType = expenseTypeMapper.toExpenseType(dto);
        return expenseTypeRepo.saveAndFlush(expenseType).getId();
    }

    @Override
    public ExpenseType getExpenseTypeById(Integer id) {
        return expenseTypeRepo.findById(id).orElseThrow(()->new RuntimeException("Expense Type Not Found"));
    }

    @Override
    public List<expenseTypeDTO> getAllExpenseTypes() {
        return expenseTypeRepo.findAll().stream().map(expenseTypeMapper::toExpenseTypeDTO).collect(Collectors.toList());
    }

    @Override
    public void updateExpenseType(Integer id, Map<String, Object> updateExpenseType) {
        ExpenseType expenseType = expenseTypeRepo.findById(id).orElseThrow(()->new RuntimeException("Expense Type Not Found"));
        generalService.updateEntity(updateExpenseType,expenseType, ExpenseType.class);
        expenseTypeRepo.saveAndFlush(expenseType);
    }

    @Override
    public void deleteExpenseType(Integer id) {
        expenseTypeRepo.deleteById(id);
    }
}
