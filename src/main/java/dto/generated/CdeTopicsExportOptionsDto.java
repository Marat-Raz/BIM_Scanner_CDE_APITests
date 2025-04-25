package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicsExportOptionsDto {

    private CdeTopicExportFormat format;
    private Boolean exportByDefault;
    private ArrayList<String> exceptTopics;
}
