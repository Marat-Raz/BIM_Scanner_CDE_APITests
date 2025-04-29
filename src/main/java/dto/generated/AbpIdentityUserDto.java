package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpIdentityUserDto {

    public String id;
    public String creationTime;
    public String creatorId;
    public String lastModificationTime;
    public String lastModifierId;
    public Boolean isDeleted;
    public String deleterId;
    public String deletionTime;
    public String tenantId;
    public String userName;
    public String name;
    public String surname;
    public String email;
    public Boolean emailConfirmed;
    public String phoneNumber;
    public Boolean phoneNumberConfirmed;
    public Boolean isActive;
    public Boolean lockoutEnabled;
    public Integer accessFailedCount;
    public String lockoutEnd;
    public String concurrencyStamp;
    public Integer entityVersion;
    public String lastPasswordChangeTime;
}
