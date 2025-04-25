package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class CdeNotificationDtoPagedResultDto {

    private ArrayList<CdeNotificationDto> items;
    private Long totalCount;
}
