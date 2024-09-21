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
<title>마이 페이지</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
          crossorigin="anonymous">
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
		.totalmypage{
    		text-align: center;
		}
		.mypagerecordtab,.totalmypage{
			margin-top: 30px;
			list-style-type: none;
		}
		.infomypage-tab{
    		display: inline-block;
  			background-color: #000;
  			color: #fff;
  			cursor: pointer;
		}
		.select{
  			background-color: #e5e5e5;
  			color: #000;
		}
		.infomypage-tabcontent,.toppagetab{
   			display: none;
   			width:1500px;
    		padding: 10px;
		}
		.show{
    		display: block;
		}
		.boardrecordcontent,.commentrecordcontent{
    		padding: 10px;
    		text-align: center;
			max-height:1000px;
    		overflow-y: scroll;
		}
		.recordtable,.friend_div{
			max-height:1000px;
    		overflow-y: scroll;
		}
		
		#memberinfocontent{
    		text-align: center;
            width: 80%;
		}
		
		.board-record,.comment-record{
			table-layout:fixed;
  			border-spacing: 20px 20px;
  				text-align: center;
            width: 100%;
		}
		.file-record,.friend-record,.chat-record{
			table-layout:fixed;
  			border-spacing: 20px 20px;	
  				text-align: center;
			width: 100%;
		}
		.userinfo{
		}
		
		/*.boardrecord-title,.commentrecord-cmt {
			width: 300px;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}*/
		.etcmodal_register,.etcmodal_update,.etcmodal_delete,.chatmodal_create{
    		background-color: #f0f0f0;
    		padding: 10px; 
    		border: 1px solid #ccc; 
   			margin-bottom: 10px; 
   			margin-left:5px;
		}
		td{
			word-wrap:break-word;
		}
