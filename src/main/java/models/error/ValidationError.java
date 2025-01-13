package models.error;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
  public String message;
  public ArrayList<String> members;
}
