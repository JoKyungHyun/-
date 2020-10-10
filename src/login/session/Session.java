package login.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;




@WebServlet("/session")
public class Session extends HttpServlet {
	// 로그인
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String type = request.getParameter("type");
			if(id.isEmpty()||pw.isEmpty()) {
				request.setAttribute("error", "아이도 또는 비밀번호를 입력하지 않았습니다");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			HttpSession session = request.getSession();	
			try {
				if(type.equals("customer")) {
					if(MemberDAO.check(id,pw)&&(session.isNew()||session.getAttribute("id")==null)) {
						session.setAttribute("id",id);
						response.sendRedirect("main.jsp");
					}else {
						request.setAttribute("error", "로그인 실패입니다. 아이디 또는 비밀번호를 확인해주세요");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				}else {
					if(MemberDAO.check(id,pw)&&(session.isNew()||session.getAttribute("id")==null)) {
						session.setAttribute("id",id);
						response.sendRedirect("vodservlet");
					}else {
						request.setAttribute("error", "로그인 실패입니다. 아이디 또는 비밀번호를 확인해주세요");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			out.close();
		}
		
		// 로그아웃
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				HttpSession session = request.getSession(false);
				
				if(session != null && session.getAttribute("id") != null) {
					session.invalidate();
					response.sendRedirect("main.jsp");
				}else {
					response.sendRedirect("logout");
				}
				out.close();
			}
			
			

}
