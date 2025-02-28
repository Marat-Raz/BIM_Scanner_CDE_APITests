package models.customfields.customfieldstoedit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomFieldToEdit {

  public String id;
  public boolean isEnabled;
  public boolean isRequired;
  public String defaultValue;

}
