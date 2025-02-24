package models.user;
import static CommonConstants.APP_NAME;

public class UserCredentials {


  private String userName;
  private String emailAddress;
  private String password;
  private String appName;

  private UserCredentials(String userName, String emailAddress, String password, String appName) {
    this.userName = userName;
    this.emailAddress = emailAddress;
    this.password = password;
    this.appName = appName;
  }

  private String getUserName() {
    return userName;
  }

  private String getEmailAddress() {
    return emailAddress;
  }

  private String getPassword() {
    return password;
  }

  private String getAppName() {
    return appName;
  }

  public static UserCredentials from(User user) {
    return new UserCredentials(user.getUserName(), user.getEmail(), user.getPassword(), APP_NAME);
  }

}
