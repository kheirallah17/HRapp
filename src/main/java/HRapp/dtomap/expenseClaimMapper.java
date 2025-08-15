package HRapp.dtomap;

import HRapp.model.Expenseclaim;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface expenseClaimMapper {
    expenseClaimDTO toDTO(Expenseclaim expenseClaim);
    Expenseclaim toEntity(expenseClaimDTO dto);
}
