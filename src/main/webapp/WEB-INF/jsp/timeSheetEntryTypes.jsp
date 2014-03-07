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

         <h1>Time Sheet Entry Types</h1>



<table class="table table-hover table-striped">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Hourly Rate</th>
        <th><input class="btn btn-default" type="button" value="New..." onClick="javascript:$('#rowNEW').slideToggle(500);"></th>
    </tr>
<form method="POST" action="saveTimeSheetEntryType.html">
    <tr id="rowNEW" style="display:none;">
        <td><input type="text" name="id" readonly value=""></td>
        <td><input type="text" name="name" value="Enter name..."></td>
        <td><input type="text" name="hourlyRate" value="Enter hourly rate in pounds"></td>
        <td>
            <input class="btn btn-default" type="submit" value="Save">
        </td>
    </tr>
</form>
<c:forEach items="${timeSheetEntryTypes}" var="timeSheetEntryType">
<form method="POST" action="saveTimeSheetEntryType.html">
    <tr id="row${timeSheetEntryType.id}">
        <td><input type="text" name="id" readonly value="${timeSheetEntryType.id}"></td>
        <td><input type="text" name="name" value="${timeSheetEntryType.name}"></td>
        <td><input type="text" name="hourlyRate" value="${timeSheetEntryType.hourlyRate}"></td>
        <td>
            <input class="btn btn-default" type="submit" value="Save">
            <input class="btn btn-default" type="button" value="Delete..." onClick="javascript:deleteTimeSheetEntryType(${timeSheetEntryType.id});">
        </td>
    </tr>
</form>
</c:forEach>
</table>


     </div> <!-- /container -->

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
      <script>
        function deleteTimeSheetEntryType(timeSheetEntryTypeId) {
            location.href="deleteTimeSheetEntryType.html?id=" + timeSheetEntryTypeId;
        }
      </script>
    </body>
</html>