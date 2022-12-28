package br.com.crud.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.stream.Collectors.joining;

@Getter
@Setter
@Entity
@Table(name = "PROJECTS")
public class Project extends DomainEntity {

  private String name;
  private LocalDate startDate;
  private String projectManager;
  private LocalDate endDate;
  private LocalDate realEndDate;
  private Double budgetTotal;
  private String description;

  @Enumerated(EnumType.STRING)
  private Status status;

  @OneToMany(fetch = EAGER, mappedBy = "project", cascade = ALL)
  private List<Employee> employees = new ArrayList<>();

  public void setEmployees(List<Employee> employees) {
    employees.forEach(e -> e.setProject(this));
    this.employees = employees;
  }

  public String getEmployeesNames() {
    return employees.stream()
      .map(Employee::getName)
      .collect(joining(", "));
  }
}
