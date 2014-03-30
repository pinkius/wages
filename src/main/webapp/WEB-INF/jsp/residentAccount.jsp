<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Room Bookings</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/datepicker.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

         <h1>Resident Account: ${account.resident.name}</h1>
         <h2>Balance: &pound; ${account.balance}</h2>
<table class="table table-hover table-striped">
    <tr>
        <th>Date</th>
        <th>Name</th>
        <th>Amount</th>
    </tr>
<c:forEach items="${account.charges}" var="charge" varStatus="count">
    <tr id="displayRow${count.index}">
        <td><joda:format value="${charge.date}" pattern="dd/MM/yyyy" /></td>
        <td>${charge.name}</td>
        <td>${charge.amount}</td>
    </tr>
</c:forEach>
</table>
     </div> <!-- /container -->

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
      <script src="js/bootstrap-datepicker.js"></script>
      <script>
        function editRow(rowCounter) {
            $('#displayRow'+rowCounter).toggle();
            $('#editRow'+rowCounter).toggle();
        }

        function addRow() {
            $('#addRow').toggle();
        }

        function deleteRow(id) {
            location.href="deleteRoom.html?id=" + id;
        }
      </script>
    </body>
</html>