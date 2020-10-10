<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>vod.jsp</title>
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
    <div class="w3-padding-32">
      <h4><b>Movie Search</b></h4>
      
      <form action="vodservlet">
      	<input type="hidden" name="command" value="vod">
      	<input type="text" name="title" placeholder="Title">
      	<input id=apple type="text" name="director" placeholder="Director"><br>
      	<label for="score"><b>score</b></label><br>
    <input type="radio"name="score"value="1">1
    <input type="radio"name="score"value="2">2
    <input type="radio"name="score"value="3">3
    <input type="radio"name="score"value="4">4
	<input type="radio"name="score"value="5">5<br><br>
      	<input type="submit" name="search" value="submit">

      </form>
      <h6><i>With Passion For Real, Good Food</i></h6>
      <p>Just me, myself and I, exploring the universe of unknownment. I have a heart of love and an interest of lorem ipsum and mauris neque quam blog. I want to share my world with you. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
    </div>
  </div>
  </c:if>
  <hr>

</body>
</html>