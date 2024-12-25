package models.user;

import static org.passay.CharacterCharacteristicsRule.ERROR_CODE;

import org.apache.commons.lang3.RandomStringUtils;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class UserGenerator {

  public String userName = RandomStringUtils.randomAlphabetic(6, 256);
  public String emailAddress = RandomStringUtils.randomAlphabetic(3, 247) + "@mail.com";
  public String password = generatePassayPassword(10, 2, 2, 2, 2);
  public String appName = RandomStringUtils.randomAlphabetic(8);

  public User getUser() {
    return new User(userName, emailAddress, password, appName);
  }


  public User getNewUser() {
    return new User(userName + 1, 2 + emailAddress, password + 3, appName + 4);
  }

  public String generatePassayPassword(int length, int lowerCase, int upperCase,
      int digitRuleNumb, int specialCharsNumb) {
    PasswordGenerator gen = new PasswordGenerator();
    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
    lowerCaseRule.setNumberOfCharacters(lowerCase);

    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
    upperCaseRule.setNumberOfCharacters(upperCase);

    CharacterData digitChars = EnglishCharacterData.Digit;
    CharacterRule digitRule = new CharacterRule(digitChars);
    digitRule.setNumberOfCharacters(digitRuleNumb);

    CharacterData specialChars = new CharacterData() {
      public String getErrorCode() {
        return ERROR_CODE;
      }

      public String getCharacters() {
        return "!@#$%^&*()_+";
      }
    };
    CharacterRule splCharRule = new CharacterRule(specialChars);
    splCharRule.setNumberOfCharacters(specialCharsNumb);

    String password = gen.generatePassword(length, splCharRule, lowerCaseRule,
        upperCaseRule, digitRule);
    return password;
  }

  public User getUserWithoutEmail() {
    return new User(userName,null, password, appName);
  }

  public User getUserWithoutPassword() {
    return new User(userName, emailAddress, null, appName);
  }

  public static User getUserWithoutUserName() {
    return new User(null, emailAddress, password, appName);
  }
}
