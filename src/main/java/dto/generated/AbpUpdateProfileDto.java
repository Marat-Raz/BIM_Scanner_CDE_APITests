package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpUpdateProfileDto {

    public String userName;
    public String email;
    public String name;
    public String surname;
    public String phoneNumber;
    public String concurrencyStamp;
}
