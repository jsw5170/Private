package com.study.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.dao.CDao;
import com.study.project.dto.CDto;

public class CommentReplyCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		 // 파라미터를 가져온다.
        String cNum = request.getParameter("cNum");
        int bId = Integer.parseInt(request.getParameter("bId"));
        String cId = request.getParameter("cId");
        String cContent = request.getParameter("cContent");
 
        CDao dao = CDao.getInstance();                
        dao.cWrite(cNum, bId, cId, cContent);
	}
}
