package com.sinse.news_web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.news_web.domain.News;
import com.sinse.news_web.exception.NewsException;
import com.sinse.news_web.mybatis.MybatisConfig;
/**
 * news테이블의 DAO 
 * -selectAll(테이블 모두조회) list로 리턴
 * -select(특정 한건만 조회) list로 리턴
 * -insert(한건 추가)
 * -update(수정)
 * -delete(삭제)
 * */
public class NewsDAO {
	//mybatis 호출
	MybatisConfig config = new MybatisConfig();
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * news 테이블 전체 검색
	 * */
	public List selectAll() throws NewsException{
		SqlSession sqlSession = config.getSqlSession();
		List list=sqlSession.selectList("News.selectAll");
		if(list.size()<=0) {
			throw new NewsException("전체 목록 조회 실패");
		}
		sqlSession.commit();
		sqlSession.close();
		logger.debug("news 태이블 데이터 모두 조회 성공 :"+list);
		return list;
	}
	/**
	 * news 테이블에 있는 데이터 1건 호출
	 * */
	public News select() {
		return null;
	}
	/**
	 * news 테이블에 데이터 1건 추가
	 * */
	public void insert(News news) throws NewsException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("News.insert", news);
		sqlSession.commit();
		sqlSession.close();
		if(result<1) {
			throw new NewsException("글 등록 실패");
		}
	}
	/**
	 * news 테이블에 데이터 1건 수정
	 * */
	public void update() {
		
	}
	/**
	 * news 테이블에 데이터 1건 삭제
	 * */
	public void delete() {
		
	}
	
}
