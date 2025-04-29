package dto.helpers;

import static constants.CommonConstants.APP_NAME;

import dto.generated.*;
import java.util.ArrayList;

public class DtoConverter {

  public static AbpRegisterDto convertDto(AbpIdentityUserCreateDto user) {
    if (user == null) {
      return null;
    }
    return new AbpRegisterDto(
        user.getUserName(),
        user.getEmail(),
        user.getPassword(),
        APP_NAME);
  }

  public static CdeAddOrUpdateEnumerationCustomFieldItemDto
  convertDto(CdeEnumerationCustomFieldItemDto source) {
    if (source == null) {
      return null;
    }
    return new CdeAddOrUpdateEnumerationCustomFieldItemDto(
        source.getId(),
        source.getName(),
        source.getDisabled()
    );
  }

  public static CdeModifyTopicBoardCustomFieldDto
  convertDto(CdeTopicBoardCustomFieldDto source) {
    if (source == null) {
      return null;
    }

    return new CdeModifyTopicBoardCustomFieldDto(
        source.getId(),
        source.getIsEnabled(),
        source.getIsRequired(),
        source.getDefaultValue()
    );
  }

  public static CdeUpdateCustomFieldDto convertDto(CdeCustomFieldDto responseCustomField) {
    ArrayList<CdeAddOrUpdateEnumerationCustomFieldItemDto> enumerationItemsArray = new ArrayList<>();
    for (int i = 0; i < responseCustomField.getEnumerationItems().size(); i++) {
      enumerationItemsArray.add(DtoConverter
          .convertDto(responseCustomField.getEnumerationItems().get(i)));
    }

    return new CdeUpdateCustomFieldDto(
        responseCustomField.getName(),
        responseCustomField.getDescription(),
        responseCustomField.getArchived(),
        enumerationItemsArray
    );
  }

  public static CdeUpdateProjectDto convertDto(CdeProjectDto source) {
    if (source == null) {
      return null;
    }
    return new CdeUpdateProjectDto(
        source.getName(),
        source.getDescription(),
        source.getResponsible().getId(),
        source.getCompletionTime(),
        source.getConcurrencyStamp()
    );
  }

  public static CdeUpdateProjectRoleDto convertDto(CdeProjectRoleDto source) {
    if (source == null) {
      return null;
    }return new CdeUpdateProjectRoleDto(
        source.getName(),
        source.getIsDefault(),
        source.getConcurrencyStamp(),
        source.getPermissions()
    );}
}
