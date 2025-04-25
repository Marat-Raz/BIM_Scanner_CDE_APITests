package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpExtensionEnumDto {

    private ArrayList<AbpExtensionEnumFieldDto> fields;
    private String localizationResource;
}
