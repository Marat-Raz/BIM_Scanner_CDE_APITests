package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpExtensionPropertyApiCreateDto;
import dto.generated.AbpExtensionPropertyApiGetDto;
import dto.generated.AbpExtensionPropertyApiUpdateDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpExtensionPropertyApiDto {

    public AbpExtensionPropertyApiGetDto onGet;
    public AbpExtensionPropertyApiCreateDto onCreate;
    public AbpExtensionPropertyApiUpdateDto onUpdate;
}