</style>
</head>
<body>
	<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<sec:authentication property="principal" var="principal"/>

	<div class="mypagetotal">

		<div class="mypagetop">
			<button id="homebtn" data-href="board/listboard">홈으로 돌아가기</button>
		</div>

		<div class="mypageinfo">

			<div class="mypagebody-btnset">
			<button id="commoninfo-btn">기본정보 보기</button>
			<button id="etcinfo-btn">기타정보 보기</button>
			<button id="mypage-friendbtn">친구 목록 보기</button>
			<button id="chatlist-btn">채팅방 목록 보기</button>
			</div>
			<ul class="totalmypage">
			<li class="toppagetab show" id="mypagecommon">
			<div class="mypageinfobody">
				<ul class="mypagerecordtab">
					<li class="infomypage-tab select" id="memberinfotab">
						나의 회원정보
					</li>

					<li class="infomypage-tab" id="boardrecordtab">
						작성글 기록
					</li>

					<li class="infomypage-tab" id="commentrecordtab">
						작성 댓글 기록
					</li>

					<li class="infomypage-tab" id="filerecordtab">
						업로드 파일 기록
					</li>
				</ul>

				<div class="infomypagerecordcontent">
					<div class="infomypage-tabcontent show" data-tabid="memberinfotab">
						<div id="memberinfocontent">
							<div>
								<h5>사용자의 아이디</h5><h3>${memberinfo.userid}</h3><br>
							</div>
							
							<div class="userinfo">							
								<h5>사용자의 이름</h5><h3>${memberinfo.username}</h3><br>
							</div>
							
							<div class="userinfo">
								<h5>사용자의 가입날짜</h5><h3>${memberinfo.regdate}</h3><br>						
							</div>
							
							<div class="userinfo">
								<h5>사용자의 휴대폰 번호</h5><h3>${memberinfo.phone}</h3><br>
							</div>
							
							<div class="userinfo">
								<h5>사용자의 정보 수정일자</h5><h3>${memberinfo.udate}</h3><br>							
							</div>
						</div>
					</div>

					<div class="infomypage-tabcontent" data-tabid="boardrecordtab">
						<p class="brdrecordsize">게시물의 총 개수는 ${boardrecordsize} 개 입니다.</p>
						<div class="boardrecordcontent">
						<table class="board-record">
							<thead>
								<tr>
									<th>게시물 번호</th>
									<th>게시판 이름</th>
									<th>게시물 제목</th>
									<th>게시물 작성일자</th>
									<th>게시물 수정일자</th>
									<th>삭제하기</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="board" items="${boardrecord}">
									<tr>
										<td>${board.bno}</td>
										<td>${board.boardname}</td>
										<td><div class="boardrecord-title"><a href="board/readBoard?bno=${board.bno}">${board.title}</a></div></td>
										<td>${board.regdate}</td>
										<td>${board.udate}</td>	
										<td><button class="recordbrd-deletebtn" data-bno="${board.bno}" data-href="board/directdeleteboard">삭제하기</button></td>									
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
						</div>						
					</div>

					<div class="infomypage-tabcontent" data-tabid="commentrecordtab">
						<p class="cmtrecordsize">댓글의 총 개수는 ${commentrecordsize} 개 입니다.</p>
						<div class="commentrecordcontent">
							<table class="comment-record">
								<thead>
									<tr>
										<th>댓글 번호</th>
										<th>게시물 번호</th>
										<th>댓글 내용</th>
										<th>등록 일자</th>
										<th>삭제하기</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="comment" items="${commentrecord}">
										<tr>
											<td>${comment.rno}</td>
											<td>${comment.bno}</td>
											<td><div class="commentrecord-cmt">${comment.comments}</div></td>
											<td>${comment.regdate}</td>
											<td><button class="recordcmt-deletebtn" data-rno="${comment.rno}">삭제하기</button></td>
										</tr>
									</c:forEach>
								</tbody>
								
							</table>
						</div>
					</div>

					<div class="infomypage-tabcontent" data-tabid="filerecordtab">
						<p class="filerecordsize">지금 까지 업로드한 파일의 총 개수는 ${filerecordsize} 개 입니다.</p>
						<div class="recordtable">
							<table class="file-record">
								<thead>
									<tr>
										<th>파일 코드</th>
										<th>파일의 uuid</th>
										<th>파일의 이름</th>
										<th>이미지 여부</th>
										<th>게시물 번호</th>
										<th>등록일자</th>
										<th>삭제하기</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="memberfile" items="${filerecord}">
										<tr>
											<td>${memberfile.pro_mem_file_code}</td>
											<td>${memberfile.uuid}</td>
											<td>${memberfile.fileName}</td>
											<td>${memberfile.image}</td>
											<td>${memberfile.bno}</td>
											<td>${memberfile.regDate}</td>
											<td><button class="recordfile-deletebtn" data-isimage="${memberfile.image}" data-uuid="${memberfile.uuid}" 
											data-filename="${memberfile.fileName}" data-uploadpath="${memberfile.uploadPath}">삭제하기</button></td>
										</tr>
									</c:forEach>
								</tbody>
								
							</table>
						</div>
						
					</div>
				</div>
			</div>
			</li>
			
			<li class="toppagetab" id="mypageetc">
				<div class="mypage_etc">
					<div class='infoetcdiv'>
							<div class='infoetcin'>
								<h4>유저의 이메일</h4>
								<p class='usermail'>${memberinfoetc.mail}</p>
							</div>
							<div class='infoetcin'>
								<h4>유저의 생년월일</h4>
								<p class='user_birthday'>${memberinfoetc.birth_date}</p>
							</div>			
                            <div class='infoetcin'>								
                            	<h4>유저의 자기소개</h4>
                            	<p class='user_aboutme'>${memberinfoetc.about_me}</p>
                            </div>
							<div class='infoetcin'>
								<h4>유저의 기타정보 등록일자</h4>
								<p class='userregdate'>${memberinfoetc.regdate}</p>
							</div>
							<div class='infoetcin'>
								<h4>유저의 기타정보 수정일자</h4>
								<p class='userudate'>${memberinfoetc.udate}</p>
							</div>
							<div class='infoetcset'>
								<button class='etcedit'>수정하기</button>
								<button class='etcreset'>초기화하기</button>
								<button class='etcregister'>기타정보 등록하기</button>
                       		</div>
					</div>					
				</div>
			</li>
			
			
			<li class="toppagetab" id="mypagefriend">
				<div>
					<p class="myfriend">${myid}</p>
					<p class="friend_count">현재 친구는 ${friendlistsize} 명 있습니다.</p>
					<p>상대방이 접속한 상태일때 1:1 채팅이 가능합니다.</p>
					<div class="friend_div">
							<table class="friend-record">
								<thead>
									<tr>
										<th>친구 코드</th>
										<th>친구의 아이디</th>
										<th>친구 추가 날짜</th>
										<th>친구 삭제</th>
										<!--<th>메세지 남기기</th> -->
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="friend" items="${friendlist}">
										<tr>
											<td>${friend.friend_code}</td>
											<td>${friend.fuserid}</td>
											<td>${friend.regdate}</td>
											<td><button class="friend_deletebtn" data-frdid="${friend.fuserid}">친구 삭제하기</button></td>
											<!--<td><button class="message_btn" data-frdid="${friend.fuserid}">메세지 남기기</button></td> -->
										</tr>
									</c:forEach>
								</tbody>
								
							</table>
					</div>
				</div>
			</li>
			
			<li class="toppagetab" id="chattinglist">
				<div>
					<p class="mychatid">${myid}</p>
					<p>채팅방을 보고 싶으면 채팅리스트 새로고침을 눌러주세요</p>
					<p>한사람당 하나만 채팅방 생성이 가능하고, 채팅방에 사람이 없으면 자동으로 삭제됩니다.</p>
					<button class="chatlistload">채팅방 목록 새로고침</button>
					<button class="chatcreatebtn">채팅방 새로 만들기</button>
					<div class="chat_div">
							<table class="chat-record">
								<thead>
									<tr>
										<th>채팅방 코드</th>
										<th>채팅방 제목</th>
										<th>채팅방 개설 아이디</th>
										<th>채팅방 입장</th>
									</tr>
								</thead>
								
								<tbody class="chatlist_tbody">
								
								</tbody>
								
							</table>
					</div>
				</div>
			</li>
			</ul>
			
		</div>

