package usermodel;

import static generaldatatests.GeneralDataTests.BRIO_CLOUD_PASSWORD;
import static generaldatatests.GeneralDataTests.BRIO_CLOUD_USERNAME;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

  public static String name = RandomStringUtils.randomAlphabetic(8);
  public static String login = RandomStringUtils.randomAlphabetic(8);
  public static String password = RandomStringUtils.randomAlphabetic(8);

  public static User getUser() {
    return new User(name, login, password);
  }

  public static User getFixedUserForAutotests() {
    return new User(BRIO_CLOUD_USERNAME, BRIO_CLOUD_USERNAME, BRIO_CLOUD_PASSWORD);
  }

  public static User getNewUser() {
    return new User(name + 1, login + 2, password + 3);
  }

}
