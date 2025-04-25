package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpRemoteServiceErrorInfo {

    private String code;
    private String message;
    private String details;
    private Object data;
    private ArrayList<AbpRemoteServiceValidationErrorInfo> validationErrors;
}
