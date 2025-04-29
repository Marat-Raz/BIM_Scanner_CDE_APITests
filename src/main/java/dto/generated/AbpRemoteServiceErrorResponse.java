package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpRemoteServiceErrorInfo;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpRemoteServiceErrorResponse {

    public AbpRemoteServiceErrorInfo error;
}
