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

public class DetailController implements Controller{
	NewsDAO newsDAO = new NewsDAO();
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String news_id = request.getParameter("news_id");
		logger.debug("얻은 news_id = "+news_id);
		News news = newsDAO.select(Integer.parseInt(news_id));
		logger.debug("선택한 데이터 = "+news);
		request.setAttribute("news", news);
		
		
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/news/detail.view";
	}

}
