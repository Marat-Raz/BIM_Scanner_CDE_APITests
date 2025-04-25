package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicsImportSummaryDto {

    private String bcfVersion;
    private CdeProjectImportStatus project;
    private ArrayList<CdeTopicImportStatus> topics;
}
