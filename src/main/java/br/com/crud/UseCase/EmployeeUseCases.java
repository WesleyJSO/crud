package br.com.crud.UseCase;

import br.com.crud.Result;
import br.com.crud.entity.Employee;
import br.com.crud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public record EmployeeUseCases(EmployeeRepository employeeRepository) {

  public Result<Employee> saveEmployee(Employee employee) {

    Result<Employee> result = new Result<>();
    String message = "";
    if (employee.getName() == null) {
      message += "Nome do funcionario deve ser informado\n";
    }
    if (employee.getRole() == null) {
      message += "Cargo do funcionario deve ser informado";
    }
    if (!message.isEmpty()) {
      result.setError(message);
      result.setResults("employee", employee);
      return result;
    }
    var saved = employeeRepository.save(employee);
    result.setResults("employee", saved);
    result.setSuccess();
    return result;
  }

  public Result<Employee> findByRole(String role) {
    Result<Employee> result = new Result<>();
    result.setResults("employees", employeeRepository.findByRole(role));
    result.setSuccess();
    return result;
  }
}
