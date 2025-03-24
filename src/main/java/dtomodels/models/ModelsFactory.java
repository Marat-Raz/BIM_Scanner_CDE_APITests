package dtomodels.models;

import org.apache.commons.lang3.RandomStringUtils;

public class ModelsFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);

  public Model createNameForModel(ModelType modelType) {
    switch (modelType) {
      case NAME_IS_NULL:
        return new Model(null);
      case DEFAULT:
      default:
        return new Model(name);
    }
  }
  // todo добавить другие типы Model для негативных тестов
}
