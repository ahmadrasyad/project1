<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.io.OutputStream"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Database Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>	
.button {
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.button1 {background-color: crimson;}	
</style>
</head>
<body>
<form method="post" action="./homepage.jsp"><button class="button button1">Home</button></form>
<br><br><br>
<header>
<nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Context</a></li>
			</ul>
		</nav>

</header>
<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

					<form action="view" method="post">

					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />


				<fieldset class="form-group">
					
				<c:out value='${"Name : "}' />		<c:out value='${user.name}' />
				</fieldset>
<br>
				<fieldset class="form-group">
				<c:out value='${"Email : "}' />		<c:out value='${user.email}' />
				</fieldset>
<br>
				<fieldset class="form-group">
				<c:out value='${"Address : "}' />		<c:out value='${user.address}' />
				</fieldset>
<br>			
				<fieldset class="form-group">
				<c:out value='${"Phone : "}' />		<c:out value='${user.phone}' />
				</fieldset>
<br>				
				<fieldset class="form-group">
				<c:out value='${"City : "}' />		<c:out value='${user.city}' />
				</fieldset>
<br>				
				<fieldset class="form-group">
				<c:out value='${"Country : "}' />		<c:out value='${user.country}' />
				</fieldset>
<br>				
				<fieldset class="form-group">
				<c:out value='${"Education : "}' />		<c:out value='${user.education}' />
				</fieldset>
<br>				
				<fieldset class="form-group">
				<c:out value='${"Phone : "}' />		<c:out value='${user.phone}' />
				</fieldset>
<br>				
				<fieldset class="form-group">
				<c:out value='${"Photo : "}' />	
				<img src="getImageServlet?id=<c:out value='${user.id}' />"width="125" height ="125"  border="1">
				</fieldset>
				
				
				<a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
				</form>
				<input type="submit" value="Download PDF" name="download" onclick="window.print()" /> 
			</div>
		</div>
	</div>
</body>
</html>