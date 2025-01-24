package models.user;

public class UserCredentials {


  private String userName;
  private String email;

  public UserCredentials(String userName, String email) {
    this.userName = userName;
    this.email = email;
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
