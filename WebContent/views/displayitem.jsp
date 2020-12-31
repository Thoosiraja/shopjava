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
<title>SHOP HERE</title>
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
a:hover {
	text-decoration: none;
	background: white;
	color: black;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 40%;
  border-radius: 5px;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

img {
  border-radius: 5px 5px 0 0;
}

.container {
  padding: 2px 16px;
}


</style>
</head>
<body>
	<div class="head-nav">
		<a href="displayitem">SHOPING PAGE</a>
		<a href="userinvoice">CHECK MY INVOICES</a>
		<div class="log"><a href="logout">LOGOUT</a></div>
	</div>
	<form method="post" action="shop">
	<table class="table table-hover">
		<thead>
        	<tr>
            	<th>ITEM NAME</th><th>CATEGORIES</th><th>IMAGE</th><th>PRICE</th><th>UNIT</th><th>ENTER HERE</th>
        	</tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item">
            <tr>
            <td>${item.itemdesc}</td>
            <td>${item.categories}</td>
            <td><img src="data:image/jpg;base64,${item.image}" width="40" height="40"/></td>
            <td>${item.prize}</td>
            <td>${item.unit}</td>
            <td><input type="number" name= ${item.itemno} min="0" value="0" ></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
   <!--<c:forEach items="${items}" var="item">
    	<div class="card">
  		<img src="data:image/jpg;base64,${item.image}" alt=${item.itemdesc} style="width:100%">
  		<div class="container">
    		<h4><b>${item.itemdesc}</b></h4> 
    		<p>${item.categories}</p>
    		<p>Price/${item.unit} : ${item.prize}</p> 
  		</div>
		</div>
    </c:forEach>-->
    <button type="submit" class="btn btn-success">BUY</button>
    </form>
</body>
</html>