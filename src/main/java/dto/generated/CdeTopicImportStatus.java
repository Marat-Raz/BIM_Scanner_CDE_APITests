package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeEntityStatus;
import dto.generated.CdePropertyImportErrors;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicImportStatus {

    public String topicId;
    public CdeEntityStatus status;
    public ArrayList<CdePropertyImportErrors> errors;
}