<!-- 기타정보 등록 모달창 -->
	<div class="etcmodal_register" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
		<div class="etcmodal_register_dialog" role="document">
			<div class="etcmodal_register_content">
				<div class="etcmodal_register_header">
					<button class="etcmodal_register_close">닫기</button>
				</div>
				<div class="etcmodal_register_body">
					<form action="/etcinsert" method="post">
						<div>
							<h2>기존에 등록된 기타정보가 있다면 등록이 되지 않습니다.</h2>
						</div>
						<div>
							<h4>회원의 아이디</h4>
							<input type="text" id="userid" name="userid" value="${principal.username}" readonly>
						</div>
						<div>
							<h4>회원의 이메일</h4>
							<textarea id="inputmail" name="mail" row="1" cols="100"></textarea>
						</div>
						<div>
							<h4>회원의 생년월일</h4>
							<textarea id="inputbirthdate" name="birth_date" row="1" cols="100"></textarea>
						</div>
						<div>
							<h4>회원의 자기소개</h4>
							<textarea id="inputaboutme" name="about_me" row="100" cols="100"></textarea>
						</div>
						<button class="etcsubmitbtn">등록하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
<!-- 기타정보 수정 모달창 -->	
	<div class="etcmodal_update" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
		<div class="etcmodal_update_dialog" role="document">
			<div class="etcmodal_update_content">
				<div class="etcmodal_update_header">
					<button class="etcmodal_update_close">닫기</button>
				</div>
				<div class="etcmodal_update_body">
					
						<div>
							<h4>회원의 아이디</h4>
							<input type="text" id="updateuserid" name="userid" value="${principal.username}" readonly>
						</div>
						<div>
							<h4>회원의 이메일</h4>
							<textarea id="updateinputmail" name="mail" row="1" cols="100">${memberinfoetc.mail}</textarea>
						</div>
						<div>
							<h4>회원의 생년월일</h4>
							<textarea id="updateinputbirthdate" name="birth_date" row="1" cols="100">${memberinfoetc.birth_date}</textarea>
						</div>
						<div>
							<h4>회원의 자기소개</h4>
							<textarea id="updateinputaboutme" name="about_me" row="100" cols="100" >${memberinfoetc.about_me}</textarea>
						</div>
					<button class="etcupdatebtn">수정하기</button>
				</div>
			</div>
		</div>
	</div>

<!-- 기타정보 삭제 모달창 -->	
	<div class="etcmodal_delete" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
		<div class="etcmodal_delete_dialog" role="document">
			<div class="etcmodal_delete_content">
				<div class="etcmodal_delete_header">
					<button class="etcmodal_delete_close">닫기</button>
					<p>정말로 정보를 초기화 하시겠습니까?</p>
				</div>
				<div class="etcmodal_delete_header">
					<button class="etcresetbtn">기타 정보 초기화</button>
				</div>
			</div>
		</div>
	</div>
	<div class="mypagebottom">
		<div class="mbtnset">
			<button id="moutbtn">회원 탈퇴</button>
		</div>
	</div>

