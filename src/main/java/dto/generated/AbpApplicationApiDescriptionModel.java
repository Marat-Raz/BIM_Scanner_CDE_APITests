package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import dto.generated.AbpTypeApiDescriptionModel;
import dto.generated.AbpModuleApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpApplicationApiDescriptionModel {

    public Map<String, AbpModuleApiDescriptionModel> modules;
    public Map<String, AbpTypeApiDescriptionModel> types;
}
