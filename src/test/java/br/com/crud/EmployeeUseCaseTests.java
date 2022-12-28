package br.com.crud;

import br.com.crud.UseCase.EmployeeUseCases;
import br.com.crud.factory.EmployeeFactory;
import br.com.crud.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeUseCaseTests {

  private final EmployeeFactory employeeFactory = new EmployeeFactory();
  @Mock
  private EmployeeRepository employeeRepository;
  @InjectMocks
  private EmployeeUseCases employeeUseCases;

  @Test
  @DisplayName("Add employee")
  void shouldCreate() {

    var employee = employeeFactory.getDefault();

    employeeUseCases.saveEmployee(employee);

    verify(employeeRepository, times(1)).save(employee);
  }

  @Test
  @DisplayName("Error message when name is null")
  void shouldNotCreateWhenNameIsNull() {

    var employee = employeeFactory.getDefault();
    employee.setName(null);

    var result = employeeUseCases.saveEmployee(employee);

    assertTrue(result.isError());
    assertEquals("Nome do funcionario deve ser informado\n", result.getMessage());
    verify(employeeRepository, times(0)).save(employee);
  }

  @Test
  @DisplayName("Error message when role is null")
  void shouldNotCreateWhenRoleIsNull() {

    var employee = employeeFactory.getDefault();
    employee.setRole(null);

    var result = employeeUseCases.saveEmployee(employee);

    assertTrue(result.isError());
    assertTrue(result.getMessage()
      .contains("Cargo do funcionario deve ser informado"));
    verify(employeeRepository, times(0)).save(employee);
  }
}
