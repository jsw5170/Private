package com.study.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.project.dto.CDto;

public class CDao {
	static DataSource dataSource;
	private static CDao instance = new CDao();
		
	public CDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static CDao getInstance() {
		return instance;
	}
	
	public void cWrite(String cId, int bId, String cParent, String cContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "insert into comment_board " +
							"(cNum, bId, cId, cDate, cParent, cContent ) " +
							" values " +
							" (COMMENT_SEQ.nextval, ?, ?, sysdate, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bId);
			pstmt.setString(2, cId);
			pstmt.setString(3, cParent);
			pstmt.setString(4, cContent);
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static ArrayList<CDto> cList(int bNum) {

		ArrayList<CDto> dtos = new ArrayList<CDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * " +
							" from comment_board " +
							"	where bId = ? " ;
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,bNum);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int cNum = resultSet.getInt("cNum");
				int bId = resultSet.getInt("bId");
				String cId = resultSet.getString("cId");
				Date cDate = resultSet.getDate("cDate");
				int cParent = resultSet.getInt("cParent");
				String cContent = resultSet.getString("cContent");
				//int cLevel = resultSet.getInt("cLevel");
				int cLevel= 1;
				CDto dto = new CDto(cNum, bId, cId, cDate, cParent, cContent, cLevel);
				
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public void cDelete(int cNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "delete from comment_board " +
							" where cNum = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cNum);
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void cUpdate(CDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String query = "update comment_board set " +
							" cContent = ?" +
							" where cNum = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getcContent());
			pstmt.setInt(2, dto.getcNum());
			
			int rn = pstmt.executeUpdate();
			if(rn > 0) { con.commit(); }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
