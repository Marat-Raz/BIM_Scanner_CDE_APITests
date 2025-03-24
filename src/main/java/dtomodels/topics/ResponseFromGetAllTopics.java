package dtomodels.topics;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseFromGetAllTopics {

  public int totalCount;
  public ArrayList<ResponseTopics> items;

}
