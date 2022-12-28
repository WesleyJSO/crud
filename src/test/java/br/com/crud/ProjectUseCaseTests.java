package br.com.crud;

import br.com.crud.UseCase.ProjectUseCases;
import br.com.crud.factory.ProjectFactory;
import br.com.crud.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.crud.entity.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectUseCaseTests {

  private final ProjectFactory projectFactory = new ProjectFactory();
  @Mock
  private ProjectRepository projectRepository;
  @InjectMocks
  private ProjectUseCases projectUseCases;

  @Test
  @DisplayName("Create project")
  void shouldCreateProject() {

    var project = projectFactory.getDefault();

    projectUseCases.save(project);

    verify(projectRepository, times(1)).save(project);
  }

  @Test
  @DisplayName("Error message if name is missing")
  void shouldReturnErrorMessageWhenNameIsEmpty() {

    var project = projectFactory.getDefault();

    project.setName(null);

    var result = projectUseCases.save(project);

    assertTrue(result.isError());
    assertEquals("Nome do projeto nao pode ser nulo\n", result.getMessage());
    verify(projectRepository, times(0)).save(project);
  }

  @Test
  @DisplayName("Error message if status is missing")
  void shouldReturnErrorMessageWhenStatusIsEmpty() {

    var project = projectFactory.getDefault();

    project.setStatus(null);

    var result = projectUseCases.save(project);

    assertTrue(result.isError());
    assertEquals("Status do projeto nao pode ser nulo\n", result.getMessage());
    verify(projectRepository, times(0)).save(project);
  }

  @Test
  @DisplayName("Error message if end date is missing")
  void shouldReturnErrorMessageWhenEndDateIsEmpty() {

    var project = projectFactory.getDefault();

    project.setEndDate(null);

    var result = projectUseCases.save(project);

    assertTrue(result.isError());
    assertEquals("Data final do projeto nao pode ser nulo\n", result.getMessage());
    verify(projectRepository, times(0)).save(project);
  }

  @Test
  @DisplayName("Error message if employees list is empty")
  void shouldReturnErrorMessageWhenEmployeesListIsEmpty() {

    var project = projectFactory.getDefault();

    project.setEmployees(new ArrayList<>());

    var result = projectUseCases.save(project);

    assertTrue(result.isError());
    assertEquals("Lista de funcionarios do projeto nao pode ser nulo\n", result.getMessage());
    verify(projectRepository, times(0)).save(project);
  }

  @Test
  @DisplayName("Should update project")
  void shouldUpdate() {

    var project = projectFactory.getDefault();
    project.setId(1);

    var result = projectUseCases.save(project);

    verify(projectRepository, times(1)).save(project);
  }

  @Test
  @DisplayName("Should return error message if id is null or empty")
  void shouldReturnErrorMessageWhenIdIsEmpty() {

    var project = projectFactory.getDefault();

    var result = projectUseCases.update(project);

    assertTrue(result.isError());
    assertEquals("Id do projeto nao pode ser nulo", result.getMessage());
    verify(projectRepository, times(0)).save(project);
  }

  @Test
  @DisplayName("Should delete a existing project")
  void shouldDeleteProject() {

    var project = projectFactory.getDefault();
    project.setId(1);

    when(projectRepository.findById(project.getId()))
      .thenReturn(Optional.of(project));

    var result = projectUseCases.delete(project.getId());

    assertTrue(result.isSuccess());
    assertEquals(String.format("Projeto %s excluido", project.getName()), result.getMessage());
    verify(projectRepository, times(1)).findById(project.getId());
    verify(projectRepository, times(1)).delete(project);
  }

  @Test
  @DisplayName("Should not delete project when in a non deletable status")
  void shouldNotDeleteProjectWhenInNonDeletableStatus() {

    var project = projectFactory.getDefault();
    var nonDeletableStatus = List.of(STARTED, IN_PROGRESS, DONE);
    project.setId(1);
    project.setStatus(nonDeletableStatus.stream()
      .findAny()
      .get());

    when(projectRepository.findById(project.getId()))
      .thenReturn(Optional.of(project));

    var result = projectUseCases.delete(project.getId());

    var message = String.format(
      "Projeto %s nao pode ser excluido no status atual (%s)",
      project.getName(), project.getStatus()
        .getValue()
    );

    assertTrue(result.isError());
    assertEquals(message, result.getMessage());
    verify(projectRepository, times(1)).findById(project.getId());
    verify(projectRepository, times(0)).delete(project);
  }

  @Test
  @DisplayName("Should not delete project when project is not found")
  void shouldNotDeleteProjectWhenProjectIsNotFound() {

    var project = projectFactory.getDefault();
    project.setId(1);

    when(projectRepository.findById(project.getId()))
      .thenReturn(Optional.empty());

    var result = projectUseCases.delete(project.getId());

    assertTrue(result.isError());
    assertEquals(String.format("Projeto com id %d nao encontrado", project.getId()), result.getMessage());
    verify(projectRepository, times(1)).findById(project.getId());
    verify(projectRepository, times(0)).delete(project);
  }
}
