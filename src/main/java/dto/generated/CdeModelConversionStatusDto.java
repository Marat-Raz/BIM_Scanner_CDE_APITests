package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeJobStatus;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeModelConversionStatusDto {

    public String id;
    public String modelId;
    public Integer version;
    public CdeJobStatus status;
    public Double progress;
    public String error;
    public String createdAt;
    public String startedAt;
    public String completedAt;
}
