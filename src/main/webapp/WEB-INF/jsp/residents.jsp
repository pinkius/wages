<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Residents</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/datepicker.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

         <h1>Residents</h1>

<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>D.O.B.</th>
        <th>Active</th>
        <th>Date of Arrival</th>
        <th>Contact name</th>
        <th>Contact telephone</th>
        <th>NI Number</th>
        <th>Comment</th>
        <th><input class="btn btn-default" type="button" value="Add..." onClick="javascript:addRow();"></th>
    </tr>
        <tr id="addRow" style="display: none;">
            <form action="saveResident.html" method="POST">
            <td><input type="hidden" name="id" value="" /></td>
            <td><input type="text" name="name" /></td>
            <td><input type="text" size="8" name="dateOfBirth" data-provide="datepicker" data-date-format="dd/mm/yyyy"/></td>
            <td><input type="text" name="active" /></td>
            <td><input type="text" size="8" name="dateOfArrival" data-provide="datepicker" data-date-format="dd/mm/yyyy"/></td>
            <td><input type="text" name="contactName" /></td>
            <td><input type="text" name="contactTelephone" /></td>
            <td><input type="text" name="niNumber" /></td>
            <td><input type="text" name="comment" /></td>
            <td>&nbsp;</td>
            <td>
                <input class="btn btn-default" type="submit" value="Save">
                <input class="btn btn-default" type="button" value="Delete" onClick="javascript:deleteRow(${count.index});">
            </td>
            </form>
        </tr>
<c:forEach items="${residents}" var="resident" varStatus="count">
    <tr id="displayRow${count.index}">
        <td>${resident.id}</td>
        <td><a href="residentAccount.html?residentId=${resident.id}">${resident.name}</a></td>
        <td><joda:format value="${resident.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
        <td>${resident.active}</td>
        <td><joda:format value="${resident.dateOfArrival}" pattern="dd/MM/yyyy" /></td>
        <td>${resident.contactName}</td>
        <td>${resident.contactTelephone}</td>
        <td>${resident.niNumber}</td>
        <td>${resident.comment}</td>
        <td>
            <input class="btn btn-default" type="button" value="Edit..." onClick="javascript:editRow(${count.index});">
        </td>
    </tr>
    <tr id="editRow${count.index}" style="display: none;">
        <form action="saveResident.html" method="POST">
        <td><input type="hidden" name="id" value="${resident.id}" /></td>
        <td><input type="text" name="name" value="${resident.name}" /></td>
        <td><input type="text" size="8" name="dateOfBirth" data-provide="datepicker" data-date-format="dd/mm/yyyy" value="<joda:format value="${resident.dateOfBirth}" pattern="dd/MM/yyyy" />"/></td>
          <td><input type="text" name="active" value="${resident.active}" /></td>
          <td><input type="text" size="8" name="dateOfArrival" data-provide="datepicker" data-date-format="dd/mm/yyyy" value="<joda:format value="${resident.dateOfArrival}" pattern="dd/MM/yyyy" />"/></td>
          <td><input type="text" name="contactName" value="${resident.contactName}" /></td>
          <td><input type="text" name="contactTelephone" value="${resident.contactTelephone}"/></td>
          <td><input type="text" name="niNumber" value="${resident.niNumber}"/></td>
          <td><input type="text" name="comment" value="${resident.comment}"/></td>
          <td>
            <input class="btn btn-default" type="submit" value="Save">
            <input class="btn btn-default" type="button" value="Delete" onClick="javascript:deleteRow(${resident.id});">
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
            location.href="deleteResident.html?id=" + id;
        }
      </script>
    </body>
</html>