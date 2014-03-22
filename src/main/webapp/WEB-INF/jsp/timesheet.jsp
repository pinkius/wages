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

         <h1>Timesheet</h1>

<table class="table table-hover table-striped">
    <tr>
        <td>Employee id</td>
        <td>Date</td>
        <td>Monday</td>
        <td>Tuesday</td>
        <td>Wednesday</td>
        <td>Thursday</td>
        <td>Friday</td>
        <td>Saturday</td>
        <td>Sunday</td>
        <td>Total</td>
    </tr>
<c:forEach items="${timeSheetRows}" var="row">
    <tr>
        <td>${row.employeeId}</td>
        <td>${row.weekCommencing}</td>
    <c:forEach var="i" begin="0" end="6">
        <td>${row.entries[i].hours}</td>
    </c:forEach>
        <td>1239 hours / &pound;100.50</td>
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