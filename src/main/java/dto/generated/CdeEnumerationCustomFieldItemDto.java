package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeEnumerationCustomFieldItemDto {

  private String id;
  private String name;
  private Boolean disabled;

  public static CdeAddOrUpdateEnumerationCustomFieldItemDto
  convert(CdeEnumerationCustomFieldItemDto source) {
    if (source == null) {
      return null;
    }

    return new CdeAddOrUpdateEnumerationCustomFieldItemDto(
        source.getId(),
        source.getName(),
        source.getDisabled()
    );
  }
}
