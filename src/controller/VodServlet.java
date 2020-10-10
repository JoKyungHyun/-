package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.MemberDAO;
import model.MovieDAO;
import model.VodDAO;
import model.dto.MemberDTO;
import model.dto.MovieDTO;
import model.dto.VodDTO;

/**
 * Servlet implementation class VodServlet
 */
@WebServlet("/vodservlet")
public class VodServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// command pattern & controller
		String command = request.getParameter("command");

		if (command == null) {
			command = "admin";
		}

		if (command.equals("vod")) {
			list(request, response);
		} else if (command.equals("view")) {
			view(request, response);
		} else if (command.equals("movie")) {
			movie(request, response);
		} else if (command.equals("register")) {
			register(request, response);
		} else if (command.equals("admin")) {
			admin(request, response);
		} else if (command.equals("end")) {
			end(request, response);
		} else if (command.equals("check")) {
			check(request, response);
		} else if (command.equals("delete")) {
			delete(request, response);
		} else if (command.equals("update")) {
			update(request, response);
		}

	}
	
	// vod 저장
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		boolean result = false;

		try {
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			String director = request.getParameter("director");
			int score = Integer.parseInt(request.getParameter("score"));

			MovieDTO movie = MovieDAO.searchMovie(title, director);
			result = VodDAO.saveMovie(new VodDTO(title, movie.getGenre(), director, score));
			request.setAttribute("list", VodDAO.getAllContents());
			if (result) {
				request.setAttribute("result", result);
				request.getRequestDispatcher("vodview.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "영화가 존재 하지 않거나 이미 있습니다");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "영화가 저장 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}
	
	// 모든 vod 조회
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		boolean result = true;

		try {

			request.setAttribute("list", VodDAO.getAllContents());
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "영화 보기 실패");
		}

		if (result) {
			request.getRequestDispatcher("vodview.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "영화 보기 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	// 영화 조회
	private void movie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String director = request.getParameter("director");
		MovieDTO movie = MovieDAO.searchMovie(title, director);

		if (movie != null) {
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("view.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "영화 조회 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	// 회원가입
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String repw = request.getParameter("repw");
		String address = request.getParameter("address");

		boolean result = false;

		try {
			result = MemberDAO.writeContent(new MemberDTO(id, name, sex, address, pw, repw));
			
		} catch (SQLException e) { 
			e.printStackTrace();
			request.setAttribute("error.jsp", "가입 실패입니다.");
		}
		if (result) {
			request.setAttribute("success", "님 환영합니다.");
			request.setAttribute("name", name);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "가입 실패입니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

		
	}
	
	// 회원 조회
	private void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		try {
			request.setAttribute("list", AdminDAO.getAllContents());
			url = "admin.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "모두 보기 실패 재 실행 해 주세요");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void end(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();// 무효화
		session = null;

		response.sendRedirect("main.jsp");

	}
	
	// vod 확인
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");

		try {
			VodDTO vod = VodDAO.getMovie(title);
			if (vod != null) {
				request.setAttribute("vod", vod);
				request.getRequestDispatcher("movie.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "검색 실패입니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}

		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
	
	// vod 삭제
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		boolean result = false;

		try {
			result = VodDAO.deleteMovie(title);

			if (result) {
				request.setAttribute("success", "삭제 성공입니다.");
				request.getRequestDispatcher("success.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("error", "삭제 실패입니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}

		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("erroe.jsp");
		}
	}
	
	// vod 수정
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		int score = Integer.parseInt(request.getParameter("score"));
		boolean result = false;

		try {
			result = VodDAO.updateMovie(title, score);

			if (result) {
				request.setAttribute("success", "수정 성공입니다.");
				request.getRequestDispatcher("success.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("error", "검색 사번에 해당하는 사람이 존재하지 않습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}

		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
}	
