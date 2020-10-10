package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import model.dto.MemberDTO;
import util.DBUtil;



public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public static ArrayList<MemberDTO> userData = new ArrayList<>();
	
	public static void addCustomer(MemberDTO register) {
		userData.add(register);
	}
	
	public static ArrayList<String> getIds(){
		ArrayList<String> id = new ArrayList<>();
		for(MemberDTO c : userData) {
			id.add(c.getId());
		}
		
		return id;
	}
	
	public static ArrayList<String> getPws(){
		ArrayList<String> pw = new ArrayList<>();
		for(MemberDTO c : userData) {
			pw.add(c.getId());
		}
		
		return pw;
	}
	
	// 중복된 아이디 또는 비밀 번호 확인 여부 점검
	public static boolean add(MemberDTO register) {
		if(getIds().contains(register.getId())&&(register.getPw()!=register.getRepw())) {
			return false;
		}else {
			addCustomer(register);
			return true;
		}
	}
	

	// 회원가입
	public static boolean writeContent(MemberDTO mm) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		if(add(mm)!=true) {
			return false;
		}
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO members VALUES(?,?,?,?,?,?)");
			
	        pstmt.setString(1, mm.getId());
	        pstmt.setString(2, mm.getName());
	        pstmt.setString(3, mm.getSex());
	        pstmt.setString(4, mm.getAddress());
	        pstmt.setString(5, mm.getPw());
	        pstmt.setString(6, mm.getRepw());
	        
			int count = pstmt.executeUpdate();			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(pstmt, con);
		}
		return result;		
	}
	
	// 회원 조회
	public static boolean check(String id, String pw) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from members where id =? and pw=?");

	       
	        pstmt.setString(1, id);
	       
	        pstmt.setString(2, pw);
	       
	        
			int count = pstmt.executeUpdate();			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(pstmt, con);
		}
		return result;		
	}
}
