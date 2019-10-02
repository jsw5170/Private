package com.study.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/JoinOutProcess")
public class JoinOutProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
	
		request.setCharacterEncoding("UTF-8");

		String id, pw;
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		
		DAOEx dao = DAOEx.getInstance();
		DTOEx dto = new DTOEx();
		dto.setId(id);
		dto.setPw(pw);
		
		String json_data = "";
		int checkNum = dao.userCheck(id, pw);
		if (checkNum == -1) {
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"} ";
		} else if (checkNum == 0) {
			json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 틀립니다.\"} ";
		} else if (checkNum == 1) {
			int ri = dao.deleteMember(dto);
			if (ri == DAOEx.MEMBER_JOIN_SUCCESS) {
				HttpSession session = request.getSession();
				session.invalidate();

				json_data = "{\"code\":\"success\", \"desc\":\"회원탈퇴 했습니다.\"} ";
			} else {
				json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원탈퇴에 실패했습니다.\"} ";
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
