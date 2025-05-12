package dtomodels.projectmember;

import dto.generated.AbpIdentityUserDto;
import dto.generated.CdeAddProjectMemberDto;
import dto.generated.CdeProjectRoleDto;

public class ProjectMembersFactory {

  private AbpIdentityUserDto responseUser;
  private CdeProjectRoleDto responseProjectRole;
  private String userId = responseUser.getId();
  private String roleId = responseProjectRole.getId();

  public CdeAddProjectMemberDto createMember(MemberType memberType) {
    switch (memberType) {
      case USER_ID_IS_NULL:
        return new CdeAddProjectMemberDto(null, roleId);
      case ROLE_IS_NULL:
        return new CdeAddProjectMemberDto(userId, null);
      case DEFAULT:
      default:
        return new CdeAddProjectMemberDto(userId, roleId);
    }
  }

  public CdeAddProjectMemberDto from(AbpIdentityUserDto responseUser,
      CdeProjectRoleDto responseProjectRole) {
    return new CdeAddProjectMemberDto(responseUser.getId(), responseProjectRole.getId());
  }
}