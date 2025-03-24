package dtomodels.project;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseFromGetAllProjects {

  public int totalCount;
  public ArrayList<ServerResponseProject> items;
}
