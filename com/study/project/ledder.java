package com.study.project;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ledder")
public class ledder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		String num = null;
		num = request.getParameter("num");
		int mem = Integer.parseInt(num);
		
		int[][] table = new int[10][mem];

		Random rand = new Random();
		for(int i = 0; i < mem*2 ; i++) {
			int row = rand.nextInt(10*mem) + 1;
			int j = row % 10;
			int k = row / 10;
			table[j][k] = 5;
		}
		for(int i =0;i<10; i++) {
			for(int j=0;j<mem;j++)
			{
				System.out.println(table[i][j]);
			}
			System.out.println();
		}
		HttpSession session = request.getSession();
		session.setAttribute("table", table);
	}

}
