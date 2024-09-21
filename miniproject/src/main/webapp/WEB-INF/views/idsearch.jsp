<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 찾기</title>
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
        <script type="text/javascript" src="/resources/js/idsearchjsfile.js"></script>
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
	.idserchform,.passsearchform{
		margin-top: 100px;
  		background-color: rgba(160, 160, 160, 1);
	}
	.authac{
    	background-color: #f0f0f0;
    	padding: 10px; 
    	border: 1px solid #ccc; 
   		margin-bottom: 10px; 
   		margin-left:5px;
	}
</style>
</head>
<body>
<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<button id="backlogin" data-href="/loginboard">로그인 화면으로 돌아가기</button>

	<div class="idserchform">
		<div class="searchinput">
			<div>
				<h2>아이디 찾기</h2>
			</div>
			
			<div>
				<h4>사용하는 전화번호를 입력해주세요</h4>
				<input class="idphone" type="text">
			</div>
			<div>
				<h4>사용하는 이메일을 입력해주세요.</h4>
				<input class="idemail" type="text">
			</div>
			
			<div>
				<button class="idsearchbtn">아이디 확인</button>
			</div>
		</div>
		<div class="authac" id="authid"tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			<div class="authac_dialog" role="document">
				<div class="authac_body">
					<h4>인증번호를 입력하세요.(7자리)</h4>
					<input class="authnum" type="text">
					<button class="authbtn" id="idac">확인하기</button>
				</div>
			</div>
		</div>
		<div class="searchresult">
			<div class="youridresult">
			
			</div>
		</div>
	</div>
	
	<div class="passsearchform">
		<div class="searchinput">
			<div>
				<h2>비밀번호 찾기</h2>
			</div>
			<div>
				<h4>사용하는 아이디를 입력해주세요</h4>
				<input class="passid" type="text">
				<h4>사용하는 이메일을 입력해주세요</h4>
				<input class="passemail" type="text">
				<h4>사용하는 전화번호를 입력해주세요</h4>
				<input class="passphone" type="text">
			</div>
			
			<div>
				<button class="passsearchbtn">비밀번호 확인</button>
			</div>
		</div>
		<div class="authac" id="authpass" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			<div class="authac_dialog" role="document">
				<div class="authac_body">
					<h4>아이디를 다시 입력하세요</h4>
					<input class="idreinput" type="text">
					<h4>새로운 비밀번호를 입력하세요.(최소 8자 이상, 적어도 하나 이상의 특수문자 및 숫자 포함)</h4>
					<input class="passinput" type="text">
					<h4>새로운 비밀번호를 다시 입력하세요.(다시 입력)</h4>
					<input class="passreinput" type="text"><br>
					<button class="authbtn" id="psac">비밀번호 재등록하기</button>
				</div>
			</div>
		</div>
		<div class="searchresult">
			<div class="yourpassresult">
			
			</div>
		</div>
	</div>
	
</body>
</html>