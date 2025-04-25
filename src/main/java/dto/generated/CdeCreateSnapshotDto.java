package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeCreateSnapshotDto {

    private CdeSnapshotType snapshotType;
    private String snapshotData;
}
