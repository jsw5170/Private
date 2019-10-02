package com.study.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/modifyProcess")
public class modifyProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		DTOEx dto = new DTOEx();
		
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		dto.setId(id);
		dto.setPw(pw);
		dto.seteMail(eMail);
		dto.setAddress(address);
		
		String json_data = "";
		DAOEx dao = DAOEx.getInstance();
		int ri = dao.updateMember(dto);
		if(ri == 1) {
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			json_data = "{\"code\":\"success\", \"desc\":\"정보가 수정되었습니다.\"} ";
		} else {
			json_data = "{\"code\":\"fail\", \"desc\":\"정보수정에 실패했습니다.\"} ";
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();		
	}
}
