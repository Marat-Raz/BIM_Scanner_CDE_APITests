package dtomodels.projectmember;

import dtomodels.user.CdeUserDto;
import dtomodels.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeProjectMemberDto {

  public String projectId;
  public String userId;
  public String creationTime;
  public String creatorId;
  public String memberType;
  public CdeUserDto user;
  public CdeProjectMemberRoleDto role;

}
