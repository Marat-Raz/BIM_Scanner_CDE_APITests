package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeModifyTopicBoardCustomFieldDto {

    private String id;
    private Boolean isEnabled;
    private Boolean isRequired;
    private String defaultValue;
}
