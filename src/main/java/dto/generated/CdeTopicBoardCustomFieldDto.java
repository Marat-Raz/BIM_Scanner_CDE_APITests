package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicBoardCustomFieldDto {

    public String id;
    public Boolean isEnabled;
    public Boolean isRequired;
    public String defaultValue;
}
