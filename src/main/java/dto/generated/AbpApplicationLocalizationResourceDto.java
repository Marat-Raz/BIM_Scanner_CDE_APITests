package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpApplicationLocalizationResourceDto {

    public Map<String, String> texts;
    public ArrayList<String> baseResources;
}
