package models.error;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Error {
    public String code;
    public String message;
    public String details;
    public Data data;
    public ArrayList<ValidationError> validationErrors;

  public Error() {

  }
}

