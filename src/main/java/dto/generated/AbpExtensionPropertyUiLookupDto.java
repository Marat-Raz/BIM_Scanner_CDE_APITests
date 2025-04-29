package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpExtensionPropertyUiLookupDto {

    public String url;
    public String resultListPropertyName;
    public String displayPropertyName;
    public String valuePropertyName;
    public String filterParamName;
}
