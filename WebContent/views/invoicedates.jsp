<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INVOICE BETWEEN DATE</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
body{
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
		<a href="registeradmin">ADD ADMIN </a> 
		<a href="additem">ADD ITEM</a> 
		<a href="checkinvoice">CHECK INVOICE </a> 
		<a href="invoicebetweendate">INVOICE BETWEEN DATE </a> 
		<div class="log"><a href="logout" class="logout">LOGOUT</a></div>
	</div>
	<form action="invoicebetweendate" method="post" class="navbar-form navbar-left">
		<label>From : </label>
		<div class="form-group">
        	<input type="date" name="start" class="form-control">
      	</div>
      	<label>To : </label>
		<div class="form-group">
        	<input type="date" name="end" class="form-control">
      	</div>
		<button type="submit" class="btn btn-default">GET INVOICES</button>
	</form>
	<br>
	<label>${error}</label><br/>
</body>
</html>