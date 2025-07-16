package com.sinse.news_web.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.news_web.repository.NewsDAO;
import com.sinse.news_web.web.servlet.Controller;

public class DeleteController implements Controller{
	Logger logger = LoggerFactory.getLogger(getClass());
	NewsDAO newsDAO = new NewsDAO();
	@Override
	public void execue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("news_id");
		newsDAO.delete(Integer.parseInt(id));
		
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/news/delete.view";
	}

}
