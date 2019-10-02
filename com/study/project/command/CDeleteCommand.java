package com.study.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.dao.CDao;

public class CDeleteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int cNum = Integer.parseInt(request.getParameter("cNum"));
		
		CDao dao = CDao.getInstance();
		dao.cDelete(cNum);
	}
}
