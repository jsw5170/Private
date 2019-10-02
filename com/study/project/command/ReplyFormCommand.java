package com.study.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.dao.CDao;
import com.study.project.dto.CDto;

public class ReplyFormCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		 // 부모글의 글번호를 가져온다.
        int comment_num = Integer.parseInt(request.getParameter("num"));
 
        CDao dao = CDao.getInstance();
      //  CDto comment = dao.getComment(comment_num);
        
        // 댓글 정보를 request에 세팅한다.
   //     request.setAttribute("comment", comment);
	}
}
