package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpExtensionPropertyUiLookupDto;
import dto.generated.AbpExtensionPropertyUiFormDto;
import dto.generated.AbpExtensionPropertyUiTableDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpExtensionPropertyUiDto {

    public AbpExtensionPropertyUiTableDto onTable;
    public AbpExtensionPropertyUiFormDto onCreateForm;
    public AbpExtensionPropertyUiFormDto onEditForm;
    public AbpExtensionPropertyUiLookupDto lookup;
}
