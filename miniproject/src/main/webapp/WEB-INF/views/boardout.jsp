<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 탈퇴 및 타 계정연동 해제 </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
          crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="/resources/js/boardoutjsfile.js"></script>
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
        .boardoutscript{
        	margin-top: 100px;
    		text-align: center;
        }
        .adddeleteinfo,.userinput,.description{
        	margin-top:30px;
    		background-color: #f0f0f0;
    		padding: 10px; 
    		border: 1px solid #ccc; 
        }
        </style>
</head>
<body>
	<div class="boardouttop">
		<button id="backbtn" data-href="/myPage">마이 페이지로 돌아가기</button>
	</div>
<h2>회원 탈퇴 또는 계정연동 해제를 진행합니다.</h2>
<div class="boardoutscript">
	<div class="userinput">
		<h4>아이디</h4>
		<input type="text" class="deleteuserid">
		<h4>비밀번호</h4>
		<input type="text" class="deleteuserpass">
	</div>
	<div class="description">
		<h4>회원 탈퇴시, 회원가입시에 입력 되었던 정보 및 추가로 입력한 정보들이 삭제됩니다. 정말로 탈퇴하시겠습니까?</h4>
		<button class="boardoutbtn">회원 탈퇴</button>
	</div>
	<div class="adddeleteinfo">
		<h4>추가로 입력한 댓글, 게시글, 파일들도 삭제하겠습니까?</h4>
		<input id="chckdelete" type="checkbox">
	</div>
</div>
</body>
</html>