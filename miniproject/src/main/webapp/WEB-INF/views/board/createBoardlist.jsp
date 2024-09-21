<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 개설</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
          crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
    <style>
    </style>
</head>
<body>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<sec:authentication property="principal" var="userinfo"/>
<h1><a href="listboard">메인 홈페이지로</a></h1>
<div class="boardlist_add">
	
	<h2>게시판 리스트에 추가</h2>
	
	<form action="/board/createBoardlistaction" method="post">
	
	<h4>게시판의 이름</h4>
		<div class="listinsert">
			<textarea id="boardname" name="boardname" rows="1" cols="100"></textarea>
		</div>
	<h4>게시판에 대한 설명</h4>	
		<div class="listinsert">
			<textarea id="boardsubject" name="boardsubject" rows="80" cols="140"></textarea>
		</div>
	<h4>게시판 개설자</h4>
		<div class="listinsert">
			<input type="text" id="reguserid" name="reguserid" value="${userinfo.username}" readonly><br>
		</div>
		
		<button type="submit" id="brdlist_btn">게시판 개설완료</button>
	</form>
</div>
</body>
</html>