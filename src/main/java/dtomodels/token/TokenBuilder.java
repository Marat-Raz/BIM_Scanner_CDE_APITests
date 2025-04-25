package dtomodels.token;

import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpIdentityUserCreateDto;
import static constants.CommonConstants.ADMIN;
import static constants.CommonConstants.ADMIN_P;
import static constants.CommonConstants.GRANT_TYPE;

@Getter
@Setter
public class TokenBuilder {

  private static String grantType;
  private static String username;
  private static String password;
  private static String scope = "openid profile CDE email phone";
  private static String clientId = "CDE_TestClient";
  private static String clientSecret = "a7af4e9397dc457cb99672d3cdc221c0";

  public static RequestToken getTokenForAdminUser() {
    return RequestToken.builder()
        .grantType(GRANT_TYPE)
        .username(ADMIN)
        .password(ADMIN_P)
        .scope(scope)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }

  public static RequestToken getTokenForUser(AbpIdentityUserCreateDto abpIdentityUserCreateDto) {
    return RequestToken.builder()
        .grantType(grantType)
        .username(abpIdentityUserCreateDto.getUserName())
        .password(abpIdentityUserCreateDto.getPassword())
        .scope(scope)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }

}
