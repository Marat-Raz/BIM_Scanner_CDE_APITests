package labels;

import static dtomodels.labels.LabelType.DEFAULT;

import basetests.StartTests;
import client.LabelsClient;
import dto.generated.CdeCreateLabelDto;
import dtomodels.labels.LabelFactory;
import dto.generated.CdeLabelDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;

public class LabelBaseTests extends StartTests {

  protected static LabelsClient labelsClient = new LabelsClient();
  protected static ValidatableResponse baseAddResponse;
  protected static CdeCreateLabelDto createLabelDto;
  protected static ArrayList<CdeCreateLabelDto> cdeCreateLabels = new ArrayList<>();
  protected static ArrayList<CdeLabelDto> cdeLabels = new ArrayList<>();
  protected static CdeLabelDto cdeLabelDto;
  protected static String labelId;
  protected static final int labelsCount = 3;

  @BeforeAll
  @Step("Создаем метку в проекте")
  public static void addLabelToProject() {
    for (int i = 0; i < labelsCount; i++) {
      createLabelDto = new LabelFactory().createLabel(DEFAULT);
      cdeCreateLabels.add(createLabelDto);
    }
    for (CdeCreateLabelDto cdeCreateLabelDto : cdeCreateLabels) {
      baseAddResponse = labelsClient.createLabelInProject(projectId, cdeCreateLabelDto);
      cdeLabelDto = baseAddResponse.extract().as(CdeLabelDto.class);
      cdeLabels.add(cdeLabelDto);
    }
    labelId = cdeLabels.get(0).getId();
  }

}
