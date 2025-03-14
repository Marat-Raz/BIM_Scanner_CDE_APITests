package dtomodels.models;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ListOfModel {

  public int totalCount;
  public ArrayList<ResponseModel> items;

}
