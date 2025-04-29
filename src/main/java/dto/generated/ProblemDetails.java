package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProblemDetails {

    public String type;
    public String title;
    public Integer status;
    public String detail;
    public String instance;
}
