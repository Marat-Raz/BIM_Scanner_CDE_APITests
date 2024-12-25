package models.user;

public class UserCredentials {


  private String userName;
  private String email;

  public UserCredentials(String userName, String password) {
    this.userName = userName;
    this.email = password;
  }

  public String getUserName() {
    return userName;
  }

  public String getEmail() {
    return email;
  }

  public static UserCredentials from(User user) {
    return new UserCredentials(user.getUserName(), user.getEmail());
  }

}
