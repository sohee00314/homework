package com.sinse.news_web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.news_web.repository.NewsDAO;

public class WriteServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//write.jsp에서 작성한 parameter 가져오기
		String tilte = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
	}

}
