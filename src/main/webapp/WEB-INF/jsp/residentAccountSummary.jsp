<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Residents Account Summary</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/datepicker.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

         <h1>Residents Account Summary</h1>
<table class="table table-hover table-striped">
    <tr>
        <th>Resident</th>
        <th>D.O.A.</th>
        <th>Current Room</th>
        <th>Balance</th>
    </tr>
<c:forEach items="${accountSummaries}" var="account">
    <tr>
        <td>${account.resident.name}</td>
        <td><joda:format value="${account.resident.dateOfArrival}" pattern="dd/MM/yyyy" /></td>
        <td>NOT YET IMPLEMENTED</td>
        <td><a href="residentAccount.html?residentId=${account.resident.id}">&pound; ${account.balance}</a></td>
    </tr>
</c:forEach>
</table>
     </div> <!-- /container -->

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>