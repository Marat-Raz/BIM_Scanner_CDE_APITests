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
  protected static CdeCreateLabelDto cdeCreateLabelDto;
  protected static ArrayList<CdeCreateLabelDto> cdeCreateLabelDtos = new ArrayList<>();
  protected static ArrayList<CdeLabelDto> cdeLabelDtos = new ArrayList<>();
  protected static CdeLabelDto cdeLabelDto;
  protected static String labelId;
  protected static final int labelsCount = 3;

  @BeforeAll
  @Step("Создаем метку в проекте")
  public static void addLabelToProject() {
    for (int i = 0; i < labelsCount; i++) {
      cdeCreateLabelDto = new LabelFactory().createLabel(DEFAULT);
      cdeCreateLabelDtos.add(cdeCreateLabelDto);
    }
    for (CdeCreateLabelDto cdeCreateLabelDto : cdeCreateLabelDtos) {
      baseAddResponse = labelsClient.createLabelInProject(projectId, cdeCreateLabelDto);
      cdeLabelDto = baseAddResponse.extract().as(CdeLabelDto.class);
      cdeLabelDtos.add(cdeLabelDto);
    }
    labelId = cdeLabelDtos.get(0).getId();
  }

}
