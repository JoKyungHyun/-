<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>admin.jsp</title>
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
  </c:if>

  </div>

  <br><table id="customers"  border="1">
	<tr>
			<th>ID</th><th>Name</th><th>Sex</th><th>Address</th><th>Password</th><th>Repeat Password</th>
		</tr>
		<c:forEach items="${requestScope.list}" var="data">
		    <tr>
		        <td>${data.id}</td>
		        <td>${data.name}</td>
		        <td>${data.sex}</td>
		        <td>${data.address}</td>
	            <td>${data.pw}</td>
		         <td>${data.repw}</td>
		    </tr>
	</c:forEach>
	
	</table><br>
  <hr>

</body>
</html>