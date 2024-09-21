<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
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
	.logbtn_set{
		margin-top:20px;
	}
</style>
</head>
<body>

<button class="homebtn" data-href="/board/listboard">이전페이지로 돌아가기</button><br><br>

<form role="form" action="/loginaction" method="post">
	<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<h4>아이디</h4>
<div class="logingroup">
<input type="text" id="id" name="id" required><br>
</div>

<h4>비밀번호</h4>
<div class="logingroup">
<input type="password" id="passwd" name="passwd" required><br>

<!-- <input type="checkbox" name="remember-me">id remember<br> -->

</div>

<button id="loginbtn" type="submit">Login</button>
</form>

<div class="logbtn_set">
<!--<button id="googleloginbtn" class="memberbtn" data-href="/logingoogle">구글계정으로 로그인</button>-->
<button id="memberjoinbtn" class="memberbtn" data-href="/boardjoin">회원가입</button>
<button id="idsearchbtn" class="memberbtn" data-href="/idsearch">아이디/비밀번호 찾기</button>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
        <script>
        $(document).ready(function(){
        	var login=$('#loginbtn');
        	var backbtn=$('.homebtn');
        	var bottombtn=$('.memberbtn');
        	login.on("click",function(e){
        	    e.preventDefault();
        	    $("form").submit();
        	});
        	
        	backbtn.on("click",function(e){
        		window.location.href=backbtn.data("href");
        	})
        	bottombtn.on("click",function(e){
        		var btn=$(this).data("href");
        		window.location.href=btn;
        	});
        });
        </script>
</body>
</html>