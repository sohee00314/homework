<%@page import="java.util.List"%>
<%@page import="com.sinse.news_web.repository.NewsDAO"%>
<%@page import="com.sinse.news_web.domain.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	List<News> newsList = (List<News>) request.getAttribute("newsList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$("button").click(()=>{
			location.href="/news/write.jsp";
		});
	});
</script>
</head>
<body>

<h2>Today News</h2>
<p>테스트중</p>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>글쓴이</th>
    <th>업데이트 날짜</th>
    <th>조회수</th>
  </tr>
  <%  
  int num = 1;
  for(News news : newsList){
	%>
  <tr>
    <td><%= num++ %></td>
    <td><a href="/news/detail.do?news_id=<%= news.getNews_id() %>"><%= news.getTitle() %></a></td>
    <td><%= news.getWriter()%></td>
    <td><%= news.getRegdate() %></td>
    <td><%= news.getHit() %></td>
  </tr>
  <% } %>
  <tr>
  	<td colspan="5"><button>글등록</button></td>
  </tr>
</table>

</body>
</html>
