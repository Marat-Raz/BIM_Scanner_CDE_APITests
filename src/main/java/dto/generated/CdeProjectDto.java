package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeProjectDto {

  private String id;
  private String creationTime;
  private String creatorId;
  private String lastModificationTime;
  private String lastModifierId;
  private String name;
  private String description;
  private String completionTime;
  private String concurrencyStamp;
  private CdeUserDto owner;
  private CdeUserDto responsible;

}

