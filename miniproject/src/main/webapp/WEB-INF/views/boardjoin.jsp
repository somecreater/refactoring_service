<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script type="text/javascript" src="/resources/js/boardjoinjsfile.js"></script>
<style>
	button, input[type="button"]{
  		background-color: #000;
  		color: #fff;
  		padding: 10px 20px;
  		border: 1px solid #fff;
  		border-radius: 5px;
  		cursor: pointer;
	}
	input[type="button"]:hover,button:hover {
  		background-color: #e5e5e5;
  		color: #000;
	}
</style>
</head>
<body>

<div class="topbtnset">
<button id="backloginbtn" data-href="/loginboard">로그인 화면으로 돌아가기</button>
</div>
<form action="/boardjoinaction" method="post" role="form">
	<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<div>
<h3>비밀번호는 숫자,영문자, 특수문자를 포함하고 8자 이상이여야 하고,<br>휴대폰 번호는 11자여야 하고, 이름은 3자 이상이어야 합니다.<br>아이디 찾기와 비밀번호 찾기가 추후 가능하도록<br> 마이페이지에 들어가서 기타정보를 등록해주세요.</h3>
</div>
<div class="joinset" id="joinidset">
<h4>아이디</h4>
	<input type="text" id="id" name="id" required>
	<input type="checkbox" id="isidchk" name="isidchk" disabled>
	<button id="idcheck">아이디 존재여부 확인</button>
	<br>
</div>

<div class="joinset">
<h4>비밀번호</h4>
	<input type="text" id="passwd" name="passwd" required><br>
</div>

<div class="joinset">
<h4>사용자의 이름</h4>
	<input type="text" id="username" name="username" required><br>
</div>

<div class="joinset">
<h4>휴대폰 번호</h4>
	<input type="text" id="phone" name="phone" required><br>
</div>

<div class="joinset">
<h4>자동등록방지 숫자</h4>
	
</div>

</form>

<button id="joinbtn">회원가입 완료</button>

</body>
</html>