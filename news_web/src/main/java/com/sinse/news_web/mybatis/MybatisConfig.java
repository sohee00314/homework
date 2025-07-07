package com.sinse.news_web.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory sqlSessionFactory;
	public MybatisConfig() {
		String resource = "com/sinse/news_web/mybatis/mybatis_config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static MybatisConfig getConfig() {
		if(instance == null) instance = new MybatisConfig();
		
		return instance;
	}
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
