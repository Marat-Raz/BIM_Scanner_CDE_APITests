package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeUpdateCustomFieldDto {

    private String name;
    private String description;
    private Boolean archived;
    private ArrayList<CdeAddOrUpdateEnumerationCustomFieldItemDto> enumerationItems;

    public static CdeUpdateCustomFieldDto from(CdeCustomFieldDto responseCustomField) {
        return new CdeUpdateCustomFieldDto(
            responseCustomField.getName(),
            responseCustomField.getDescription(),
            responseCustomField.getArchived(),
            responseCustomField.getEnumerationItems()
        );}

}
