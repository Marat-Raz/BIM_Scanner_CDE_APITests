package models.user;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

  public static String userName = RandomStringUtils.randomAlphabetic(6, 256);
  public static String emailAddress = RandomStringUtils.randomAlphabetic(3, 247) + "@mail.com";
  public static String password = generateCommonLangPassword(8, 33, 122);
  public static String appName = RandomStringUtils.randomAlphabetic(8);

  public static User getUser() {
    return new User(userName, emailAddress, password, appName);
  }


  public static User getNewUser() {
    return new User(userName + 1, 2 + emailAddress, password + 3, appName + 4);
  }

  public static String generateCommonLangPassword(int count, int start, int end) {
    String upperCaseLetters = RandomStringUtils.random(count, start, end, true, true);
    String lowerCaseLetters = RandomStringUtils.random(count, start, end, true, true);
    String numbs = RandomStringUtils.randomNumeric(count);
    String specialChar = RandomStringUtils.random(count, start, end, false, false);
    String totalChars = RandomStringUtils.randomAlphanumeric(count);
    String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
        .concat(numbs)
        .concat(specialChar)
        .concat(totalChars);
    List<Character> pwdChars = combinedChars.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toList());
    Collections.shuffle(pwdChars);
    String password = pwdChars.stream()
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
        .toString();
    return password;
  }
}
