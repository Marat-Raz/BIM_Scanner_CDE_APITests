package dtomodels.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonCreator;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobStatus {
  WAITING("Waiting"),
  PROCESSING("Processing"),
  ERROR("Error"),
  SUCCESS("Success");

  private final String jsonName;

  @JsonValue
  public String getJsonName() {
    return jsonName;
  }

  @JsonCreator // Десериализация
  public static JobStatus fromString(String value) {
    return Arrays.stream(values())
        .filter(v -> v.jsonName.equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown status: " + value));
  }
}

