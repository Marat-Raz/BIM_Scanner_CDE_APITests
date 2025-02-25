package models.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseUser {

  public String tenantId;
  public String userName;
  public String name;
  public String surname;
  public String email;
  public boolean emailConfirmed;
  public String phoneNumber;
  public boolean phoneNumberConfirmed;
  public boolean isActive;
  public boolean lockoutEnabled;
  public int accessFailedCount;
  public String lockoutEnd;
  public String concurrencyStamp;
  public int entityVersion;
  public String lastPasswordChangeTime;
  public boolean isDeleted;
  public Object deleterId;
  public Object deletionTime;
  public Object lastModificationTime;
  public Object lastModifierId;
  public String creationTime;
  public String creatorId;
  public String id;
  public Object extraProperties;

}
