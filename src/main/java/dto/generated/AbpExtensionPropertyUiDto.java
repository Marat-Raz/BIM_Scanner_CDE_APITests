package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpExtensionPropertyUiDto {

    private AbpExtensionPropertyUiTableDto onTable;
    private AbpExtensionPropertyUiFormDto onCreateForm;
    private AbpExtensionPropertyUiFormDto onEditForm;
    private AbpExtensionPropertyUiLookupDto lookup;
}
