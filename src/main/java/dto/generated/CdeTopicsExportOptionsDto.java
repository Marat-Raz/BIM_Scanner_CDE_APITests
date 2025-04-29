package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeTopicExportFormat;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicsExportOptionsDto {

    public CdeTopicExportFormat format;
    public Boolean exportByDefault;
    public ArrayList<String> exceptTopics;
}
