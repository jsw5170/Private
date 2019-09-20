package com.study.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.project.dao.BDao;

public class BWriteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bType = request.getParameter("bType");
		System.out.println(bType);
		BDao dao = new BDao();
		dao.write(bName,bTitle,bContent,bType);

	}
}
