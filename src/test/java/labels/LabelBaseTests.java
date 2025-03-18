package labels;

import static models.labels.LabelType.DEFAULT;

import basetests.StartTests;
import client.LabelsClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import models.labels.Label;
import models.labels.LabelFactory;
import models.labels.ResponseLabel;
import org.junit.jupiter.api.BeforeAll;

public class LabelBaseTests extends StartTests {

  protected static LabelsClient labelsClient = new LabelsClient();
  protected static ValidatableResponse baseAddResponse;
  protected static Label label;
  protected static ArrayList<Label> labels = new ArrayList<>();
  protected static ArrayList<ResponseLabel> responseLabels = new ArrayList<>();
  protected static ResponseLabel responseLabel;
  protected static String labelId;
  protected static final int labelsCount = 3;

  @BeforeAll
  @Step("Создаем метку в проекте")
  public static void addLabelToProject() {
    for (int i = 0; i < labelsCount; i++) {
      label = new LabelFactory().createLabel(DEFAULT);
      labels.add(label);
    }
    for (Label label : labels) {
      baseAddResponse = labelsClient.createLabelInProject(projectId, label);
      responseLabel = baseAddResponse.extract().as(ResponseLabel.class);
      responseLabels.add(responseLabel);
    }
    labelId = responseLabels.get(0).getId();
  }
  
}
