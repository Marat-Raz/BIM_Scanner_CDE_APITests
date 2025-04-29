package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpIValueValidator;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpIStringValueType {

    public String name;
    public Map<String, Object> properties;
    public AbpIValueValidator validator;
}
