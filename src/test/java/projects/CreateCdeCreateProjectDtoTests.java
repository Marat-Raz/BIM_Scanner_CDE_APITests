package projects;

import static dtomodels.project.ProjectType.PROJECT_WITHOUT_DATA;
import static dtomodels.project.ProjectType.PROJECT_WITHOUT_NAME;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import basetests.StartTests;
import dto.generated.CdeCreateProjectDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Api interface CDE")
@Feature("Раздел Projects(Проекты)")
@Story("Создание проекта")
public class CreateCdeCreateProjectDtoTests extends StartTests {

  CdeCreateProjectDto cdeCreateProjectDto;
/*
// todo создать проект со всеми параметрами
// todo проверить создание проекта!
// todo создать проект без необязательных полей
// todo создать проект с неверной авторизацией
// todo создать проект с не верными данными: не верные данные в обязательных полях - параметризованные тесты
// todo проверить длину строк параметров проекта - создать проекта с проверкой длины строки его параметров
 */

  @Test
  @DisplayName("Создать проект без названия, без обязательного поля name")
  public void createProjectWithoutNameTest() {
    cdeCreateProjectDto = projectFactory.createProject(PROJECT_WITHOUT_NAME);
    createProjectResponse = projectsClient.createProject(cdeCreateProjectDto);
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_UNPROCESSABLE_ENTITY, statusCode);
  }

  @Test
  @DisplayName("Создать проект, где все параметры null")
  public void createProjectWithNullTest() {
    cdeCreateProjectDto = projectFactory.createProject(PROJECT_WITHOUT_DATA);
    createProjectResponse = projectsClient.createProject(cdeCreateProjectDto);
    statusCode = extractStatusCode(createProjectResponse);

    assertEquals(SC_UNPROCESSABLE_ENTITY, statusCode);
  }

}
