package com.sinse.news_web.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.news_web.domain.News;
import com.sinse.news_web.repository.NewsDAO;
import com.sinse.news_web.web.servlet.Controller;

public class UpdateController implements Controller{
	Logger logger = LoggerFactory.getLogger(getClass());
	NewsDAO newsDAO = new NewsDAO();
	
	@Override
	public void execue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 가져오기
		String newsId = request.getParameter("news_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		logger.debug("얻은 정보 = news_id : " +newsId+" title : "+title+" writer : "+writer+" content : "+content);
		
		News news = new News();
		news.setNews_id(Integer.parseInt(newsId));
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		newsDAO.update(news);
		request.setAttribute("news", news);
		
	}

	@Override
	public boolean isForward() {
		return true;
	}

	@Override
	public String getViewName() {
		return "/news/update.view";
	}

}
