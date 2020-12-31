<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>MY INVOICES</title>
<style type="text/css">
body {
	margin: 0px;
}
.head-nav {
	height: 60px;
	background: black;
	display: flex;
	justify-content: space-around;
	align-items: center;
}
.head-nav>a {
	color: #fff;
	width: 20%;
	margin-right: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	text-decoration: none;
}
.log{
	align-items: right;
	border-style: solid;
	border-color: red;
	border-width: 1px;
	border-radius: 3px;
	padding: 5px;
	background-color: red;
	font-size: 20px;
	font-style: italic;
	color: white;
}
.head-nav>.logout {
	margin-right: unset !important;
}
a:hover {
	text-decoration: none;
	background: white;
	color: black;
}

</style>
</head>
<body>
	<div class="head-nav">
		<a href="displayitem">SHOPING PAGE</a>
		<a href="userinvoice">CHECK MY INVOICES</a>
		<div class="log"><a href="logout">LOGOUT</a></div>
	</div>
	<label>${error}</label><br>
	<table class="table table-hover">
        <thead>
        	<tr>
            	<th>INVOICE NO</th><th>DATE</th><th>TOTAL</th><th>GET INVOICE</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${invoices}" var="invoice">
            <tr>
            <td>${invoice.invno}</td>
            <td>${invoice.date}  </td>
            <td>${invoice.total}</td>
            <td><form method="post" action="getinvoice"><input type="hidden" name="invoiceid" value="${invoice.invno}"/><button type="submit" class="btn btn-primary">GET INVOICE</button></form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>