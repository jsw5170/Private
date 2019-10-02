package com.study.project.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.project.command.MAllMemberCommand;
import com.study.project.command.MBlockMemberCommand;
import com.study.project.command.MCommand;
import com.study.project.command.MDeleteBoardCommand;
import com.study.project.command.MDeleteMemberCommand;
import com.study.project.command.MGetMemberCommand;
import com.study.project.command.MMaxValueCommand;

@WebServlet("*.mo")
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MFrontController() {
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
		MCommand command = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		String sel = request.getParameter("com");
		System.out.println(sel);
		if(sel.equals("allMember")) {
			command = new MAllMemberCommand();
			command.execute(request, response);
			viewPage = "manager.jsp";
		} else if(sel.equals("getMember")) {
			command = new MGetMemberCommand(); 
			command.execute(request,response);
			viewPage = "manager.jsp"; 
		} else if(sel.equals("deleteMember")) {
			command = new MDeleteMemberCommand(); 
			command.execute(request,response);
			viewPage = "manager.jsp"; 
		} else if(sel.equals("deleteBoard")) {
			command = new MDeleteBoardCommand(); 
			command.execute(request,response);
			viewPage = "manager.jsp"; 
		} else if(sel.equals("maxBoard")) {
			command = new MMaxValueCommand(); 
			command.execute(request,response);
			viewPage = "manager.jsp"; 
		} else if(sel.equals("maxComment")) {
			command = new MMaxValueCommand(); 
			command.execute(request,response);
			viewPage = "manager.jsp"; 
		} else if(sel.equals("blockMember")) {
			command = new MBlockMemberCommand(); 
			command.execute(request,response);
			viewPage = "manager.jsp"; 
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
