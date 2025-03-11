package customfield;

import static models.customfields.CustomFieldType.ENUMERATION;

import basetests.StartTests;
import client.CustomFieldsClient;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.customfields.CustomField;
import models.customfields.CustomFieldFactory;
import models.customfields.ResponseCustomField;
import org.junit.jupiter.api.BeforeAll;

public class CustomFieldsBaseTests extends StartTests {

  protected static CustomFieldsClient customFieldsClient = new CustomFieldsClient();
  protected static ValidatableResponse addResponse;
  protected static CustomField customField;
  protected static ArrayList<ResponseCustomField> responseCustomFieldArrayList = new ArrayList<>();
  protected static int customFieldsCount = 3;
  protected static String customFieldId;

  @BeforeAll
  public static void addAnyCustomFieldsToProject() {
    for (int i = 0; i < customFieldsCount; i++) {
      customField = new CustomFieldFactory().createCustomField(ENUMERATION);
      addResponse = customFieldsClient.addNewCustomFieldToProject(projectId, customField);
      ResponseCustomField responseCustomField = addResponse.extract().as(ResponseCustomField.class);
      responseCustomFieldArrayList.add(responseCustomField);
    }
    customFieldId = responseCustomFieldArrayList.get(0).getId();
  }

}
