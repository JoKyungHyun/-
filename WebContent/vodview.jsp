<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>vodview.jsp</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet"type="text/css"href="index.css">

</head>
<body>

<div class="header">
  <h1>My Website</h1>
  <p>A website created by me.</p>
</div>

<div class="navbar">
	<a href="register.jsp" class="right">Register</a>
	<c:if test="${sessionScope.id == null}">
  <a onclick="document.getElementById('id01').style.display='block'"class="right">Login</a>
	</c:if>
<!-- The Modal -->
<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'"
class="close" title="Close Modal">&times;</span>

  <!-- Modal Content -->
  <form class="modal-content animate" action="session" method="post">
    <div class="imgcontainer">
      <img src="image/img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <label for="ID"><b>ID</b></label><br>
      <input type="text" placeholder="Enter ID" name="id"><br>

      <label for="pw"><b>Password</b></label><br>
      <input  type="password" placeholder="Enter Password" name="pw"><br>
      
      <label for="type"><b>type</b></label><br>
      <input type="radio"name="type"value="admin">admin
	  <input type="radio"name="type"value="customer">customer<br>

      <button type="submit" value="Log in">Login</button>
    </div>
    

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
   </div>
    
  </form>
  </div>
  <c:if test="${sessionScope.id != null}">
  <a href="vodservlet?command=end" class="right">Log out</a>
  <a href="main.jsp">Home</a>
  <a href="vodservlet?command=view">Vod</a>
  <a href="vod.jsp">Save</a>

  </c:if>

  </div>



  <!-- About Section -->
 
  <c:if test="${sessionScope.id != null}">
  <div class="w3-container w3-padding-32 w3-center">  
    <br><table id="customers"  border="1">
	<tr>
			<th>Title</th><th>Genre</th><th>Director</th><th>Score</th>
		</tr>
		<c:forEach items="${requestScope.list}" var="data">
		    <tr>	
		        <td><a href="vodservlet?command=check&title=${data.title}">${data.title}</a></td>
		        <td>${data.genre}</td>
		        <td>${data.director}</td>
		        <td>${data.score}</td>

		    </tr>
	</c:forEach>
	
	</table><br>
  </div>
  </c:if>
  <hr>

</body>
</html>