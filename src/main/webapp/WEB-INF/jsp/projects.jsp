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
      <style>
        html{
            background: #e3e3e3;
            margin: 20px;
            padding: 10px;
        }
        body {
          display: flex;
          flex-direction:
          flex-wrap: nowrap;
          justify-content: center;
          align-content: center;
          align-items: center;
        }
        .child {
          margin: 10px;
          padding: 5px;
          flex: 0 1 auto;
          align-self: auto;
          text-align: center; /*optional*/
        }
      </style>
  </head>
  <body>
    <div class="child">
      <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
          ${message}
        </div>
      </c:if>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Nome</th>
            <th scope="col">Data de início</th>
            <th scope="col">Gerente responsável</th>
            <th scope="col">Previsão de término</th>
            <th scope="col">Data real de término</th>
            <th scope="col">Orçamento total</th>
            <th scope="col">Descrição</th>
            <th scope="col">Funcionarios</th>
            <th scope="col">Status</th>
            <th scope="col">Options</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${projects}" var="project">
              <tr>
                <td><c:out value="${project.name}"/></td>
                <td><c:out value="${project.startDate}"/></td>
                <td><c:out value="${project.projectManager}"/></td>
                <td><c:out value="${project.endDate}"/></td>
                <td><c:out value="${project.realEndDate}"/></td>
                <td><c:out value="${project.budgetTotal}"/></td>
                <td><c:out value="${project.description}"/></td>
                <td><c:out value="${project.employeesNames}"/></td>
                <td><c:out value="${project.status.value}"/></td>
                <td>
                  <form:form action="/projects/${project.id}" method="GET">
                    <button type="submit" class="btn btn-primary">Alterar</button>
                  </form:form>
                </td>
                <td>
                  <form:form action="/projects/${project.id}" method="DELETE">
                    <button type="submit" class="btn btn-danger">Excluir</button>
                  </form:form>
                </td>
              </tr>
          </c:forEach>
        </tbody>
      </table>
      <form action="/projects/new" method="GET">
        <button type="submit" id="newProject" class="btn btn-success">
          New Project
        </button>
      </form>
     </div>
  </body>
</html>