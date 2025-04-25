package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeNotificationDto {

    private String id;
    private String creationTime;
    private String creatorId;
    private Object content;
    private String userId;
    private String type;
    private Boolean isRead;
}
