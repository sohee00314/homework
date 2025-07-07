package com.sinse.news_web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.news_web.exception.NewsException;
import com.sinse.news_web.model.News;
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
	MybatisConfig config = new MybatisConfig();
	
	public List selectAll() {
		return null;
	}
	public List select() {
		return null;
	}
	public void insert(News news) throws NewsException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("News.insert", news);
		sqlSession.commit();
		sqlSession.close();
		if(result<1) {
			throw new NewsException("글 등록 실패");
		}
	}
	public void update() {
		
	}
	public void delete() {
		
	}
	
}
