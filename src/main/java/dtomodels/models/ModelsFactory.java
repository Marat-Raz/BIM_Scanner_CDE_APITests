package dtomodels.models;

import dto.generated.CdeCreateOrUpdateModelDto;
import org.apache.commons.lang3.RandomStringUtils;

public class ModelsFactory {

  private String name = RandomStringUtils.randomAlphabetic(1, 256);

  public CdeCreateOrUpdateModelDto createNameForModel(ModelType modelType) {
    switch (modelType) {
      case NAME_IS_NULL:
        return new CdeCreateOrUpdateModelDto(null);
      case DEFAULT:
      default:
        return new CdeCreateOrUpdateModelDto(name);
    }
  }
  // todo добавить другие типы Model для негативных тестов
}
