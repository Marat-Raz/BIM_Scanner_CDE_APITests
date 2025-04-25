package dto.generated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CdeUpdateProjectDto {

  private String name;
  private String description;
  private String responsibleId;
  private String completionTime;
  private String concurrencyStamp;

  public CdeUpdateProjectDto(CdeCreateProjectDto cdeCreateProjectDto, String responsibleId, String concurrencyStamp) {
    name = cdeCreateProjectDto.getName();
    description = cdeCreateProjectDto.getDescription();
    this.responsibleId = responsibleId;
    completionTime = cdeCreateProjectDto.getCompletionTime();
    this.concurrencyStamp = concurrencyStamp;
  }
}

