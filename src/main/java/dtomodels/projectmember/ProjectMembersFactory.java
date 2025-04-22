package dtomodels.projectmember;

import dtomodels.projectroles.ResponseProjectRole;
import dtomodels.user.ResponseUser;

public class ProjectMembersFactory {

  private ResponseUser responseUser;
  private ResponseProjectRole responseProjectRole;
  private String userId = responseUser.getId();
  private String roleId = responseProjectRole.getId();

  public ProjectMember createMember(MemberType memberType) {
    switch (memberType) {
      case USER_ID_IS_NULL:
        return new ProjectMember(null, roleId);
      case ROLE_IS_NULL:
        return new ProjectMember(userId, null);
      case DEFAULT:
      default:
        return new ProjectMember(userId, roleId);
    }
  }

  public ProjectMember from(ResponseUser responseUser, ResponseProjectRole responseProjectRole) {
    return new ProjectMember(responseUser.getId(), responseProjectRole.getId());
  }
}