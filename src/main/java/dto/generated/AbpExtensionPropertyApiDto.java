package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpExtensionPropertyApiDto {

    private AbpExtensionPropertyApiGetDto onGet;
    private AbpExtensionPropertyApiCreateDto onCreate;
    private AbpExtensionPropertyApiUpdateDto onUpdate;
}
