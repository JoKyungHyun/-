package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.MemberDTO;
import model.dto.MovieDTO;
import model.dto.VodDTO;
import util.DBUtil;

public class VodDAO {
	
	// vod 저장
	public static boolean saveMovie(VodDTO movie) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO vod VALUES(?,?,?,?)");

			pstmt.setString(1, movie.getTitle());
			pstmt.setString(2, movie.getGenre());
			pstmt.setString(3, movie.getDirector());
			pstmt.setInt(4, movie.getScore());

			int count = pstmt.executeUpdate();
			if (count != 0) {
				result = true;
			}
		} finally {
			DBUtil.close(pstmt, con);
		}
		return result;
	}

	// 모든 vod 조회
	public static ArrayList<VodDTO> getAllContents() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<VodDTO> alist = null;
		String sql = "SELECT title,genre,director,score from vod";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rset = pstmt.executeQuery();
			alist = new ArrayList<VodDTO>();
			while (rset.next()) {
				alist.add(new VodDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getInt(4)));
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return alist;
	}
	
	//vod 조회
	public static VodDTO getMovie(String title) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		VodDTO alist = null;
		String sql = "SELECT title,genre,director,score from vod where title ='" + title + "'";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rset = pstmt.executeQuery();
			;
			while (rset.next()) {
				alist = new VodDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getInt(4));
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		return alist;
	}
	
	// vod 삭제
	public static boolean deleteMovie(String title) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "delete from vod where title=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);

			int count = pstmt.executeUpdate();

			if (count != 0) {
				result = true;
			}
		} finally {
			DBUtil.close(pstmt, con);
		}
		return result;
	}
	// vod 수정
		public static boolean updateMovie(String title, int score) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean result = false;
			String sql = "update vod set score =? where title=?";
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, score);
				pstmt.setString(2, title);

				int count = pstmt.executeUpdate();

				if (count != 0) {
					result = true;
				}
			} finally {
				DBUtil.close(pstmt, con);
			}
			return result;
		}
}
