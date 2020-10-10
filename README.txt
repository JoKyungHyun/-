 주제: 영화 평점 프로젝트

 데이터 : 영화 진흥 위원회 api를 jsoup을 통하여 데이터를 불러온다.
 
 main페이지는  main.jsp
 
 웹 사용법 : 1. Register로 회원 가입을 한다. 
 		 2. log in을 한다.
 		 3. admin으로 로그인하면 사용자의 데이터를 볼 수 있다.
 		 4. customer로 로그인하면 메인 페이지가 뜬다.
 		 5. 메인의 search로 영화를 검색한다.
 		 6. Save 바로 가면 영화의 평점을 남길 수 있다.
 		 7. Vod 바로 가면 평점을 남긴 영화들을 볼 수 있다.
 		 8. Vod 바에서 영화 제목을 누르면 삭제/수정이 가능하다.
 		 
MVC 패턴

구성 
		sql로 member와 vod 테이블 추가
		register로 회원가입하여 member 테이블에 저장
		login 세션으로 구성
		vod의 CRUD기능 서블릿을 이용하여 구성
 		