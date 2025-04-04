package dtomodels;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PaginatedResponse<T> {

  private int totalCount;
  private ArrayList<T> items;

}
