package models.customfields.customfieldsintopicbords;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomFieldOnTopicBoards {

  public String id;
  public boolean isEnabled;
  public boolean isRequired;
  public String defaultValue;

}
