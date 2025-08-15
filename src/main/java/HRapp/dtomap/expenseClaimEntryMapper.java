package HRapp.dtomap;

import HRapp.model.Expenseclaimentry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface expenseClaimEntryMapper {
    expenseClaimEntryDTO toDTO(Expenseclaimentry expenseclaimentry);
    Expenseclaimentry toEntity(expenseClaimEntryDTO expenseClaimEntryDTO);
}
