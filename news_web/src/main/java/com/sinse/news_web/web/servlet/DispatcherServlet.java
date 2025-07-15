package com.sinse.news_web.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View에서 요청을 주고 받는 대표 컨트롤러
 * 	<br>	=> Front Controller
 * */
public class DispatcherServlet extends HttpServlet{
	//log 호츨하는 객체
	Logger logger = LoggerFactory.getLogger(getClass());
	//key-value 쌍의 데이터를 이해할 수 있는 객체
	Properties props;
	@Override
	public void init(ServletConfig config) throws ServletException {
		//현재 Servlet을 호출하고 있는 환경(웹애플리케이션) 호출
		ServletContext context = config.getServletContext();
		//config-mapping.data이 있는 위치 호출(키 = contextConfigLocation)
		String realPath = context.getRealPath(config.getInitParameter("contextConfigLocation"));
		logger.debug("호출하고 있는 realPath는 "+realPath);
		//config-mapping.data 호출
		try(FileInputStream fis = new FileInputStream(realPath)){
			props = new Properties();
			//config-mapping.data 파일 안에 정의한 key-value 호출
			props.load(fis);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	/**
	 * Post, Get 모두 호출되는 메서드
	 * 모든 호출을 주고 받는 기능을 한다
	 * */
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 요청한 url 받아오는 객체
		String url = request.getRequestURI();
		//url(key)에 맞는 value(값)호출
		String className = props.getProperty(url);
		logger.debug("호출하고 있는 className은 "+className);
		
		try {
			//호출한 value에 맞는 클래스 정의
			Class clazz = Class.forName(className);
			//하위 컨트롤러 호출
			Controller controller = (Controller)clazz.newInstance();
			//하위 컨트롤러에서 받은 값 
			logger.debug("Controller에 대입할  request : "+request+" reponse : "+response);
			controller.execue(request, response);
			//하위 컨트롤러가 반환한 페이지 검색어 받기
			String viewName = controller.getViewName();
			//검색어(key)를 이용해서 실제 페이지 변환
			String viewPage = props.getProperty(viewName);
			//요청 유지하기
			if(controller.isForward()) {
				//요청에서 얻는 자원을 다른 컨트롤러로 전달하는 객체 생성
				RequestDispatcher dis = request.getRequestDispatcher(viewPage);
				//포워딩 시작(자원 전달)
				logger.debug("다시 반환 할 request : "+request+" reponse : "+response);
				dis.forward(request, response);
			}else {
				//요청 끝고 페이지 전환
				response.sendRedirect(viewPage);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
