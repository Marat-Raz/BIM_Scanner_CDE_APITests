package models;

import java.util.Random;

public class RandomWord {

  private static final String LATIN_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String CYRILLIC_CHARS =
      "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
  private static final String UZBEK_CHARS = "ўқғҳЎҚҒҲ";
  private static final String NUMBERS = "0123456789";
  private static final String SPECIAL_CHARS = "!@#$%^&*()_+-={}[]|;:',.<>/?`~«»";

  private static final String ALL_CHARS =
      LATIN_CHARS + CYRILLIC_CHARS + UZBEK_CHARS + SPECIAL_CHARS + NUMBERS;

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

}