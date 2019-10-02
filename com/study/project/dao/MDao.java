package com.study.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.project.DAOEx;
import com.study.project.DTOEx;
import com.study.project.dto.CDto;

public class MDao {
	static DataSource dataSource;
	private static MDao instance = new MDao();
		
	public MDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MDao getInstance() {
		return instance;
	}
	
	public ArrayList<DTOEx> getAllMember() {
		ArrayList<DTOEx> dtos = new ArrayList<DTOEx>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members";
		DTOEx dto = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			set = pstmt.executeQuery();
			while(set.next()) {
				dto = new DTOEx();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
				dto.setBlockMember(set.getString("blockMember"));
				
				dtos.add(dto);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	public DTOEx getMember(String searchId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where id = ?";
		DTOEx dto = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchId);
			set = pstmt.executeQuery();
			if(set.next()) {
				dto = new DTOEx();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
				dto.setBlockMember(set.getString("blockMember"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	public void deleteMember(String searchId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from members where id = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchId);
			pstmt.executeUpdate();
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
	public void deleteBoard(String searchId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchId);
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
	public String maxValue(String com, String date, String today) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = null;
		String startDay = date;
		String endDay = today;
		String bName = null;
		String cId = null;
		String result = null;
		if(com.equals("maxBoard")) {
			query = "select bname " + 
				" from mvc_board " + 
				" where bDate between to_date(?, 'YYYY-MM-DD') and to_date(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')" + 
				" group by bname " + 
				" having count(*) = (" + 
				"    select max(mc) from (" + 
				"        select bName,count(*) as mc " + 
				"        from mvc_board " + 
				"        group by bName)) ";
		} else if(com.equals("maxComment")) {
			query = "select cid " + 
					" from comment_board " + 
					" where cDate between to_date(?, 'YYYY-MM-DD') and to_date(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') " +
					" group by cid " + 
					" having count(*) = ( " +
					" 	select max(mc) from ( " +
			        " 		select cid,count(*) as mc " +
			        " 		from comment_board " +
			        " 		group by cid)) ";
		}	
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			set = pstmt.executeQuery();
			if(set.next()) {
				if(com.equals("maxBoard")) {
					bName = set.getString("bName");
					result = bName;
				} else if(com.equals("maxComment")) {
					cId = set.getString("cId");
					result = cId;
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public String getCount(String com, String date, String today) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = null;
		String startDay = date;
		String endDay = today;
		String result = null;
		if(com.equals("maxBoard")) {
			query = "select count(*) as num " + 
				" from mvc_board " + 
				" where bDate between to_date(?, 'YYYY-MM-DD') and to_date(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') " +
				" group by bname " +
				" having count(*) = ( " +
				"    select max(mc) from ( " +
				"        select bName,count(*) as mc " + 
				"        from mvc_board " + 
				"        group by bName))" ;
		} else if(com.equals("maxComment")) {
			query = "select count(*) as num " + 
					" from comment_board " + 
					" where cDate between to_date(?, 'YYYY-MM-DD') and to_date(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') " +
					" group by cid " +
					" having count(*) = ( " +
					"    select max(mc) from ( " +
					"        select cid,count(*) as mc " + 
					"        from comment_board " + 
					"        group by cid))" ;
		}	
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			set = pstmt.executeQuery();
			if(set.next()) {
				result = set.getString("num");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public void blockMember(String searchId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update members " +
						"	set blockMember = '1' " +
						" where id = ? ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchId);
			pstmt.executeUpdate();
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
