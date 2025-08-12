package HRapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;
@Service
public class generalService {
    public void updateEntity(Map<String, Object> entityDTO, Object entityToUpdate, Class entityToUpdateClass) {
        entityDTO.remove("createdBy");

        entityDTO.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(entityToUpdateClass, k);

            if (field == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field " + k + " does not exist in " + entityToUpdateClass.getName());
            }

            // Type conversions
            if (field.getType().equals(Long.class) && v instanceof Integer)
                v = ((Integer) v).longValue();

            if (field.getType().equals(Double.class) && v instanceof Long)
                v = ((Long) v).doubleValue();

            if (field.getType().equals(Double.class) && v instanceof Integer)
                v = ((Integer) v).doubleValue();

            if (field.getType().equals(BigDecimal.class) && v instanceof Integer)
                v = BigDecimal.valueOf(((Integer) v).intValue());

            if (field.getType().equals(BigDecimal.class) && v instanceof Double)
                v = BigDecimal.valueOf(((Double) v).doubleValue());

            if (k.equals("suffix") && v != null)
                v = v + "";

            field.setAccessible(true);
            ReflectionUtils.setField(field, entityToUpdate, v);
        });
    }
}
