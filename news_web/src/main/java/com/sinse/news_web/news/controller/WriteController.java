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

public class WriteController implements Controller{
	NewsDAO newsDAO = new NewsDAO();
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		newsDAO.insert(news);
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/news/write.view";
	}

}
