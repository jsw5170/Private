package com.study.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.dao.CDao;
import com.study.project.dto.CDto;

public class CUpdateCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = null;
		session = request.getSession();
		int bId = Integer.parseInt(request.getParameter("bId")) ;
		int cNum = Integer.parseInt(request.getParameter("cNum")) ;
		String cContent = request.getParameter("cContent");
		
		CDao dao = CDao.getInstance();
		CDto dto = new CDto();
		dto.setcNum(cNum);
		dto.setcContent(cContent);
		
		dao.cUpdate(dto);
	}
}
