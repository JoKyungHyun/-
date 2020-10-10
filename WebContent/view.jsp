<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<th>Title</th><th>Genre</th><th>Nation</th><th>Director</th><th>Actor1</th><th>Actor2</th>
		</tr>
		
		    <tr>
		        <td>${requestScope.movie.title}</td>
		        <td>${requestScope.movie.genre}</td>
		         <td>${requestScope.movie.nation}</td>
		           <td>${requestScope.movie.dirctor}</td>
	              <td>${requestScope.movie.actor1}</td>
		         <td>${requestScope.movie.actor2}</td>
		    </tr>
	</table><br>
	 <hr>
	<button onclick="location.href='main.jsp'">초기 화면으로 돌아가기</button>
</body>
</html>