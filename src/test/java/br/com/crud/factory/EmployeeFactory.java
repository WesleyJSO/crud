package br.com.crud.factory;

import br.com.crud.entity.Employee;

public class EmployeeFactory {

  public Employee getDefault() {
    var employee = new Employee();
    employee.setRole("role");
    employee.setName("name");
    return employee;
  }
}
