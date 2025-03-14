package models.user;

import static org.passay.CharacterCharacteristicsRule.ERROR_CODE;

import org.apache.commons.lang3.RandomStringUtils;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class UserFactory {

  private String userName = RandomStringUtils.randomAlphabetic(6, 256);
  private String emailAddress = RandomStringUtils.randomAlphabetic(3, 247) + "@mail.com";
  private String password = generatePassword(10, 2, 2, 2, 2);
  private String appName = RandomStringUtils.randomAlphabetic(8);

  public User createUser(UserType userType) {
    switch (userType) {
      case USER_WITHOUT_EMAIL:
        return new User(userName, null, password, appName);
      case USER_WITHOUT_PASSWORD:
        return new User(userName, emailAddress, null, appName);
      case USER_WITHOUT_NAME:
        return new User(null, emailAddress, password, appName);
      case NEW_USER:
        return new User("newUser" + userName, "newEmail" + emailAddress, "newPassword" + password,
            appName);
      default:
      case DEFAULT_USER:
        return new User(userName, emailAddress, password, appName);
    }
  }

  private String generatePassword(int length, int lowerCase, int upperCase,
      int digitalRuleNumber, int specialCharsNumber) {
    PasswordGenerator generator = new PasswordGenerator();
    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
    lowerCaseRule.setNumberOfCharacters(lowerCase);

    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
    upperCaseRule.setNumberOfCharacters(upperCase);

    CharacterData digitalChars = EnglishCharacterData.Digit;
    CharacterRule digitalRule = new CharacterRule(digitalChars);
    digitalRule.setNumberOfCharacters(digitalRuleNumber);

    CharacterData specialChars = new MyCharacterData();
    CharacterRule specialCharRule = new CharacterRule(specialChars);
    specialCharRule.setNumberOfCharacters(specialCharsNumber);

    String password = generator.generatePassword(length, specialCharRule, lowerCaseRule,
        upperCaseRule, digitalRule);
    return password;
  }

  private class MyCharacterData implements CharacterData {

    @Override
    public String getErrorCode() {
      return ERROR_CODE;
    }

    @Override
    public String getCharacters() {
      return "!@#$%^&*()_+?<>:;'";
    }
  }
}
