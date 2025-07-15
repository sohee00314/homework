package com.sinse.news_web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.news_web.domain.News;
import com.sinse.news_web.exception.NewsException;
import com.sinse.news_web.repository.NewsDAO;

public class WriteServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//write.jsp에서 작성한 parameter 가져오기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//가져오는지 확인하는 로그
		logger.debug("제목 : "+title);
		logger.debug("글쓴이 : "+writer);
		logger.debug("내용 : "+content);
		
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		try {
			newsDAO.insert(news);
			out.print("alert('글 등록 성공');");
			out.print("location.href='/news/list.jsp';");
			
		} catch (NewsException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
			out.print("alert('글 등록 실패');");
		}
		out.print("</script>");
		
	}

}
