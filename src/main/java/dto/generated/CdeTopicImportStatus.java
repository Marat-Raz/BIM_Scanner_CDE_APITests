package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeTopicImportStatus {

    private String topicId;
    private CdeEntityStatus status;
    private ArrayList<CdePropertyImportErrors> errors;
}
