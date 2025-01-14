package models.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.user.Owner;
import models.user.Responsible;

@AllArgsConstructor
@Getter
@Setter
public class ServerResponseProject {

  public String name;
  public String description;
  public String completionTime;
  public String concurrencyStamp;
  public Owner owner;
  public Responsible responsible;
  public String lastModificationTime;
  public String lastModifierId;
  public String creationTime;
  public String creatorId;
  public String id;

}
