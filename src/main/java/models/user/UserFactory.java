package models.user;

import static constants.CommonConstants.REAL_EMAIL;
import static org.passay.CharacterCharacteristicsRule.ERROR_CODE;

import org.apache.commons.lang3.RandomStringUtils;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class UserFactory {

  private final String NULL = null; // На данном этапе тестирования принимаем так
  private String userName = RandomStringUtils.randomAlphabetic(6, 248);
  private String name = NULL;
  private String surname = NULL;
  private String emailAddress = RandomStringUtils.randomAlphabetic(3, 247) + "@mail.com";
  private String phoneNumber = NULL;
  private boolean active = true; // пока делаем так, далее при необходимости будем менять
  private boolean lockoutEnabled = true;  // пока делаем так, далее при необходимости будем менять
  private String[] roleNames = null;   // пока для простоты делаем так, далее при необходимости будем менять
  private String password = generatePassword(10, 2, 2, 2, 2);

  public User createUser(UserType userType) {
    switch (userType) {
      case USER_WITHOUT_EMAIL:
        return new User(userName, name, surname, null, phoneNumber, active,
            lockoutEnabled, null, password);
      case USER_WITHOUT_PASSWORD:
        return new User(userName, name, surname, emailAddress, phoneNumber, active,
            lockoutEnabled, null, null);
      case USER_WITHOUT_USERNAME:
        return new User(null, name, surname, emailAddress, phoneNumber, active,
            lockoutEnabled, null, null);
      case NEW_USER:
        return new User("newUser_" + RandomStringUtils.randomAlphabetic(0, 247),
            RandomStringUtils.randomAlphabetic(0, 64), RandomStringUtils.randomAlphabetic(0, 64),
            "newEmail" + RandomStringUtils.randomAlphabetic(3, 239) + "@mail.com",
            phoneNumber, active, lockoutEnabled, roleNames,
            "newPassword" + generatePassword(10, 2, 2, 2, 2));
      case USER_WITH_REAL_E_MAIL:
        return new User(userName, name, surname, REAL_EMAIL, phoneNumber, active,
            lockoutEnabled, roleNames, password);
      default:
      case DEFAULT_USER:
        return new User(userName, name, surname, emailAddress, phoneNumber, active,
            lockoutEnabled, roleNames, password);
    }
  }

  private String generatePassword(int length, int lowerCase, int upperCase,
      int digitalRuleNumber, int specialCharsNumber) {
    // todo заменить на свою реализацию включающую буквы кириллицы, латиницы (+ узбекские)
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

    CharacterData specialChars = new SpecialCharacterData();
    CharacterRule specialCharRule = new CharacterRule(specialChars);
    specialCharRule.setNumberOfCharacters(specialCharsNumber);

    String password = generator.generatePassword(length, specialCharRule, lowerCaseRule,
        upperCaseRule, digitalRule);
    return password;
  }

  private class SpecialCharacterData implements CharacterData {

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
