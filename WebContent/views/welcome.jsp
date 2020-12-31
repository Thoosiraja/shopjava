<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>USER DASHBOARD</title>
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
	text-decoration: none;
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
.head-nav>.logout {
	margin-right: unset !important;
}
.lo{
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
	text-decoration: none;
}
a{
	text-decoration: none;
}
a:hover {
	text-decoration: none !important;
	background: white;
	color: black;
}

</style>
</head>
<body>
	<div class="head-nav">
		<a href="displayitem">SHOPING PAGE</a>
		<a href="userinvoice">CHECK MY INVOICES</a>
		<div class="lo"><a href="logout">LOGOUT</a></div>
	</div>
	<h2>WELCOME</h2>
</body>
</html>