package com.study.project.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.project.DTOEx;
import com.study.project.dao.MDao;

public class MDeleteBoardCommand implements MCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		MDao dao = MDao.getInstance();
		String searchId = request.getParameter("bId");
		System.out.println(searchId);
		dao.deleteBoard(searchId);
	}
}
