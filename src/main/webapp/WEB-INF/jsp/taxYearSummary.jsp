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
    <link rel="stylesheet" href="css/datepicker.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

         <h1>Tax Year Summary</h1>

<table class="table table-hover table-striped">
    <tr>
        <th>Employee</th>
        <th>Week</th>
        <th>Tax Code</th>
        <th align="right">Weekly Gross</th>
        <th align="right">Cumulative Gross</th>
        <th align="right">Tax so far</th>
        <th align="right">Tax This Week</th>
        <th align="right">Net This Week</th>
        <th align="right">Cumulative Net</th>
    </tr>
<c:forEach items="${weeks}" var="week" varStatus="count">
    <tr id="displayRow${count.index}">
        <td>${week.employee.firstNames} ${week.employee.lastName}</td>
        <td><joda:format value="${week.weekCommencing}" pattern="dd/MM/yyyy" /></td>
        <td>${week.taxCode}</td>
        <td align="right">&pound; ${week.grossThisWeek}</td>
        <td align="right">&pound; ${week.cumulativeGross}</td>
        <td align="right">&pound; ${week.cumulativeTaxToPreviousWeek}</td>
        <td align="right">&pound; ${week.taxThisWeek}</td>
        <td align="right">&pound; ${week.netThisWeek}</td>
        <td align="right">&pound; ${week.cumulativeNet}</td>
    </tr>
</c:forEach>
</table>
     </div> <!-- /container -->

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>