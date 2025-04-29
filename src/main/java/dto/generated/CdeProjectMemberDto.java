package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeProjectMemberRoleDto;
import dto.generated.CdeProjectMemberType;
import dto.generated.CdeUserDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeProjectMemberDto {

    public String projectId;
    public String userId;
    public String creationTime;
    public String creatorId;
    public CdeProjectMemberType memberType;
    public CdeUserDto user;
    public CdeProjectMemberRoleDto role;
}
