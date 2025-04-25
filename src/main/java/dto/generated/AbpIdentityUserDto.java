package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpIdentityUserDto {

  private Object extraProperties;
  private String id;
  private String creationTime;
  private String creatorId;
  private String lastModificationTime;
  private String lastModifierId;
  private Boolean isDeleted;
  private String deleterId;
  private String deletionTime;
  private String tenantId;
  private String userName;
  private String name;
  private String surname;
  private String email;
  private Boolean emailConfirmed;
  private String phoneNumber;
  private Boolean phoneNumberConfirmed;
  private Boolean isActive;
  private Boolean lockoutEnabled;
  private Integer accessFailedCount;
  private String lockoutEnd;
  private String concurrencyStamp;
  private Integer entityVersion;
  private String lastPasswordChangeTime;
}