package dto.generated;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeUpdateCustomFieldDto {

  private String name;
  private String description;
  private Boolean archived;
  private ArrayList<CdeAddOrUpdateEnumerationCustomFieldItemDto> enumerationItems;

  public static CdeUpdateCustomFieldDto from(CdeCustomFieldDto responseCustomField) {
    ArrayList<CdeAddOrUpdateEnumerationCustomFieldItemDto> enumerationItemsArray = new ArrayList<>();
    for (int i = 0; i < responseCustomField.getEnumerationItems().size(); i++) {
      enumerationItemsArray.add(CdeEnumerationCustomFieldItemDto
          .convert(responseCustomField.getEnumerationItems().get(i)));
    }

    return new CdeUpdateCustomFieldDto(
        responseCustomField.getName(),
        responseCustomField.getDescription(),
        responseCustomField.getArchived(),
        enumerationItemsArray
        );
  }

}
