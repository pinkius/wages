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

         <h1>Employee Administration</h1>



<form method="POST" action="saveEmployee.html">
<table class="table table-hover table-striped">
    <tr>
        <th>ID</th>
        <td><input type="hidden" id="id" name="id" value="${employee.id}">${employee.id}</td>
    </tr>
    <tr>
        <th>First names</th>
        <td><input type="text" id="firstNames" name="firstNames" value="${employee.firstNames}"></td>
    </tr>
    <tr>
        <th>Last name</th>
        <td><input type="text" id="lastName" name="lastName" value="${employee.lastName}"></td>
    </tr>
    <tr>
        <th>Tax Code</th>
        <td><input type="text" id="taxCode" name="taxCode" value="${employee.taxCode}"></td>
    </tr>
    <tr>
        <td colspan="2"><input class="btn btn-default" type="submit" value="Save"></th>
    </tr>
</table>
</form>


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
      </script>
    </body>
</html>