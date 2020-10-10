package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.dto.MemberDTO;
import util.DBUtil;

public class AdminDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 모든 게시물 조회
		public  static ArrayList<MemberDTO> getAllContents() throws SQLException{
			Connection con = null;	
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<MemberDTO> alist = null;
			String sql="SELECT ID,name,sex,addr,pw,rpw from members";
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				rset = pstmt.executeQuery();
				alist = new ArrayList<MemberDTO>();
				while(rset.next()){
					alist.add(new MemberDTO(rset.getString(1),rset.getString(2),
							rset.getString(3),rset.getString(4),rset.getString(5)
			 				,rset.getString(6)));
				}
			}finally{
				DBUtil.close(rset, pstmt, con);
			}
			return alist;
		}

}
