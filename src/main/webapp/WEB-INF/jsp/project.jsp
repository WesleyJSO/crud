<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Reports</title>
    </head>
    <body>
      <div class="alert alert-primary" role="alert">
        ${message}
      </div>
      <form:form action="/projects" method="POST" modelAttribute="project">
        <div class="form-group">
          <label for="name">Nome</label>
          <form:input id="name" value="${project.name}" type="text" path="name" class="form-control" placeholder="Name" />
        </div>
        <div class="form-group">
          <label for="startDate">Data de início</label>
          <form:input id="startDate"  value="${project.startDate}" type="date" path="startDate" class="form-control" placeholder="Start Date" />
        </div>
        <div class="form-group">
          <label for="projectManager">Gerente responsável</label>
          <form:input id="projectManager" value="${project.projectManager}" type="text" path="projectManager" class="form-control" placeholder="Project Manager" />
        </div>
        <div class="form-group">
          <label for="endDate">Previsão de término</label>
          <form:input id="endDate" value="${project.endDate}" type="date" path="endDate" class="form-control" placeholder="End Date" />
        </div>
        <div class="form-group">
          <label for="realEndDate">Data real de término</label>
          <form:input id="realEndDate" value="${project.realEndDate}" type="date" path="realEndDate" class="form-control" placeholder="Real End Date" />
        </div>
        <div class="form-group">
          <label for="budget">Orçamento total</label>
          <form:input id="budget" value="${project.budgetTotal}" type="number" path="budgetTotal" class="form-control" placeholder="Budget Total" />
        </div>
        <div class="form-group">
          <label for="total">Descrição</label>
          <form:input id="description" value="${project.description}" type="text" path="description" class="form-control" placeholder="Description" />
        </div>
        <div class="form-group">
          <label for="employees">Funcionario</label>
          <select multiple class="custom-select form-control" name="employees" id="employees">
              <option value="">Selecione</option>
              <c:forEach items="${employees}" var="employee">
                 <option selected=${(employee.id == project.id) ? "selected" : ""} value=${employee.id}>${employee.name} - ${employee.role}</option>
              </c:forEach>
              </>
          </select>
        </div>

        <div class="form-group">
          <label for="status">Status</label>
          <select class="custom-select form-control" name="status" id="status">
              <option value="">Selecione</option>
              <c:forEach items="${statusList}" var="status">
                 <option selected="${(status.value == project.status.value) ? 'selected' : ''}" value=${status}>${status.value}</option>
              </c:forEach>
              </>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
      </form:form>
    </body>
</html>