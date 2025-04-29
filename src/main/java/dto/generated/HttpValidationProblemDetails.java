package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class HttpValidationProblemDetails {

    public String type;
    public String title;
    public Integer status;
    public String detail;
    public String instance;
    public Map<String, ArrayList<String>> errors;
}
