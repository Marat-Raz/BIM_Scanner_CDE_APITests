package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpApplicationAuthConfigurationDto {

    public Map<String, Boolean> grantedPolicies;
}
