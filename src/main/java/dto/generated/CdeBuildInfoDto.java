package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeBuildInfoDto {

    private Integer major;
    private Integer minor;
    private Integer patch;
    private String preRelease;
    private String buildMetadata;
}
