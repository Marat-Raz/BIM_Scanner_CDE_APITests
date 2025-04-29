package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeNotificationDto {

    public String id;
    public String creationTime;
    public String creatorId;
    public Object content;
    public String userId;
    public String type;
    public Boolean isRead;
}
