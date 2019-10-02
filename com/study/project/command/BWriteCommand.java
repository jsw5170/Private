package com.study.project.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.study.project.dao.BDao;

public class BWriteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = null;
		session = request.getSession();
		String bName = (String) session.getAttribute("name");
		String bType = (String) session.getAttribute("bType");
		
		MultipartRequest multi = null;
		String path = request.getSession().getServletContext().getRealPath("/upload");
		
		int size = 1024 * 1024 * 10 ;	//10M
		String file = "";
		String oriFile = "";
		
		try{
			multi = new MultipartRequest(request, path, size,
											"UTF-8", new DefaultFileRenamePolicy());
			
			Enumeration files = multi.getFileNames();
			String str = (String)files.nextElement();
			
			file = multi.getFilesystemName(str);
			oriFile = multi.getOriginalFileName(str);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
		String regip = request.getRemoteAddr();
		
		BDao dao = new BDao();
		dao.write(bName,bTitle,bContent,bType,file);

	}
}
