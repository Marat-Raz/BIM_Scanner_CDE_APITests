package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeProjectMemberDto {

    private String projectId;
    private String userId;
    private String creationTime;
    private String creatorId;
    private CdeProjectMemberType memberType;
    private CdeUserDto user;
    private CdeProjectMemberRoleDto role;
}
