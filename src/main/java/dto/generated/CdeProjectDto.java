package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.CdeUserDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeProjectDto {

    public String id;
    public String creationTime;
    public String creatorId;
    public String lastModificationTime;
    public String lastModifierId;
    public String name;
    public String description;
    public String completionTime;
    public String concurrencyStamp;
    public CdeUserDto owner;
    public CdeUserDto responsible;
}
