package dtomodels.customfields;

import dto.generated.CdeCreateCustomFieldDto;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListOfCustomField { // todo переделать на CustomFieldList<T>

  public ArrayList<CdeCreateCustomFieldDto> cdeCreateCustomFieldDto;

  public ListOfCustomField(CdeCreateCustomFieldDto cdeCreateCustomFieldDto) {
    this.cdeCreateCustomFieldDto = new ArrayList<>();
    this.cdeCreateCustomFieldDto.add(cdeCreateCustomFieldDto);
  }

  public void addCustomField(CdeCreateCustomFieldDto cdeCreateCustomFieldDto) {
    this.cdeCreateCustomFieldDto.add(cdeCreateCustomFieldDto);
  }
}
/*
public class CustomFieldList<T> {

  public ArrayList<T> fields;

  public CustomFieldList(T field) {
    this.fields = new ArrayList<>();
    this.fields.add(field);
  }

  public void addField(T field) {
    this.fields.add(field);
  }
}
 */