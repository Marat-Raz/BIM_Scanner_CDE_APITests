package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeJobStatus;
import dto.generated.CdeUserDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeModelRevisionDto {

    public String modelId;
    public Integer version;
    public String modelName;
    public String comment;
    public String creationTime;
    public CdeUserDto createdBy;
    public CdeJobStatus status;
}
