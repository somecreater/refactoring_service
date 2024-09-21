<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
</head>
<body>
<h1>아이디가 존재하지 않거나, 비밀번호가 일치하지 않습니다.</h1>
<h1>아이디가 차단 상태 일수도 있습니다.</h1>
<button id="relogin" data-href="/loginboard">다시 로그인 하기</button>

<h4>차단 여부 확인하기</h4>
<div class="checkmemberdiv">
	<h5>아이디</h5>
	<input class="checkbanid" type="text">
	
	<h5>패스워드</h5>
	<input class="checkbanpass" type="password">
	
	<button class="checkbanbtn">아이디 체크</button>
</div>



<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){        	
        var chkbtn=$('.checkbanbtn');	
    	var btn=$('#relogin');
    	
        btn.on("click",function(e){
        	window.location.href=btn.data("href");
       	});
        
        chkbtn.on("click",function(e){
        	var chid=$('.checkbanid').val();
        	var chpass=$('.checkbanpass').val();
        	$.ajax({
    			type:'get',
    			url:'/checkban',
    			data:{userid:chid,userpass:chpass},
    			dataType:'json',
    			success: function(response){
    				if(response['result']==='success'){
    					var ban=response['bancontent'];
    					if(ban === null){
    						alert("차단 목록에 존재하지 않습니다.");
    					}else{
    						alert("차단 아이디: "+ban.userid+" 차단 시작일: "+displaytime(ban.startdate)+" 차단 종료일: "+displaytime(ban.enddate)+" 차단 사유: "+ban.banreason);
    					}
    				}else{
    					alert("아이디가 존재하지 않습니다.");
    				}
    			},
    			error: function(){
    				console.error('차단 내용 불러오기 실패');
    			}
        	});
        });
        	

    	function displaytime(time){
    		var date=new Date(time);
    		var year=date.getFullYear();
    		var month=('0'+(date.getMonth() + 1)).slice(-2); 
    		var day=('0'+date.getDate()).slice(-2); 
    		var formattedDate=year+'-'+month+'-'+day;
    		return formattedDate;

    	}	
	});
</script>
</body>
</html>