package com.study.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginProcess")
public class loginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id, pw, name = null;
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		name = request.getParameter("name");
		String json_data = "";
		DAOEx dao = DAOEx.getInstance();
		
		if(name != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("id", id);
			session.setAttribute("ValidMem", "yes");
			json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공.\"} ";	
		}else {
		int checkNum = dao.userCheck(id, pw);
		if (checkNum == -1) {
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"} ";
		} else if (checkNum == 0) {
			json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 틀립니다.\"} ";
		} else if (checkNum == 1) {
			DTOEx dto = dao.getMember(id);
			if(dto.getBlockMember().equals("1")) {
				json_data = "{\"code\":\"fail\", \"desc\":\"로그인 금지\"} ";
			} else {
				name = dto.getName();
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				session.setAttribute("id", id);
				session.setAttribute("pw", pw);
				session.setAttribute("ValidMem", "yes");
				json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공.\"} ";
			}	
		}
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
