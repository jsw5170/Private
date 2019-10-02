package com.study.project.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.command.BPageInfo;
import com.study.project.dao.BDao;
import com.study.project.dto.BDto;

public class BMainCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage = 1;
		try { 
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch(Exception e) {}
		String col = "1";
		col = request.getParameter("col");
        String search = "1";
        search = request.getParameter("search");
        
		BDao dao = BDao.getInstance();
		String bType = request.getParameter("bType");
		request.setAttribute("bType", bType);
		BPageInfo pinfo = dao.articlePage(nPage,bType, col, search);
		request.setAttribute("page", pinfo);
		// 세션에 btype 저장
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("bType", bType);
		
		ArrayList<BDto> dtos = dao.list(nPage,bType, col, search);
		request.setAttribute("list", dtos);
	}
}
