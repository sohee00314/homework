package com.sinse.news_web.news.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.news_web.domain.News;
import com.sinse.news_web.mybatis.MybatisConfig;
import com.sinse.news_web.repository.NewsDAO;
import com.sinse.news_web.web.servlet.Controller;
/**
 * 목록을 읽는 컨트롤러
 * */
public class ListController implements Controller{
	NewsDAO newsDAO = new NewsDAO();
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void execue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List newsList = newsDAO.selectAll();
		//key("newsList")로 값 newsList 보내기
		request.setAttribute("newsList", newsList);
	}
	
	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/news/list.view";
	}
	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true;
	}
}
