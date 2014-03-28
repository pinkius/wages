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

         <h1>Timesheet</h1>

<table class="table table-hover table-striped">
    <tr>
        <th>Employee</th>
        <th>Week</th>
        <th>Monday</th>
        <th>Tuesday</th>
        <th>Wednesday</th>
        <th>Thursday</th>
        <th>Friday</th>
        <th>Saturday</th>
        <th>Sunday</th>
        <th>Total</th>
        <th><input class="btn btn-default" type="button" value="Add..." onClick="javascript:addTimeSheetRow();"></th>
    </tr>
        <tr id="addRow" style="display: none;">
            <form action="saveTimeSheet.html" method="POST">
            <input type="hidden" name="weekTimeSheetId" value="" />
            <input type="hidden" name="employeeId" value="${weeks[0].employee.id}" />
            <td>${week.employee.firstNames} ${week.employee.lastName}</td>
            <td><input type="text" size="8" name="weekCommencing" data-provide="datepicker" data-date-format="dd/mm/yyyy"/></td>
            <td><input type="text" size="2" name="mondayHours" /></td>
            <td><input type="text" size="2" name="tuesdayHours" /></td>
            <td><input type="text" size="2" name="wednesdayHours" /></td>
            <td><input type="text" size="2" name="thursdayHours" /></td>
            <td><input type="text" size="2" name="fridayHours" /></td>
            <td><input type="text" size="2" name="saturdayHours" /></td>
            <td><input type="text" size="2" name="sundayHours" /></td>
            <td>&nbsp;</td>
            <td>
                <input class="btn btn-default" type="submit" value="Save">
                <input class="btn btn-default" type="button" value="Delete" onClick="javascript:deleteTimeSheetRow(${count.index});">
            </td>
            </form>
        </tr>
<c:forEach items="${weeks}" var="week" varStatus="count">
    <tr id="displayRow${count.index}">
        <td>${week.employee.firstNames} ${week.employee.lastName}</td>
        <td><joda:format value="${week.weekCommencing}" pattern="dd/MM/yyyy" /></td>
        <td>${week.mondayHours}</td>
        <td>${week.tuesdayHours}</td>
        <td>${week.wednesdayHours}</td>
        <td>${week.thursdayHours}</td>
        <td>${week.fridayHours}</td>
        <td>${week.saturdayHours}</td>
        <td>${week.sundayHours}</td>
        <td>${week.totalHours} hrs @ &pound; ${week.timeSheetEntryType.hourlyRate}/hr / &pound; ${week.totalGrossWage}</td>
        <td>
            <input class="btn btn-default" type="button" value="Edit..." onClick="javascript:editTimeSheetRow(${count.index});">
        </td>
    </tr>
    <tr id="editRow${count.index}" style="display: none;">
        <form action="saveTimeSheet.html" method="POST">
        <input type="hidden" name="weekTimeSheetId" value="${week.id}" />
        <input type="hidden" name="employeeId" value="${week.employee.id}" />
        <td>${week.employee.firstNames} ${week.employee.lastName}</td>
        <td><input type="text" size="8" name="weekCommencing" value="<joda:format value="${week.weekCommencing}" pattern="dd/MM/yyyy" />" data-provide="datepicker" data-date-format="dd/mm/yyyy"/></td>
        <td><input type="text" size="2" name="mondayHours" value="${week.mondayHours}" /></td>
        <td><input type="text" size="2" name="tuesdayHours" value="${week.tuesdayHours}" /></td>
        <td><input type="text" size="2" name="wednesdayHours" value="${week.wednesdayHours}" /></td>
        <td><input type="text" size="2" name="thursdayHours" value="${week.thursdayHours}" /></td>
        <td><input type="text" size="2" name="fridayHours" value="${week.fridayHours}" /></td>
        <td><input type="text" size="2" name="saturdayHours" value="${week.saturdayHours}" /></td>
        <td><input type="text" size="2" name="sundayHours" value="${week.sundayHours}" /></td>
        <td>&nbsp;</td>
        <td>
            <input class="btn btn-default" type="submit" value="Save">
            <input class="btn btn-default" type="button" value="Delete" onClick="javascript:deleteTimeSheetRow(${count.index});">
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
        function editTimeSheetRow(rowCounter) {
            $('#displayRow'+rowCounter).toggle();
            $('#editRow'+rowCounter).toggle();
        }

        function addTimeSheetRow() {
            $('#addRow').toggle();
        }

        function deleteEmployee(employeeId) {
            location.href="deleteEmployee.html?id=" + employeeId;
        }
      </script>
    </body>
</html>