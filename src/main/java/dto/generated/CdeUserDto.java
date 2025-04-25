package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CdeUserDto {

    private String id;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private Boolean isActive;
    private String creationTime;
}
