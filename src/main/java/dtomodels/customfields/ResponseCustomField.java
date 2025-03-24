package dtomodels.customfields;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import dtomodels.customfields.enumerationitem.ResponseEnumerationItem;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseCustomField {

  public String type;
  public String name;
  public String description;
  public boolean archived;
  public ArrayList<ResponseEnumerationItem> enumerationItems;
  public String creationTime;
  public String creatorId;
  public String id;

}
