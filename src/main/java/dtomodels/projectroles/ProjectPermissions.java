package dtomodels.projectroles;

public enum ProjectPermissions {

  PROJECT_UPDATE("Project.Update"),
  PROJECT_UPDATE_RESPONSIBLE("Project.UpdateResponsible"),

  PROJECT_ROLE_CREATE("ProjectRole.Create"),
  PROJECT_ROLE_UPDATE("ProjectRole.Update"),
  PROJECT_ROLE_UPDATE_PERMISSIONS("ProjectRole.UpdatePermissions"),
  PROJECT_ROLE_DELETE("ProjectRole.Delete"),

  PROJECT_MEMBER_CREATE("ProjectMember.Create"),
  PROJECT_MEMBER_MANAGE_ROLES("ProjectMember.ManageRoles"),
  PROJECT_MEMBER_DELETE("ProjectMember.Delete"),

  CUSTOM_FIELD_CREATE("CustomField.Create"),
  CUSTOM_FIELD_UPDATE("CustomField.Update"),

  LABEL_CREATE("Label.Create"),
  LABEL_UPDATE("Label.Update"),
  LABEL_DELETE("Label.Delete"),

  TOPIC_BOARD_GROUP_CREATE("TopicBoardGroup.Create"),
  TOPIC_BOARD_GROUP_UPDATE("TopicBoardGroup.Update"),
  TOPIC_BOARD_GROUP_DELETE("TopicBoardGroup.Delete"),

  TOPIC_BOARD_CREATE("TopicBoard.Create"),
  TOPIC_BOARD_UPDATE("TopicBoard.Update"),
  TOPIC_BOARD_DELETE("TopicBoard.Delete"),

  TOPIC_CREATE("Topic.Create"),
  TOPIC_UPDATE("Topic.Update"),
  TOPIC_DELETE("Topic.Delete"),
  TOPIC_COMMENT_UPDATE_FOREIGN("TopicComment.UpdateForeign"),

  MODEL_CREATE("Model.Create"),
  MODEL_UPDATE("Model.Update"),
  MODEL_DELETE("Model.Delete");

  private final String permissionName;

  ProjectPermissions(String permissionName) {
    this.permissionName = permissionName;
  }

  @Override
  public String toString() {
    return permissionName;
  }

  public static ProjectPermissions fromString(String value) {
    for (ProjectPermissions perm : values()) {
      if (perm.permissionName.equals(value)) {
        return perm;
      }
    }
    throw new IllegalArgumentException("Unknown permission: " + value);
  }
}
