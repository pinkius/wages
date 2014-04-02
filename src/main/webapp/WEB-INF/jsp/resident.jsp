<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Resident: ${resident.name}</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/datepicker.css">

    <!-- link rel="stylesheet" href="css/wages.css" -->
  </head>
  <body>
     <%@ include file="/WEB-INF/jsp/common-navbar.jsp" %>


     <div class="container">

         <h1>Resident: ${resident.name}</h1>
         <h3>Details</h3>
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <td>${resident.id}</td>
    </tr>
    <tr>
        <th>Name</th>
        <td>${resident.name}</td>
    </tr>
    <tr>
        <th>D.O.B.</th>
        <td><joda:format value="${resident.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
    </tr>
    <tr>
        <th>Active</th>
        <td>${resident.active}</td>
    </tr>
    <tr>
        <th>Date of Arrival</th>
        <td><joda:format value="${resident.dateOfArrival}" pattern="dd/MM/yyyy" /></td>
    </tr>
    <tr>
        <th>Contact name</th>
        <td>${resident.contactName}</td>
    </tr>
    <tr>
        <th>Contact telephone</th>
        <td>${resident.contactTelephone}</td>
    </tr>
    <tr>
        <th>NI Number</th>
        <td>${resident.niNumber}</td>
    </tr>
</table>
<h3>Comments</h3>
<p class="bg-info">
${resident.comment}
</p>
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