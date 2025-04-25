package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeModelRevisionDto {

    private String modelId;
    private Integer version;
    private String modelName;
    private String comment;
    private String creationTime;
    private CdeUserDto createdBy;
    private CdeJobStatus status;
}
