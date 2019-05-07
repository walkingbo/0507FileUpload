<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<!-- form의 속성
	action은 서버가 처리할 URL 로 생략하면 이 페이지로 이동하게 만든 URL 그대로
	method는 파라미터 전송 방법인데 기본은 get 
	get은 url 뒤에 파라미터를 붙여서 전송하고 나머지 방식은 header에 포함시켜 전송
	password, file, textarea가 있으면 post로 전송
	
	enctype은 form 안에 file이 있을 때  multipart/form-data	로 지정
	
	id는 form의 이벤트를 처리하기 위해서 사용하는데 대부분 submit이벤트에서
	유효성 검사를 수행하기 위해서 사용
	
	 -->
	<form method="post" enctype="multipart/form-data" id="">
		<label for="writer">작성자</label>
		<!-- 이전 IE가 id를 생략하고 name을 설정하면 name을 id로 사용 -->
		<input type="text" required="required" id="writer" name="writer"/><br/>
	
		<label for="">첨부파일</label>
		<input type="file" id="file" name="file" /><br/>
		
		<input type="submit" value="파일 업로드"/>
	</form>
</body>
</html>