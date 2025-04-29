package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Map;
import dto.generated.AbpRemoteServiceValidationErrorInfo;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpRemoteServiceErrorInfo {

    public String code;
    public String message;
    public String details;
    public Map<String, Object> data;
    public ArrayList<AbpRemoteServiceValidationErrorInfo> validationErrors;
}
