package models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomWord {

  private static final String LATIN_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String LATIN_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LATIN_CHARS = LATIN_LOWERCASE + LATIN_UPPERCASE;
  private static final String CYRILLIC_LOWERCASE = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
  private static final String CYRILLIC_UPPERCASE = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
  private static final String CYRILLIC_CHARS = CYRILLIC_LOWERCASE + CYRILLIC_UPPERCASE;
  private static final String UZBEK_CHARS = "ўқғҳЎҚҒҲ";
  private static final String DIGITS = "0123456789";
  private static final String SPECIAL_CHARS = "!@#$%^&*()_+-={}[]|;:',.<>/?`~«»";
  private static final String ALL_CHARS =
      LATIN_CHARS + CYRILLIC_CHARS + UZBEK_CHARS + SPECIAL_CHARS + DIGITS;
  private static final SecureRandom random = new SecureRandom();

  public static String generateWord(int minLength, int maxLength, String chars) {
    if (minLength > maxLength) {
      throw new IllegalArgumentException(
          "Минимальная длина должна быть меньше или равна максимальной");
    }
    Random random = new Random();
    int length = random.nextInt(maxLength - minLength + 1) + minLength;
    StringBuilder word = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(chars.length());
      word.append(chars.charAt(index));
    }
    return word.toString();
  }

  public static String randomOnlyFromUzbekCharacters(int minLength, int maxLength) {
    return generateWord(minLength, maxLength, UZBEK_CHARS);
  }

  public static String randomLatinCharacters(int minLength, int maxLength) {
    return generateWord(minLength, maxLength, LATIN_CHARS);
  }

  public static String randomCyrillicCharacters(int minLength, int maxLength) {
    return generateWord(minLength, maxLength, CYRILLIC_CHARS);
  }

  public static String randomSpecialCharacters(int minLength, int maxLength) {
    return generateWord(minLength, maxLength, SPECIAL_CHARS);
  }

  public static String randomAllCharacters(int minLength, int maxLength) {
    return generateWord(minLength, maxLength, ALL_CHARS);
  }

  public static String randomLatinAndNumberCharacters(int minLength, int maxLength) {
    return generateWord(minLength, maxLength, LATIN_CHARS + DIGITS);
  }

  public static String generatePassword(int minLength, int maxLength) {
    if (minLength < 6) {
      throw new IllegalArgumentException("Минимальная длина пароля должна быть не менее 6 символов");
    }
    if (maxLength < minLength) {
      throw new IllegalArgumentException("Максимальная длина должна быть больше или "
          + "равна минимальной длине.");
    }

    int length = minLength + random.nextInt(maxLength - minLength + 1);
    List<Character> passwordChars = new ArrayList<>();
    passwordChars.add(getRandomChar(LATIN_LOWERCASE));
    passwordChars.add(getRandomChar(LATIN_UPPERCASE));
    passwordChars.add(getRandomChar(DIGITS));
    passwordChars.add(getRandomChar(SPECIAL_CHARS));

    for (int i = 4; i < length; i++) {
      passwordChars.add(getRandomChar(ALL_CHARS));
    }

    Collections.shuffle(passwordChars, random);

    StringBuilder password = new StringBuilder();
    for (char c : passwordChars) {
      password.append(c);
    }

    return password.toString();
  }

  private static char getRandomChar(String charSet) {
    int randomIndex = random.nextInt(charSet.length());
    return charSet.charAt(randomIndex);
  }

}