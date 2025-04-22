package dtomodels.projectmember;

public enum ProjectMemberType {

  MEMBER("Member"),
  OWNER("Owner");

  private final String projectMemberTypeName;

  ProjectMemberType(String projectMemberTypeName) {
    this.projectMemberTypeName = projectMemberTypeName;
  }

  @Override
  public String toString() {
    return projectMemberTypeName;
  }

  public static ProjectMemberType fromString(String value) {
    for (ProjectMemberType perm : values()) {
      if (perm.projectMemberTypeName.equals(value)) {
        return perm;
      }
    }
    throw new IllegalArgumentException("Unknown projectMemberType: " + value);
  }
}
