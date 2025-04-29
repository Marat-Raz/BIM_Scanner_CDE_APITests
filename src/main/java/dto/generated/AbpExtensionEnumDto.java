package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpExtensionEnumFieldDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpExtensionEnumDto {

    public ArrayList<AbpExtensionEnumFieldDto> fields;
    public String localizationResource;
}
