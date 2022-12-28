package br.com.crud.factory;

import br.com.crud.entity.Project;
import br.com.crud.entity.Status;

import java.time.LocalDate;
import java.util.List;

public class ProjectFactory {

  private final EmployeeFactory employeeFactory = new EmployeeFactory();

  public Project getDefault() {
    var project = new Project();
    project.setName("Name");
    project.setStartDate(LocalDate.now());
    project.setProjectManager("ProjectManager");
    project.setEndDate(LocalDate.now());
    project.setRealEndDate(LocalDate.now());
    project.setBudgetTotal(1000.0);
    project.setDescription("Description");
    project.setStatus(Status.IN_ANALYSIS);
    project.setEmployees(List.of(employeeFactory.getDefault()));
    return project;
  }
}
