<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Rooms</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/datepicker.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

         <h1>Rooms</h1>

<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>Number</th>
        <th><input class="btn btn-default" type="button" value="Add..." onClick="javascript:addRow();"></th>
    </tr>
        <tr id="addRow" style="display: none;">
            <form action="saveRoom.html" method="POST">
            <td><input type="hidden" name="id" value="" /></td>
            <td><input type="text" name="number" /></td>
            <td>&nbsp;</td>
            <td>
                <input class="btn btn-default" type="submit" value="Save">
                <input class="btn btn-default" type="button" value="Delete" onClick="javascript:deleteRow(${count.index});">
            </td>
            </form>
        </tr>
<c:forEach items="${rooms}" var="room" varStatus="count">
    <tr id="displayRow${count.index}">
        <td>${room.id}</td>
        <td><a href="roomBookings.html?roomId=${room.id}">${room.number}</a></td>
        <td>
            <input class="btn btn-default" type="button" value="Edit..." onClick="javascript:editRow(${count.index});">
        </td>
    </tr>
    <tr id="editRow${count.index}" style="display: none;">
        <form action="saveRoom.html" method="POST">
        <td><input type="hidden" name="id" value="${room.id}" /></td>
        <td><input type="text" name="number" value="${room.number}" /></td>
          <td>
            <input class="btn btn-default" type="submit" value="Save">
            <input class="btn btn-default" type="button" value="Delete" onClick="javascript:deleteRow(${room.id});">
        </td>
        </form>
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