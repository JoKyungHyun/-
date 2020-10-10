<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>
<link rel="stylesheet"type="text/css"href="index.css">
</head>
<body>

 <br><table id="customers"  border="1">
	<tr>
			<th>Title</th><th>Genre</th><th>Director</th><th>Score</th>
		</tr>
		
		    <tr>
		        <td>${requestScope.vod.title}</td>
		        <td>${requestScope.vod.genre}</td>
		         <td>${requestScope.vod.director}</td>
		           <td>${requestScope.vod.score}</td>
		    </tr>
	</table><br>
	 <hr>
	 <form action="vodservlet">
	  <label for="score"><b>score</b></label><br>
    	<input type="radio"name="score"value="1">1
    	<input type="radio"name="score"value="2">2
    	<input type="radio"name="score"value="3">3
    	<input type="radio"name="score"value="4">4
		<input type="radio"name="score"value="5">5<br><br>
	 <input type= hidden name = title value="${requestScope.vod.title}">
	  <button onclick="location.href='vodservlet'"name="command" value="update">수정 하기</button>
	 </form>
	 <form action="vodservlet">
	 <input type= hidden name = title value="${requestScope.vod.title}">
	  <button onclick="location.href='vodservlet'"name="command" value="delete">삭제 하기</button>
	 </form>
	<button onclick="location.href='main.jsp'"class="right">초기 화면으로 돌아가기</button>
</body>
</html>