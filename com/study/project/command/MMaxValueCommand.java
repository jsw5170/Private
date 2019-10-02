package com.study.project.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.project.DTOEx;
import com.study.project.dao.MDao;

public class MMaxValueCommand implements MCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		MDao dao = MDao.getInstance();
		String searchDate = request.getParameter("date");
		String com = request.getParameter("com");
		
		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		today = calendar.getTime();
		
		Date date = new Date();
		if(searchDate.equals("week")) {
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			date = calendar.getTime();
		} else if(searchDate.equals("month")) {
			calendar.add(Calendar.MONTH, -1);
			date = calendar.getTime();
		} else if(searchDate.equals("year")) {
			calendar.add(Calendar.YEAR, -1);
			date = calendar.getTime();
		}
		String name = dao.maxValue(com, new SimpleDateFormat("yyyyMMdd").format(date),new SimpleDateFormat("yyyyMMdd").format(today));
		String count = dao.getCount(com, new SimpleDateFormat("yyyyMMdd").format(date),new SimpleDateFormat("yyyyMMdd").format(today));
		request.setAttribute("maxValue", name);
		request.setAttribute("maxCount", count);
	}
}
