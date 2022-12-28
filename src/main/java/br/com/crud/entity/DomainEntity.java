package br.com.crud.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Data;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

@Data
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class DomainEntity {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Integer id;
}
