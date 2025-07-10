package com.sinse.news_web.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 컨트롤러에게 상속하는 상위 컨트롤러
 * */
public interface Controller {
	/**
	 * DispatcherServlet 클래스 대신 request, response 호출들을 처리
	 * */
	public void execue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	/**
	 * 포워딩 할지 아니면 리다이렉트 할지 정하는 메서드
	 * true -> 포워딩
	 * false -> 리다이렉트
	 * */
	public boolean isForward();
	/**
	 * DispatcherServlet 클래스에서 요청을 반환할 페이지의 이름 검색할 수 있는 
	 * 검색어 반환하는 메서드
	 * */
	public String getViewName();
}
