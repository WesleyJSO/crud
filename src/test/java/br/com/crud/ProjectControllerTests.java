package br.com.crud;

import br.com.crud.UseCase.ProjectUseCases;
import br.com.crud.factory.ProjectFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class ProjectControllerTests {

  private final String uri = "/api/employees";
  private final ProjectFactory projectFactory;
  private final ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ProjectUseCases projectUseCases;

  public ProjectControllerTests() {
    projectFactory = new ProjectFactory();
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  @DisplayName("Should create a project")
  @Transactional
  @Rollback
  void shouldCreateProject() throws Exception {

    var result = projectUseCases.findAll();

    var project = projectFactory.getDefault();

    mockMvc.perform(post(uri, project)
      .content(objectMapper.writeValueAsString(project)));

    var resultNew = projectUseCases.findAll();

    assertThat(resultNew.getResults("projects")
      .size() == (result.getResults("projects")
      .size() + 1));
  }
}
