package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeTopicCommentDto {

  private String id;
  private String topicId;
  private String creationTime;
  private CdeUserDto creator;
  private String lastModificationTime;
  private CdeUserDto lastModifier;
  private String comment;

}
