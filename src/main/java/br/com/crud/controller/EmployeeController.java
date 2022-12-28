package br.com.crud.controller;

import br.com.crud.UseCase.EmployeeUseCases;
import br.com.crud.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/employees")
public record EmployeeController(EmployeeUseCases employeeUseCases) {

  @PostMapping
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

    var result = employeeUseCases.saveEmployee(employee);

    if (result.isError()) {
      return ResponseEntity.unprocessableEntity()
        .build();
    }
    var saved = result.getResult("employee");
    return ResponseEntity
      .created(URI.create("http://localhost:9999/api/employees/" + saved.getId()))
      .build();
  }
}
