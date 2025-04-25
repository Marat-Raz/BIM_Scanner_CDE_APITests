package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProblemDetails {

    private String type;
    private String title;
    private Integer status;
    private String detail;
    private String instance;
}
