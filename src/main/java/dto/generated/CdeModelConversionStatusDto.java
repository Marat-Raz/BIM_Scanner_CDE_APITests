package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeModelConversionStatusDto {

    private String id;
    private String modelId;
    private Integer version;
    private CdeJobStatus status;
    private Double progress;
    private String error;
    private String createdAt;
    private String startedAt;
    private String completedAt;
}
