package com.study.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.project.DTOEx;

public class DAOEx {
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static DAOEx instance = new DAOEx();
	
	public static DAOEx getInstance() {
		return instance;
	}
	public int insertMember(DTOEx dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values (?, ?, ?, ?, ?, ?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri = DAOEx.MEMBER_JOIN_SUCCESS;
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
		
		return ri;
	}
	
	public int deleteMember(DTOEx dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from members where id = ? and pw = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.executeUpdate();
			ri = DAOEx.MEMBER_JOIN_SUCCESS;
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
		
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = DAOEx.MEMBER_EXISTENT;
			} else {
				ri = DAOEx.MEMBER_NONEXISTENT;
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
		
		return ri;
	}
	public int snsCheck( String email) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where emil = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				ri = DAOEx.MEMBER_LOGIN_SUCCESS;	// 로그인 Ok
			} else {
				System.out.println("login fail no id");
				ri = DAOEx.MEMBER_LOGIN_IS_NOT;	// 아이디 X
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
		return ri;
	}
	public int userCheck( String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			System.out.println(id);
			System.out.println(pw);
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("login ok");	
					ri = DAOEx.MEMBER_LOGIN_SUCCESS;	// 로그인 Ok
				} else {
					System.out.println("login fail no pw");
					ri = DAOEx.MEMBER_LOGIN_PW_NO_GOOD;	// 비밀번호 X
				}
			} else {
				System.out.println(set.next());
				System.out.println("login fail no id");
				ri = DAOEx.MEMBER_LOGIN_IS_NOT;	// 아이디 X
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
		
		return ri;
	}
	public DTOEx getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where id = ?";
		DTOEx dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				dto = new DTOEx();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
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
	
	public int updateMember (DTOEx dto) {
		int ri = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update members set pw=?, eMail=?, address=? where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();			
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
		
		return ri;
	}
	public static Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		try {
			// lookup 함수의 파라메터는  context.xml에 설정된
			// name(jdbc/Oracle11g)과 동일해야 한다.
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
