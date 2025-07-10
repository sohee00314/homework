package com.sinse.news_web.model;

import lombok.Data;
/**
 * News테이블의 모델
 * */
@Data
public class News {
	private int news_id;//news의 아이디
	private String title;//제목
	private String writer;//글쓴이
	private String content;//내용
	private String regdate;//업로드날짜
	private int hit;//조회수
	
}
