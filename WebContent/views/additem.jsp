<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Item</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
.form-control {
	height: 41px;
	background: #F2F2F2;
	box-shadow: none !important;
	border: none;
}
.form-control:focus {
	background: #E2E2E2;
}
.form-control, .btn {
	border-radius: 3px;
}
.signup-form {
	width: 400px;
	margin: 30px auto;
}
.signup-form form {
	color: #999;
	border-radius: 3px;
	margin-bottom: 15px;
	background: #fff;
	border-radius: 10px;
	box-shadow: 3px 3px 6px 6px #dadada;
	padding: 30px;
}
.signup-form h2 {
	color: #333;
	font-weight: bold;
	margin-top: 0;
}
.signup-form hr {
	margin: 0 -30px 20px;
}
.signup-form .form-group {
	margin-bottom: 20px;
}
.signup-form input[type="checkbox"] {
	margin-top: 3px;
}
.signup-form .row div:first-child {
	padding-right: 10px;
}
.signup-form .row div:last-child {
	padding-left: 10px;
}
.signup-form .btn {
	font-size: 16px;
	font-weight: bold;
	background: #3598DC;
	border: none;
	min-width: 140px;
}
.signup-form .btn:hover, .signup-form .btn:focus {
	background: #2389CD !important;
	outline: none;
}
.signup-form a {
	color: #fff;
	text-decoration: underline;
}
.signup-form a:hover {
	text-decoration: none;
}
.signup-form form a {
	color: #3598DC;
	text-decoration: none;
}
.signup-form form a:hover {
	text-decoration: underline;
}
.head {
	display: flex;
	justify-content: center;
	padding-bottom: 30px;
}
.signup-form .hint-text {
	padding-bottom: 15px;
	text-align: center;
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
.head-nav>.logout {
	margin-right: unset !important;
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
	<div class="signup-form">
    <form action="additem" method="post">
		<h2>Add Item</h2>
		<hr>
        <div class="form-group">
			<label>Select Categories </label><br>
			<select name="categories" class="form-control">
				<option value="electronic">Electronic</option>
				<option value="foodproducts">Food-Products</option>
				<option value="books">Books</option>
			</select>       
        </div>
		<div class="form-group">
            <input type="text" class="form-control" name="itemdesc" placeholder="Item Name" required="required">
        </div>
		<div class="form-group">
            <label>Unit</label><br>
			<select name="unit" class="form-control">
				<option value="nos">No's</option>
				<option value="kg">Kg</option>
				<option value="litre">Litre</option>
			</select>
        </div>
        <div class="form-group">
        	<label>Price</label><br>
            <input type="number" class="form-control" name="prize" min="1" required/>
        </div>
        <div class="form-group">
        	<label>Image</label><br>
        	<input type="file" name="image" accept=".jpg, .jpeg, .png" required >
        </div>        
		<div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg">Add Item</button>
        </div>
    </form>
</div>
</body>
</html>