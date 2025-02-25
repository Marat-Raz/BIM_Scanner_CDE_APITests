package models.customfields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomFields {
  public String id;
  public boolean isEnabled;
  public boolean isRequired;
  public String defaultValue;
}
