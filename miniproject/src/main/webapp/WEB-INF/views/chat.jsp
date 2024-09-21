<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
          crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tone/14.8.49/Tone.min.js"></script>
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
       textarea{
			margin: 0;
			text-align: justify;
			font-size: 15px;
			resize: none;
		}
		.chatdiv{
			margin-top:20px;
			width: 800px;
			height: 800px;
    		border: 1px solid #ccc;
   			overflow-y: auto;
    		padding: 10px;
    		background-color: #f9f9f9;
		}
		.chatdiv .message{
			max-width: 100%;
    		background-color: #fff;
    		border-radius: 5px;
    		border: 1px solid #ccc;
    		padding: 5px 10px;
    		background-color: #f9f9f9;
   			margin-bottom: 10px;
		}
		.chatdiv .message:last-child{
    		margin-bottom: 0;
    	}
		.chatdiv .mymessage{
		 	background-color: #FFFFE0;
			text-align: right;
		}
		.chatdiv .othermessae{
			background-color: #CCCCCC;
			text-align: left;
		}
		.chatdiv .particate{
			background-color: #ADD8E6;
			text-align: center;
		}
		.chatdiv .exit{
			background-color: #FFC0CB;
			text-align: center;
		}
		.chatText{
			margin-top: 20px;
		}
	    .chatdiv .pmsg{
			word-wrap: break-word;
		}
		.chatremovemodal{
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
	<sec:authentication property="principal" var="userinfo"/>
	<button id="session_btn">close_btn</button>
	
	<sec:authorize access="${reguser eq userinfo.username}">
		<button id="chatremove">채팅방 폭파</button>
	</sec:authorize>
	
	<div class="chatremovemodal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
		<div class="chatremovemodal_dialog" role="document">
			<div class="chatremovemodal_content">
				<div class="chatremovemodal_header">
					<button id="chatremoveclose">닫기</button>
					<sec:authorize access="${reguser eq userinfo.username}">
					<p>채팅방을 폭파하시겠습니까?</p>
					</sec:authorize>
					<sec:authorize access="${reguser ne userinfo.username}">
					<p>권한이 없습니다.</p>
					</sec:authorize>
				</div>
				<div class="chatremovemodal_body">
					<sec:authorize access="${reguser eq userinfo.username}">
						<button id="chatremoveaction">채팅방 삭제</button>
					</sec:authorize>
					<sec:authorize access="${reguser ne userinfo.username}">
					
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
	
	<div class="chatroom_title">
		<h3 id="chatroomcode">${chatroomcode}</h3>
		<h3 id="chatowner">${myid}</h3>
		<h3 id="chatroom_id">${chatroomtitle}</h4>
	</div>
	<div class="chatdiv">
		<div></div>
	</div>
	<div class="chatText">
		<textarea id="msgcontent" rows="1" cols="80"></textarea>
		<button id="submitmsg">submit</button>
	</div>
	<div class="chathelperdiv">
		<h4>crtl key, enter key를 동시에 누를시 채팅이 전송됩니다.</h4>
	</div>
	
</body>
	<script>  
	let jssocket;
	let jsstomp;
	$(document).ready(function(){
		var sessionclosebtn=$("#session_btn");
		var chtrmcode="${chatroomcode}";
		var myid=$("#chatowner").text();
			jssocket=new SockJS("http://localhost:8080/stomp/chat");
			jsstomp=Stomp.over(jssocket);
			jsstomp.connect({}, function(frame) {
				console.log('Connected: ' + frame);
				
				jsstomp.subscribe("/sub/chat/"+chtrmcode,function(chat){
			        var msg=JSON.parse(chat.body);
			        var msgtype=msg.type;
			        var msgcontent=msg.content;
			        var msgwritter=msg.userid;
			        var msgdate=msg.regdate;
			        var msgdate="TIME: "+getlistenTime(msgdate);

			        var str="";
			        if(msgwritter===myid){
			        	
			        }else{
			        if(msgtype === "nomal"){
			            str=str+'<div class="message othermessae">';
			            str=str+'<h4>'+msgwritter+'</h4>';
			            str=str+'<p class="pmsg">'+msgcontent+'</p>';
			            str=str+'<p>'+msgdate+'</p>';
			            str=str+'</div>';
				        chatsound();
			        }
			        else if(msgtype === "particate"){
			            str=str+'<div class="message particate">';
			            str=str+'<h4>'+msgwritter+'</h4>';
			            str=str+'<p class="pmsg">'+msgcontent+'</p>';
			            str=str+'<p>'+msgdate+'</p>';
			            str=str+'</div>';
				        chatinsound();
			        }
			        else if(msgtype === "removechatroom"){
			    		alert("채팅방이 삭제되었습니다");
			        	chatromeextinction();
				        chatoutsound();
			        }
			        else{
			            str=str+'<div class="message exit">';
			            str=str+'<h4>'+msgwritter+'</h4>';
			            str=str+'<p class="pmsg">'+msgcontent+'</p>';
			            str=str+'<p>'+msgdate+'</p>';
			            str=str+'</div>';
				        chatoutsound();
			        }
			        }
			        $('.chatdiv').append(str);
			        scrollToBottom();
			    });
				
				var particater=$("#chatowner").text();
	            var date=new Date();
	        	var strday = getTime();
	    		var strday = '   TIME: ' + strday;
	    		var partication="채팅에 참여하였습니다.";

	    		var chatdiv=$(".chatdiv");
	            var str="";
	    		str=str+'<div class="message particate">';
	    		str=str+'<h4>'+particater+'</h4>';
	    		str=str+'<p class="pmsg">'+partication+'</p>';
	    		str=str+'<p>'+strday+'</p>';
	    		str=str+'</div>';
	    		chatdiv.append(str);
	    		//var particatemsg=particater+"|"+partication;
	    		//json 형태로 전송?
	    		var particatemsg={
	    				"roomcode": chtrmcode,
	    				"userid": particater,
	    				"content": particater+" 님이 "+partication,
	    				"type": "particate",
	    				"regdate": date
	    		}
	    		jsstomp.send("/pub/chat/message/"+chtrmcode,{},JSON.stringify(particatemsg));
				scrollToBottom();
				
			});	
			jsstomp.disconnect(function(){
				sessionclose();
			});	
	});
	
	$(document).on("click","#chatremove",function(){
		var chatrmmodal=$(".chatremovemodal");
		chatrmmodal.css("display","block");
		
	});
	$(document).on("click","#chatremoveclose",function(){
		var chatrmmodal=$(".chatremovemodal");
		chatrmmodal.css("display","none");
		
	});
	$(document).on("click","#chatremoveaction",function(){
		//채팅방 폭파 메세지를 전부에게 전송하고 강제로 해당 방과 관련된 세션을 끊어야한다
		var chtrmcode="${chatroomcode}";
		var csrfToken = $("#_csrf").val();
		$.ajax({
			type:'post',
			url:'/chatdelete',
			data:{code:chtrmcode},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				if(response['result']==='success'){
					alert(chtrmcode+" 채팅방 삭제");
					chatromeextinction();
				}
				else{
					alert("채팅방 삭제 권한이 없습니다.");
				}
			},
			error: function(error){
				console.error("채팅방 삭제 실패");
			}
		});
	});
	
	
	$(document).on("click","#submitmsg",function(){
		 sendmessage();
	});
	$(document).on("keydown","#msgcontent",function(event){
		if (event.ctrlKey && event.keyCode === 13) {
			sendmessage();
	        event.preventDefault();
	    }
	});
	function sendmessage(){
		var str="";
		var chatsender="나";
		var chatsenderid=$("#chatowner").text();
		var chatdiv=$(".chatdiv");
		var message=$("#msgcontent").val();
		var chtrmcode="${chatroomcode}";
		
		var current = new Date();
		var strday = getTime();
		var strday = '   TIME: ' + strday;
		
		$("#msgcontent").val("");
		str=str+'<div class="message mymessage">';
		str=str+'<h4>'+chatsender+'</h4>';
		str=str+'<p class="pmsg">'+message+'</p>';
		str=str+'<p>'+strday+'</p>';
		str=str+'</div>';
		chatdiv.append(str);

		//json 형태로 전송?
		var particatemsg={
    			"roomcode": chtrmcode,
    			"userid": chatsenderid,
    			"content": message,
    			"type": "nomal",
    			"regdate": current
    	}
    	jsstomp.send("/pub/chat/message/"+chtrmcode,{},JSON.stringify(particatemsg));				
		
		scrollToBottom();
	}
	$(document).on("click","#session_btn",function(){
		sessionclose();
		
	});
	window.addEventListener('beforeunload', function(event) {
	    sessionclose();
	});

	function sessionclose(){
		//창을 닫고 메세지 전송
		var chtrmcode="${chatroomcode}";
		console.log("연결 세션 종료");
		var exiter=$("#chatowner").text();
        var date=new Date();
    	var strday = getTime();
		var strday = '   TIME: ' + strday;
		var exit="채팅에서 나갔습니다.";
		var exitmsg={
				"roomcode": chtrmcode,
				"userid": exiter,
				"content": exiter+" 님이 "+exit,
				"type": "exit",
				"regdate": date
		}
		jsstomp.send("/pub/chat/message/"+chtrmcode,{},JSON.stringify(exitmsg));
		jsstomp.unsubscribe();
		window.close();
	}
	function chatromeextinction(){
		jsstomp.unsubscribe();
		window.close();
	}
	document.addEventListener('DOMContentLoaded', function() {
         Tone.start();
         console.log('Audio is ready');
     });
	function chatinsound(){
		const sound=new Tone.Synth().toDestination();
		const now = Tone.now();
		sound.triggerAttackRelease('C5', '8n', now);
		sound.triggerAttackRelease('D5', '8n', now + 0.2);
	}
	function chatsound(){
		const sound=new Tone.Synth().toDestination();
		sound.triggerAttackRelease('C4', '8n');
		
	}
	function chatoutsound(){
		const sound=new Tone.Synth().toDestination();
		const now = Tone.now();
		sound.triggerAttackRelease('C3', '8n', now);
		sound.triggerAttackRelease('B2', '8n', now + 0.2);
		
	}
	function getTime(){
		var currentDate = new Date();
		console.log(currentDate);
		var year = currentDate.getFullYear();
		var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
		var day = ('0' + currentDate.getDate()).slice(-2);
		var hours = ('0' + currentDate.getHours()).slice(-2);
		var minutes = ('0' + currentDate.getMinutes()).slice(-2);
		var seconds = ('0' + currentDate.getSeconds()).slice(-2);
		var strday = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;	
		return strday;
	}
	function getlistenTime(curtime){
		var currentDate = new Date(curtime);
		var year = currentDate.getFullYear();
		var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
		var day = ('0' + currentDate.getDate()).slice(-2);
		var hours = ('0' + currentDate.getHours()).slice(-2);
		var minutes = ('0' + currentDate.getMinutes()).slice(-2);
		var seconds = ('0' + currentDate.getSeconds()).slice(-2);
		var strday = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;	
		return strday;
	}
	function scrollToBottom() {
	    var chatdiv = $('.chatdiv');
	    chatdiv.scrollTop(chatdiv.prop("scrollHeight"));
	}
	</script>
</html>