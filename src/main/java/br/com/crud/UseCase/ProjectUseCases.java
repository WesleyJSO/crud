package br.com.crud.UseCase;

import br.com.crud.Result;
import br.com.crud.entity.Project;
import br.com.crud.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.crud.entity.Status.*;
import static java.lang.String.format;

@Component
public record ProjectUseCases(ProjectRepository projectRepository) {

  public Result<Project> findAll() {
    Result<Project> result = new Result<>();
    result.setResults("projects", projectRepository.findAll());
    result.setSuccess();
    return result;
  }

  public Result<Project> save(Project project) {
    Result<Project> result = new Result<>();

    var message = validate(project);
    if (!message.isBlank()) {
      result.setError(message);
      return result;
    }

    result.setResults("projeto", projectRepository.save(project));
    result.setSuccess();
    return result;
  }

  public Result<Project> update(Project project) {
    Result<Project> result = new Result<>();
    if (project == null || project.getId() == null) {
      result.setError("Id do projeto nao pode ser nulo");
      return result;
    }
    var message = validate(project);
    if (!message.isBlank()) {
      result.setError(message);
      return result;
    }
    this.findById(project.getId())
      .getResult("project");

    result.setResults("project", projectRepository.save(project));
    result.setSuccess();

    return result;
  }

  public Result<Project> findById(Integer id) {

    Result<Project> result = new Result<>();

    var oProject = projectRepository.findById(id);

    if (oProject.isEmpty()) {
      result.setError(format("Projeto com id %d nao encontrado", id));
      return result;
    }

    result.setSuccess();
    result.setResults("project", oProject.orElseThrow());

    return result;
  }

  public Result<Project> delete(Integer id) {

    var dbProject = this.findById(id);
    if (dbProject.isError()) {
      return dbProject;
    }

    Result<Project> result = new Result<>();
    var nonDeletableStatus = List.of(STARTED, IN_PROGRESS, DONE);

    var project = dbProject.getResult("project");
    if (nonDeletableStatus.contains(project.getStatus())) {
      result.setError(format(
        "Projeto %s nao pode ser excluido no status atual (%s)",
        project.getName(), project.getStatus()
          .getValue()
      ));
      return result;
    }

    projectRepository.delete(project);
    result.setResults("project", project);
    result.setSuccess(format("Projeto %s excluido", project.getName()));
    return result;
  }

  private String validate(Project project) {
    String message = "";
    if (project.getName() == null || project.getName()
      .isBlank()) {
      message += "Nome do projeto nao pode ser nulo\n";
    }
    if (project.getStartDate() == null) {
      message += "Data de inicio do projeto nao pode ser nulo\n";
    }
    if (project.getProjectManager() == null || project.getProjectManager()
      .isBlank()) {
      message += "Gerente do projeto nao pode ser nulo\n";
    }
    if (project.getEndDate() == null) {
      message += "Data final do projeto nao pode ser nulo\n";
    }
    if (project.getRealEndDate() == null) {
      message += "Data final real do projeto nao pode ser nulo\n";
    }
    if (project.getBudgetTotal() == null || project.getBudgetTotal() <= 0) {
      message += "Orcamento do projeto nao pode ser nulo ou negativo\n";
    }
    if (project.getDescription() == null || project.getDescription()
      .isBlank()) {
      message += "Descricao do projeto nao pode ser nulo\n";
    }
    if (project.getStatus() == null) {
      message += "Status do projeto nao pode ser nulo\n";
    }
    if (project.getEmployees() == null || project.getEmployees()
      .isEmpty()) {
      message += "Lista de funcionarios do projeto nao pode ser nulo\n";
    }
    return message;
  }
}
