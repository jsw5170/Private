package com.study.project.command;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.project.dao.BDao;
import com.study.project.dto.BDto;

public class BDownloadCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		
		BDto dto = BDao.getInstance().contentView(bId);
		String filePath = dto.getfilePath();

		String uploadFileName = request.getSession().getServletContext().getRealPath("/upload/" + filePath);
		
		File downFile = new File(uploadFileName);
		String downName = null;
		
		if(downFile.exists() && downFile.isFile()) {
			try {
				long fileSize = downFile.length();
				response.setContentType("application/octet-stream");
				response.setContentLength((int)fileSize);
				
				String strClient = request.getHeader("user-agent");
				
				if(strClient.indexOf("MSIE") != -1) {
					downName = new String(filePath.getBytes("UTF-8"),"8859_1");
				} else {
					downName = new String(filePath.getBytes("EUC-KR"),"8859_1");
				}
				response.setHeader("content-Disposition","filename=" + filePath + ";");
				
				byte b[] = new byte[1024];
				FileInputStream fin = new FileInputStream(downFile);
				ServletOutputStream outs = response.getOutputStream();
				
				int read = 0;
				while((read = fin.read(b,0,b.length)) != -1) {
					outs.write(b,0,read);
				}
				outs.flush();
				outs.close();
				fin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Download Error : downFile Error [" + downFile + "]");
		}
	}
}
