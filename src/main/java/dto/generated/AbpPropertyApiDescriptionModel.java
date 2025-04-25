package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpPropertyApiDescriptionModel {

    private String name;
    private String jsonName;
    private String type;
    private String typeSimple;
    private Boolean isRequired;
    private Integer minLength;
    private Integer maxLength;
    private String minimum;
    private String maximum;
    private String regex;
}
