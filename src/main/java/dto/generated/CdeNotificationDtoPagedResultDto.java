package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.CdeNotificationDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeNotificationDtoPagedResultDto {

    public ArrayList<CdeNotificationDto> items;
    public Long totalCount;
}
