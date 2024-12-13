package usermodel;

public class UserCredentials {


  private String login;
  private String password;

  public UserCredentials(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public static UserCredentials from(User user) {
    return new UserCredentials(user.getLogin(), user.getPassword());
  }

}
