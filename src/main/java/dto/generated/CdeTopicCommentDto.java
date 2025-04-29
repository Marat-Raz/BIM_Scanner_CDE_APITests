package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeUserDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicCommentDto {

    public String id;
    public String topicId;
    public String creationTime;
    public CdeUserDto creator;
    public String lastModificationTime;
    public CdeUserDto lastModifier;
    public String comment;
}
