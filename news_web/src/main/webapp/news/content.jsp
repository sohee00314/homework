<%@page import="com.sinse.news_web.domain.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% News news = (News)request.getAttribute("news"); %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$().ready(()=> {
			  $('#content').summernote({
				  height : 250
			  });
			  $('#content').summernote('code',"<%= news.getContent() %>");
		});
		
		//등록버튼에 수정기능 추가
		$("#update").click(()=>{
			$("form").attr({
				action : "/news/update.do",
				method : "POST"
			});
		$("form").submit();
		});
		
		//삭제 기능 추가
		$("#delete").click(()=>{
			$("form").attr({
				action : "/news/delete.do",
				method : "GET"
			});
			$("form").submit();
		});
		
		//목록으로 가는 버튼 이벤튼 연결
		$("#list").click(()=>{
			location.href="/news/list.do";
		});
	});
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
  	<input type="hidden" name="news_id" value="<%= news.getNews_id() %>">
    <label for="fname">Title</label>
    <input type="text" name="title" value="<%= news.getTitle() %>">

    <label for="lname">writer</label>
    <input type="text"  name="writer" value="<%= news.getWriter() %>">

    <label for="subject">Content</label>
    <textarea id="content" style="height:200px"></textarea>

    <input type="button" id = "update" value="edit">
    <input type="button" id = "delete" value="delete">
    <input type="button" id="list" value="List">
  </form>
</div>

</body>
</html>