</div>

<!--채팅방 생성 모달창 -->
	<div class="chatmodal_create" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
		<div class="chatmodal_create_dialog" role="document">
			<div class="chatmodal_create_content">
				<div class="chatmodal_create_header">
					<button class="chatmodal_create_close">닫기</button>
					<h4>채팅방 생성하기</h4>
				</div>
				<div class="chatmodal_create_body">
					<p>채팅방 제목</p>
					<textarea class="chatmodal_create_title" rows="1" cols="100"></textarea>
				</div>
				<div class="chatmodal_create_footer">
					<button class="chatcreateactbtn">채팅방 생성</button>
				</div>
			</div>
		</div>
	</div>
	
<!--하루에 3번으로 횟수에 제한을 두고 db에 저장해서 나중에 보여주는 형식으로 구현-->
<!--나중에 추후 구현 -->
<!--메세지 남기는 모달창 -->
<!-- 
	<div class="">
		<div>
			<div>
				<div>
				
				</div>
				<div>
				
				</div>
				<div>
				
				</div>
			</div>
		</div>
	</div>
-->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
	var tablist=$('.infomypage-tab');
	var tabcontentlist=$('.infomypage-tabcontent');
	var homebt=$('#homebtn');
	var memberoutbtn=$('#moutbtn');
	var toptablist=$('.toppagetab');
	var cmtabbtn=$('#commoninfo-btn');
	var etctabbtn=$('#etcinfo-btn');
	var friendtabbtn=$('#mypage-friendbtn');
	var chatlistbtn=$('#chatlist-btn');
	var brdrecordbtn=$('.recordbrd-deletebtn');
	var cmtrecordbtn=$('.recordcmt-deletebtn');
	var filerecordbtn=$('.recordfile-deletebtn');
	
	tablist.on("click",function(e){
		var tabid=$(this).attr('id');
		var tabobj=$('#'+tabid);
		var tabcontentobj=tabcontentlist.filter('[data-tabid="' + tabid + '"]');
		
		for(var i=0;i<tablist.length;i++){
			$(tablist[i]).removeClass('select');
			$(tabcontentlist[i]).removeClass('show');
		}
		
		tabobj.addClass('select');
		tabcontentobj.addClass('show');	
	});
	
	cmtabbtn.on("click",function(e){
		var toptabobj=$('#mypagecommon');
		var toptablist=$('.toppagetab');
		
		for(var i=0;i<toptablist.length;i++){
			$(toptablist[i]).removeClass('show');
		}
		toptabobj.addClass('show');
	});
	etctabbtn.on("click",function(e){
		var toptabobj=$('#mypageetc');
		var toptablist=$('.toppagetab');
		
		for(var i=0;i<toptablist.length;i++){
			$(toptablist[i]).removeClass('show');
		}
		toptabobj.addClass('show');
	});
	friendtabbtn.on("click",function(e){
		var toptabobj=$('#mypagefriend');
		var toptablist=$('.toppagetab');
		
		for(var i=0;i<toptablist.length;i++){
			$(toptablist[i]).removeClass('show');
		}
		toptabobj.addClass('show');
	});
	chatlistbtn.on("click",function(e){
		var toptabobj=$('#chattinglist');
		var toptablist=$('.toppagetab');
		for(var i=0;i<toptablist.length;i++){
			$(toptablist[i]).removeClass('show');
		}
		toptabobj.addClass('show');
	});
	
	var chatloadbtn=$('.chatlistload');
	chatloadbtn.on("click",function(e){
		$.ajax({
			type:'get',
			url:'/chatlist',
			dataType:'json',
			success: function(response){
				loadchatlist(response['chatlist']);
			},
			error: function(error){
				console.error("채팅창 목록을 불러오는것을 실패하였습니다.")
			}
		});
	});
	
	function loadchatlist(chatlist){
		var str="";
		var chattbody=$('.chatlist_tbody');
		chattbody.empty();
		for(var i=0;i<chatlist.length;i++){
			var chatobj=chatlist[i];
			str=str+'<tr>';
			str=str+'<td>'+chatobj.chatroom_code+'</td>';
			str=str+'<td>'+chatobj.chatroom_title+'</td>';
			str=str+'<td>'+chatobj.regid+'</td>';
			str=str+'<td><button class="chatparticate" data-code="'+chatobj.chatroom_code+'">채팅 참여하기</button></td>';
			str=str+'</tr>';
		}
		
		chattbody.append(str);
	}
	
	
	var chatmodal=$('.chatmodal_create');
	var chatcre=$('.chatcreatebtn');
	var chatmodalclose=$('.chatmodal_create_close')
	var chatcreatecation=$('.chatcreateactbtn');
	chatcre.on("click",function(e){
		chatmodal.css("display","block");
	});
	chatmodalclose.on("click",function(e){
		chatmodal.css("display","none");
	});
	chatcreatecation.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var chattitle=$(".chatmodal_create_title").val();
		console.log(chattitle);
		$.ajax({
			type:'post',
			url:'/chatcreation',
			data:{title: chattitle},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				chatmodal.css("display","none");
				alert(chattitle+" 채팅방을 생성하였습니다");
				var chatcode=response['chatcode'];
				var chatuser=response['user'];
				var chaturi="/chat?code="+chatcode;
				window.open(chaturi,"chat","width=1000, height=1200")
				//window.location.href="/chat?code="+chatcode;
			},
			error: function(error){
				chatmodal.css("display","none");
				console.error("채팅방 생성에 실패하였습니다.")
			}
		});
	});
	
	
	brdrecordbtn.on("click",function(e){
		var brdobj=$(this).data("bno")
		var csrfToken = $("#_csrf").val();
		$.ajax({
			type:'post',
			url:'/board/directremoveBoard',
			data:{bno: brdobj},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				console.log(response['result']);
				if(response['result']==="failure"){
					alert("해당 게시물 삭제에 실패하셨습니다");
				}
				else{
					alert(brdobj+" 번 게시물이 삭제되었습니다., 새로고침시 ui에 반영됩니다.");
				}
			},
			error: function(error){
				console.error(brdobj+" 번 게시물 삭제에 실패하였습니다.")
			}
		});
	});
	cmtrecordbtn.on("click",function(e){
		var cmtobj=$(this).data("rno")
		var csrfToken = $("#_csrf").val();
		$.ajax({
			type:'post',
			url:'/comment/deletecomment',
			data:{rno: cmtobj},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				console.log(response['result']);
				alert(cmtobj+' 번 댓글이 삭제되었습니다., 새로고침시 ui에 반영됩니다.');
			},
			error: function(error){
				console.error(cmtobj+" 번 댓글 삭제에 실패하였습니다.")
			}
		});
	});
	filerecordbtn.on("click",function(e){
		var filename=$(this).data("filename");
		var fileuuid=$(this).data("uuid");
		var filepath=$(this).data("uploadpath");
		var fileobjtype=$(this).data('isimage');
		
		var fileobjuri=encodeURIComponent(filepath+"/"+fileuuid+"_"+filename);
		var csrfToken = $("#_csrf").val();
		$.ajax({
			type:'post',
			url:'/deletefile',
			data:{fileuri:fileobjuri, filetype:fileobjtype},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				console.log(response['result']);
				alert("파일 기록 삭제가 정상적으로 되었습니다., 새로고침시 ui에 반영됩니다.");
			},
			error: function(error){
				console.error("파일 기록 삭제를 실패했습니다.");
			}
		});
	});

	
	var etcregimodal=$('.etcmodal_register');
	var etceditmodal=$('.etcmodal_update');
	var etcdelemodal=$('.etcmodal_delete');
	var etcregistermodalbtn=$('.etcregister');
	var etceditmodalbtn=$('.etcedit');
	var etcdeletemodalbtn=$('.etcreset');
	var etcreclose=$('.etcmodal_register_close');
	var etcedclose=$('.etcmodal_update_close');
	var etcdeclose=$('.etcmodal_delete_close');
	
	etcregistermodalbtn.on("click",function(e){		
		etcregimodal.css("display","block");
		etceditmodal.css("display","none");
		etcdelemodal.css("display","none");
	});
	etceditmodalbtn.on("click",function(e){
		etcregimodal.css("display","none");
		etceditmodal.css("display","block");		
		etcdelemodal.css("display","none");
	});
	etcdeletemodalbtn.on("click",function(e){
		etcregimodal.css("display","none");
		etceditmodal.css("display","none");
		etcdelemodal.css("display","block");
	});
	etcreclose.on("click",function(e){
		etcregimodal.css("display","none");
	});
	etcedclose.on("click",function(e){
		etceditmodal.css("display","none");
	});
	etcdeclose.on("click",function(e){
		etcdelemodal.css("display","none");
	});
	
	var etcupdatebtn=$('.etcupdatebtn');
	etcupdatebtn.on("click",function(e){
		//여기서 ajax 통신 이용해서 기타정보 수정을 하고, 페이지 재로딩	
		var csrfToken = $("#_csrf").val();
		var useretcid=$('#updateuserid').val();
		var useretcmail=$('#updateinputmail').val();
		var useretcaboutme=$('#updateinputaboutme').val();
		var useretcbirthdate=$('#updateinputbirthdate').val();
		
		var oldetcmail='${memberinfoetc.mail}';
		var oldetcaboutme='${memberinfoetc.about_me}';
		var oldetcbirth='${memberinfoetc.birth_date}';
		if(oldetcmail===useretcmail && oldetcaboutme===useretcaboutme && oldetcbirth===useretcbirthdate){
			alert("새로운 정보를 등록해주세요");
			return;
		}
		$.ajax({
			type:'post',
			url:'/etcupdate',
			data:{userid:useretcid, birthday:useretcbirthdate, mail:useretcmail, aboutme:useretcaboutme},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				if(response['result']==='success'){
					alert("기타정보를 수정하였습니다.");
					location.reload(true);
				}
				else if(response['result']==='violate'){
					alert("부적절한 접근입니다.");
				}
				else{
					alert("일부 정보의 형식이 맞지 않습니다. 다시 시도해 주세요");
				}
			},
			error: function(error){
				
				console.error("기타정보 수정을 실패했습니다.");
			}
		});
		
	});
	var etcreset=$('.etcresetbtn');
	etcreset.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var useretcid='${principal.username}';
		$.ajax({
			type:'post',
			url:'/etcdelete',
			data:{userid:useretcid},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				if(response['result']==='success'){
					alert("기타정보를 삭제하였습니다.");
					location.reload(true);
				}
				else if(response['result']==='violate'){
					alert("부적절한 접근입니다.");
				}
				else{
					alert("기타정보 삭제에 실패했습니다.");
				}
			},
			error: function(error){
				
				console.error("기타정보 삭제에 실패했습니다.");
			}
		});
	});
	
	var chatbtn=$(".chatting_btn");
	chatbtn.on("click",function(e){
		var myuserid=$(".myfriend").text();
		var frienduserid=$(this).data("frdid");
		
	});
	
	var frddelete=$(".friend_deletebtn");
	frddelete.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var myuserid=$(".myfriend").text();
		var frduserid=$(this).data("frdid");
		
		$.ajax({
			type:'post',
			url:'/deletefriend',
			data:{userid: myuserid, fuserid: frduserid},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
        		$(this).closest('tr').remove();
        		alert("친구 삭제 성공!!!");	
			},
			error: function(error){
				console.error("친구 삭제에 실패했습니다.");
			}
		});
		
	});
	//추후 구현
	/*
	var frdmsgbtn=$(".message_btn");
	frdmsgbtn.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var myuserid=$(".myfriend").text();
		var frduserid=$(this).data("frdid");
		
		//메세지 전송을 위한 모달창을 연다
	});
	*/	
	homebt.on("click",function(e){
		window.location.href=homebt.data("href")	
	});
	memberoutbtn.on("click",function(e){
		window.location.href='/boardout';
	});
});

$(document).on("click", ".chatparticate", function() {
    var chatroomCode = $(this).data("code");
    loadchatwindow(chatroomCode);
});

function loadchatwindow(code){
	var chatcode=code;
	var chatuser=$(".mychatid").text();
	//해당 채팅의 존재여부 확인
	$.ajax({
		type:'get',
		url:'/chatverification',
		data:{code: chatcode},
		dataType:'json',
		success: function(response){
			if(response['result']==='success'){
				var chaturi="/chat?code="+chatcode;
				console.log(chaturi);
				window.open(chaturi,"chat","width=1000, height=1200");
			}else{
				alert("해당 채팅방이 존재하지 않습니다.");
			}
		},
		error: function(error){
			console.error("채팅방 접속에 실패했습니다.");
		}
	});
}
</script>
</body>
</html>