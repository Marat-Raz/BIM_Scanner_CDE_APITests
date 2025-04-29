package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpPropertyApiDescriptionModel {

    public String name;
    public String jsonName;
    public String type;
    public String typeSimple;
    public Boolean isRequired;
    public Integer minLength;
    public Integer maxLength;
    public String minimum;
    public String maximum;
    public String regex;
}
