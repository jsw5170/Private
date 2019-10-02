package com.study.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.project.command.BPageInfo;
import com.study.project.dto.BDto;

public class BDao {
	DataSource dataSource;
	private static BDao instance = new BDao();
	
	int listCount = 5;	// 한 페이지당 보여줄 게시물의 갯수
	int pageCount = 10;	// 하단에 보여줄 페이지 리스트이 개수
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BDao getInstance() {
		return instance;
	}
	public void write(String bName, String bTitle, String bContent, String bType, String path) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			if(path == null) {
			String query = "insert into mvc_board " +
							"(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, board_type) " +
							" values " +
							" (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, ?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setString(4, bType);
				int rn = pstmt.executeUpdate();
			} else {
				String query = "insert into mvc_board " +
								"(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, board_type, filePath) " +
								" values " +
								" (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, ?, ?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setString(4, bType);
				pstmt.setString(5, path);
				int rn = pstmt.executeUpdate();
			}
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
	public ArrayList<BDto> list(int curPage, String bType, String col, String search) {

		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		try {
			con = dataSource.getConnection();
			if(col == null) {
				String query = "select * " +
								" from ( " +
								"	select rownum num, A.*  " +
								"		from ( " +
								"			select *  " +
								"				from mvc_board where board_type = ?" +
								"			order by bgroup desc, bstep asc ) A " +
								"	where rownum <= ? ) B " +
								" where B.num >= ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,bType);
				pstmt.setInt(2,nEnd);
				pstmt.setInt(3,nStart);
				resultSet = pstmt.executeQuery();
			} else if (col.equals("bName")){
				String query = "select * " +
								" from mvc_board " +
								" where bName LIKE ? and board_type = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,"%" + search + "%");
				pstmt.setString(2,bType);				
				resultSet = pstmt.executeQuery();
			} else if (col.equals("bTitle")){
				String query = "select * " +
								" from mvc_board " +
								" where bTitle LIKE ? and board_type = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,"%" + search + "%");
				pstmt.setString(2,bType);				
				resultSet = pstmt.executeQuery();
			} else if (col.equals("bContent")){
				String query = "select * " +
							" from mvc_board " +
							" where bContent LIKE ? and board_type = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,"%" + search + "%");
				pstmt.setString(2,bType);				
				resultSet = pstmt.executeQuery();
			}
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String filepath = resultSet.getString("filepath");
				int bLike = resultSet.getInt("bLike");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent, filepath,bLike);
				
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
	public BPageInfo articlePage(int curPage, String bType, String col, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10;	// 하단에 보여줄 페이지 리스트이 개수
		
		// 총 게시물의 갯수
		int totalCount = 0;
		
		try {
			con = dataSource.getConnection();
			if(col == null) {
			String query = "select count(*) as total from mvc_board where board_type = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,bType);
			resultSet = pstmt.executeQuery();
			} else if (col.equals("bName")){
				String query = "select count(*) as total " +
								" from mvc_board " +
								" where bName LIKE ? and board_type = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,"%" + search + "%");
				pstmt.setString(2,bType);				
				resultSet = pstmt.executeQuery();
			}  else if (col.equals("bTitle")){
				String query = "select count(*) as total " +
								" from mvc_board " +
								" where bTitle LIKE ? and board_type = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,"%" + search + "%");
				pstmt.setString(2,bType);				
				resultSet = pstmt.executeQuery();
			}  else if (col.equals("bContent")){
				String query = "select count(*) as total " +
								" from mvc_board " +
								" where bContent LIKE ? and board_type = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,"%" + search + "%");
				pstmt.setString(2,bType);				
				resultSet = pstmt.executeQuery();
			} 
			while(resultSet.next()) {
				totalCount = resultSet.getInt("total");
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
		// 총 페이지 수 
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) 
			totalPage++;
		
		// 현재 페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	public BDto contentView(String strId) {
		upHit(strId);
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strId));
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String filepath = resultSet.getString("filepath");
				int bLike = resultSet.getInt("bLike");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent, filepath, bLike);
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
		return dto;
	}
	public void modify(String bId, String bName, String bTitle,String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board " +
					"	set bName = ?, " +
					" 		bTitle = ?, " +
					"		bContent = ? " + 
					" where bId = ?";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
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
	private void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
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
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
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
	public BDto reply_view(String str) {
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String filepath = resultSet.getString("filepath");
				int bLike = resultSet.getInt("bLike");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent, filepath, bLike);
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
		return dto;
	}
	public void reply(String bId, String bName, String bTitle, String bContent, 
			String bGroup, String bStep, String bIndent, String bType) {
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
							"(bId, bName, bTitle, bContent, bGroup, bStep, bIndent, board_type) " +
							" values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			pstmt.setString(7, bType);
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
	private void replyShape(String strGroup, String strStep) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board " +
						"	set bStep = bStep + 1 " + 
						" where bGroup = ? and bStep > ?";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
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
	public void like(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bLike = bLike + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
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
}
