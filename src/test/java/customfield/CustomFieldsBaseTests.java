package customfield;

import static dto.generated.CdeCustomFieldType.ENUMERATION;

import basetests.StartTests;
import client.CustomFieldsClient;
import dto.generated.CdeCreateCustomFieldDto;
import dto.generated.CdeCustomFieldDto;
import dtomodels.customfields.CustomFieldFactory;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;

public class CustomFieldsBaseTests extends StartTests {

  protected static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  protected static ValidatableResponse addResponse;
  protected static CdeCreateCustomFieldDto createCustomField;
  protected static ArrayList<CdeCustomFieldDto> customFieldArrayList = new ArrayList<>();
  protected static int customFieldsCount = 3;
  protected static String customFieldId;

  @BeforeAll
  public static void addAnyCustomFieldsToProject() {
    for (int i = 0; i < customFieldsCount; i++) {
      createCustomField = new CustomFieldFactory().createCustomField(ENUMERATION);
      addResponse = customFieldsClient.addNewCustomFieldToProject(projectId,
          createCustomField);
      CdeCustomFieldDto cdeCustomFieldDto = addResponse.extract().as(CdeCustomFieldDto.class);
      customFieldArrayList.add(cdeCustomFieldDto);
    }
    customFieldId = customFieldArrayList.get(0).getId();
  }

}
