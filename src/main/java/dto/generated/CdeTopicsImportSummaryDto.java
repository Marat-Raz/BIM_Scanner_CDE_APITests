package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicImportStatus;
import dto.generated.CdeProjectImportStatus;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicsImportSummaryDto {

    public String bcfVersion;
    public CdeProjectImportStatus project;
    public ArrayList<CdeTopicImportStatus> topics;
}
