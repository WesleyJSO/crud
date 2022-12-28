package br.com.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class Employee extends DomainEntity {

  private String name, role;

  @JsonIgnore
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "project_id")
  private Project project;

}
