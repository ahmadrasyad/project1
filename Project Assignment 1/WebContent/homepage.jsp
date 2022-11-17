<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style >
.header{
  padding: 20px;
  background: #336DFF;
  color: violet;
  font-size: 15px;
  margin:0;
  }
.nav{
  padding: 15px;
  text-align: center;
  background: #33D1FF;
  color: red;
  font-size: 15px;
  margin:0;

}

footer{
	text-align: center;
	padding: 3px;
	background-color: DarkSalmon;
	color: white;
}

section{
	text-align: center;
	background-color: Orange;
	padding: 40px;
	color: yellow;
}

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

.button1 {background-color: Green;} 
.button2 {background-color: Blue;} 

</style>
</head>
<body>

<div class="header">
  <h2>Project 1</h2>
</div>

<div class="nav">
  <a href="#navi">Navigation 1</a>
  <a href="#navi">Navigation 2</a>
  <a href="#navi">Navigation 3</a>
</div>
<div class="sec">
  <h1>Section</h1>

<form method="post" action="./add.jsp"><button class="button button1">Add New Record</button></form>

<form method="post" action="list"><button class="button button2">View all Record</button></form>
  </div>
<div class="foot">
  <h1>Footer</h1>
  <p>This is Footer Part</p>
</div>

<input type="submit" value="Download PDF" name="download" onclick="window.print()" /> 
</body>
</html>