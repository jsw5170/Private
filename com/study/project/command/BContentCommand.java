package com.study.project.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.project.dao.BDao;
import com.study.project.dao.CDao;
import com.study.project.dto.BDto;
import com.study.project.dto.CDto;

public class BContentCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		// 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
		CDao cDao = CDao.getInstance();
		int bNum = Integer.parseInt(bId);
        ArrayList<CDto> cList = CDao.cList(bNum);

        // 댓글이 1개라도 있다면 request에 cList를 세팅한다.
        if(cList.size() > 0)   	request.setAttribute("cList", cList);

		request.setAttribute("content_view", dto);
	}
}
