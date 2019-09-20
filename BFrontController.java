package com.study.project.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.project.command.BCommand;
import com.study.project.command.BContentCommand;
import com.study.project.command.BDeleteCommand;
import com.study.project.command.BListCommand;
import com.study.project.command.BModifyCommand;
import com.study.project.command.BReplyCommand;
import com.study.project.command.BReplyViewCommand;
import com.study.project.command.BWriteCommand;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request,response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}
		if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "Notice.do";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "referenceRoom.do";
		} else if(com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		} else if(com.equals("/Notice.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "Notice.jsp";
		} else if(com.equals("/referenceRoom.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "referenceRoom.jsp";
		} else if(com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		} else if(com.equals("/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
		} else if(com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		} else if(com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do?page=" + curPage;
		} else if(com.equals("/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
		} else if(com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do?page=" + curPage;
		} else if(com.equals("/main.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		}  
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
