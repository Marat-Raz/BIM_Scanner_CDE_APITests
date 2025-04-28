package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicBoardCustomFieldDto {

    private String id;
    private Boolean isEnabled;
    private Boolean isRequired;
    private String defaultValue;


    public static CdeModifyTopicBoardCustomFieldDto
    convert(CdeTopicBoardCustomFieldDto source) {
        if (source == null) {
            return null;
        }

        return new CdeModifyTopicBoardCustomFieldDto(
            source.getId(),
            source.getIsEnabled(),
            source.getIsRequired(),
            source.getDefaultValue()
        );
    }
}
