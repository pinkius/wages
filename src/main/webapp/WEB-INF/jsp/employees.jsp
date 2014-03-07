<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Wages</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

       <!-- Main component for a primary marketing message or call to action -->
         <h1>Employees</h1>



<table class="table table-hover table-striped">
    <tr>
        <th>ID</th>
        <th>First names</th>
        <th>Last name</th>
        <th><input class="btn btn-default" type="button" value="New..." onClick="javascript:addEmployee();"></th>
    </tr>
<c:forEach items="${employees}" var="employee">
    <tr>
        <td>${employee.id}</td>
        <td>${employee.firstNames}</td>
        <td>${employee.lastName}</td>
        <td>
            <input class="btn btn-default" type="button" value="Edit..." onClick="javascript:editEmployee(${employee.id});">
            <input class="btn btn-default" type="button" value="Delete..." onClick="javascript:deleteEmployee(${employee.id});">
        </td>
    </tr>
</c:forEach>
</table>


     </div> <!-- /container -->

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
      <script>
        function editEmployee(employeeId) {
            location.href="editEmployee.html?id=" + employeeId;
        }

        function addEmployee(employeeId) {
            location.href="addEmployee.html";
        }

        function deleteEmployee(employeeId) {
            location.href="deleteEmployee.html?id=" + employeeId;
        }
      </script>
    </body>
</html>