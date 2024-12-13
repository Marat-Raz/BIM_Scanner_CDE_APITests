package models.user;

public class UserCredentials {


  private String userName;
  private String password;

  public UserCredentials(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public static UserCredentials from(User user) {
    return new UserCredentials(user.getUserName(), user.getPassword());
  }

}
