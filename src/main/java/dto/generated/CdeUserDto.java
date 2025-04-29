package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CdeUserDto {

    public String id;
    public String userName;
    public String name;
    public String surname;
    public String email;
    public Boolean isActive;
    public String creationTime;
}
