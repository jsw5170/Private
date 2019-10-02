package com.study.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.dao.CDao;

public class CWriteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = null;
		session = request.getSession();
		String cId = (String) session.getAttribute("name");
		int bId = Integer.parseInt(request.getParameter("bId")) ;
		String cParent = (String) session.getAttribute("cParent");
		String cContent = request.getParameter("cContent");
		
		CDao dao = new CDao();
		dao.cWrite(cId, bId, cParent, cContent);
	}
}
