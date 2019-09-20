package com.study.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.DAOEx;
import com.study.project.DTOEx;

@WebServlet("/joinProcess")
public class joinProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public joinProcess() {
   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");

		DAOEx dao = DAOEx.getInstance();
		DTOEx dto = new DTOEx();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));

		String json_data = "";
		if (dao.confirmId(dto.getId()) == DAOEx.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 이미 존재 합니다.\"} ";
		} else {
			int ri = dao.insertMember(dto);
			if (ri == DAOEx.MEMBER_JOIN_SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());

				json_data = "{\"code\":\"success\", \"desc\":\"회원가입을 축하합니다.\"} ";
			} else {
				json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"} ";
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
