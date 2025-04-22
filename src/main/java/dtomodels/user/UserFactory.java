package dtomodels.user;

import static constants.CommonConstants.REAL_EMAIL;

import dtomodels.RandomWord;

public class UserFactory {

  private final String NULL = null; // На данном этапе тестирования принимаем так
  private String userName = RandomWord.randomLatinAndNumberCharacters(6, 248);
  private String name = NULL;
  private String surname = NULL;
  private String emailAddress = RandomWord.randomLatinAndNumberCharacters(3, 247) + "@mail.com";
  private String phoneNumber = NULL;
  private boolean active = true; // пока делаем так, далее при необходимости будем менять
  private boolean lockoutEnabled = true;  // пока делаем так, далее при необходимости будем менять
  private String[] roleNames = null;   // пока для простоты делаем так, далее при необходимости будем менять
  private String password = RandomWord.generatePassword(8, 128);
  // todo нужно обязательность символов указывать: цифры, прописные, строчные, спецсимволы

  public AbpIdentityUserCreateDto createUser(UserType userType) {
    switch (userType) {
      case USER_WITHOUT_EMAIL:
        return new AbpIdentityUserCreateDto(userName, name, surname, null, phoneNumber, active,
            lockoutEnabled, null, password);
      case USER_WITHOUT_PASSWORD:
        return new AbpIdentityUserCreateDto(userName, name, surname, emailAddress, phoneNumber, active,
            lockoutEnabled, null, null);
      case USER_WITHOUT_USERNAME:
        return new AbpIdentityUserCreateDto(null, name, surname, emailAddress, phoneNumber, active,
            lockoutEnabled, null, null);
      case NEW_USER:
        return new AbpIdentityUserCreateDto("newUser_" + RandomWord.randomLatinCharacters(0, 247),
            RandomWord.randomLatinCharacters(0, 64), RandomWord.randomAllCharacters(0, 64),
            "newEmail" + RandomWord.randomLatinAndNumberCharacters(3, 239) + "@mail.com",
            phoneNumber, active, lockoutEnabled, roleNames,
            "newPassword" + RandomWord.randomAllCharacters(8, 117));
      case USER_WITH_REAL_E_MAIL:
        return new AbpIdentityUserCreateDto(userName, name, surname, REAL_EMAIL, phoneNumber, active,
            lockoutEnabled, roleNames, password);
      case DEFAULT_USER:
      default:
        return new AbpIdentityUserCreateDto(userName, name, surname, emailAddress, phoneNumber, active,
            lockoutEnabled, roleNames, password);
    }
  }

}
