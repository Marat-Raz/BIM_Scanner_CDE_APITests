package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeBuildInfoDto {

    public Integer major;
    public Integer minor;
    public Integer patch;
    public String preRelease;
    public String buildMetadata;
}
