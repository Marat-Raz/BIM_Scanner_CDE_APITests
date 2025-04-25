package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpRemoteServiceValidationErrorInfo {

    private String message;
    private ArrayList<String> members;
}
