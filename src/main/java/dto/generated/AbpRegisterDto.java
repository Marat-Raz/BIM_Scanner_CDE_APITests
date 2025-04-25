package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpRegisterDto {

  private Object extraProperties;
  private String userName;
  private String emailAddress;
  private String password;
  private String appName;


  public static AbpRegisterDto from(AbpIdentityUserCreateDto user) {
    return new AbpRegisterDto(
        user.getUserName(),
        user.getEmail(),
        user.getPassword(),
        APP_NAME);
  }

}
