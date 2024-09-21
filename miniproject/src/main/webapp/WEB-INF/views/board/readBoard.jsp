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
	<title><c:out value="${board.title}"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
          crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>        
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
	<style>
	.board_content{
    	border: 2px solid #000;
		width: 1200px;
		height: 600px;
	}
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
	.attachfileform {
		margin-left: 10px;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
	}
	.board_userinfo{
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
	}
	.comment_insert,.comment_obj,.imgmodalbtnform,.commodalbtnform,#comment_container,.commentedit_modal,.boardinfo_form{
    	background-color: #f0f0f0;
    	padding: 10px; 
    	border: 1px solid #ccc; 
   		margin-bottom: 10px; 
   		margin-left:5px;
	}
	.board_userid,.board_username{
		width:180px;
		height:140px;
    	background-color: #f0f0f0;
    	padding: 10px; 
    	border: 1px solid #ccc; 
   		margin-bottom: 10px; 
   		margin-left:5px;
	}
	.board_dateinfo{
  		display: flex;
 	    justify-content: space-around;
  		align-items: center;
	}
	.comment_obj{
		margin-top: -5px;
	}
	.comment_info {
  		display: flex;
 	    justify-content: space-around;
  		align-items: center;
	}
	.comment_class_page{
        display: flex;
        justify-content: center;
        margin-top: 20px;
        margin-bottom: 20px;
	}
	.commentpage{
        margin: 0 5px;
	}
	.imgfile_modal,.cmnfile_modal{
		background-color: #f0f0f0;
    	padding: 10px; 
    	border: 1px solid #ccc; 
   		margin-bottom: 10px; 
   		margin-left:5px;
		width: 1500px;
		hight: 1200px;
	}
	.imgfile_view{
  		display: flex;
 	    flex-direction: row;
  		overflow-x: scroll;
    	height:1200px;	
	}
	.cmnfile_view{
  		display: flex;
  		flex-direction: column;
  		overflow-y: scroll;
    	height:1200px;	
	}
	.img_obj{
		margin-top:20px;
		margin-left:20px;
	}
	.img_class{
		max-height: 1200px;
		max-width: 1200px;
	}
	.img_obj{
		margin-top:20px;
		margin-left:20px;
	}
	.img_class{
		max-height: 1200px;
		max-width: 1200px;
	}
	</style>
</head>
<body>
<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<sec:authentication property="principal" var="principal"/>

<button type="button" onclick="goBack()">이전 페이지로 되돌아가기</button>	

<sec:authorize access="!isAuthenticated()"></sec:authorize>
<sec:authorize access="isAuthenticated()">
<sec:authorize access="${board.writer ne principal.username}"></sec:authorize>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<div class="boardform">

	<sec:authorize access="${board.writer eq principal.username} or hasAuthority('master')">
	<button id="boardupdatebtn" type="button" data-href="updateBoard?bno=${board.bno}">게시물 수정하기</button>
	</sec:authorize>
	<sec:authorize  access="${board.writer eq principal.username} or hasAuthority('master') or hasAuthority('${board.boardname}')">
	<button id="boarddeletebtn" type="button" data-href="listboard">게시물 삭제하기</button>
	</sec:authorize>
	
</div>
</sec:authorize>

<div class="readcontent_top">

	<h4>제목</h4>
	<p>${board.title}</p>

	<h4>게시판 종류</h4>
	<p>${board.boardname}</p>

	<h4>게시글 내용</h4>
	<div class="board_content">
	<p><c:out value="${board.content}"/></p>
	</div>
	
	<div class="attachfileform">
		<div class="imgmodalbtnform">
		<h4>첨부 이미지 목록</h4>
		<p class="imgfilenumber"></p>
		<input type="button" class="imgfile_modal_btn"  data-bs-toggle="modal" data-bs-target="#imgfile_modal" value="해당 이미지들 보기"/><br><br>
		</div>
		
		<div class="commodalbtnform">
		<h4>일반 첨부 파일 목록</h4>
		<p class="comfilenumber"></p>
		<input type="button" class="comfile_modal_btn" data-bs-toggle="modal" data-bs-target="#cmnfile_modal" value="일반 첨부 파일 목록 보기" /><br><br>
		</div>
	</div>
		
	<div id="imgfile_modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">		
		<div class="modal-dialog modal-dialog-centered" role="document">
			<button id="imgfile_modal_close_btn" data-dismiss="modal">닫기</button>
			<div class="imgfile_content" >
			<div class="imgfile_view" id="imgfile_view">
			<!-- 사진의 제목, 사진 순으로 보여주고 옆으로 넘겨서 다음사진을 본다.-->
			
			</div>
			</div>
		</div>
	</div>

	<div id="cmnfile_modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">		
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="cmnfile_content">
			<button id="cmnfile_modal_close_btn" data-dismiss="modal">닫기</button>
			<div class="cmnfile_view" id="cmnfile_view">
			<!-- 일반 파일의 이름을 목차별로 보여준다. -->
			
			</div>
			</div>
		</div>
	</div>
	
	<div class="boardinfo_form">
		<div class="board_userinfo">
		<div class="board_userid">
		<h4>작성자의 아이디</h4>
		<p>${board.writer}</p>
		</div>
		<div class="board_username">
		<h4>작성자의 이름</h4>
		<p>${board.writer}</p>
		</div>
		</div>
		
		<div class="board_dateinfo">
		<h4>수정 날짜</h4>
		<p><fmt:formatDate value="${board.udate}" pattern="yyyy/MM/dd HH:mm:ss" /></p>

		<h4>작성 날짜</h4>
		<p><fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd HH:mm:ss" /></p>
		</div>	
	</div>
<br>
<sec:authorize access="isAuthenticated()">
<div class="comment_insert">
	<div class="comment_form">
	
		<form action="/comment/insertcomment" method="post">
			
			<input type="hidden" id="bno" name="bno" value="${board.bno}"/>
			<h5>댓글 작성자</h5>
		        <div class="comment_insert_group">
			    	<input type="text" id="writer" name="writer" value="${principal.username}" readonly><br>
			    </div>
			
			<h5>댓글 내용</h5>
				<div class="comment_insert_group">
					<textarea id="comments" name="comments" rows="1" cols="100"></textarea>
				</div>
			<button class="commment_insert_btn" type="submit">댓글 입력하기</button>
		</form>
		
	</div>
</div>
</sec:authorize>

<br>

<div id="comment_container" class="comment_class top">
	<div class="comment_class class">
	<h4>댓글 목록</h4>
	<input type="button" value="댓글 다시 불러오기" onclick="loadComments(1)"><br><br>
		<!-- 여기에는 댓글 페이지를 움직일시 다른 댓글을 보여주는 알고리즘 구현 board 페이지를 이용하면 될듯 -->
		<div class="comment_list">
			
		</div>
	</div>
	
	<div class="comment_class_page">
	
	</div>
	
</div>

<div class="commentedit_modal" id="comment_edit" tabindex="-1" role="dialog" aria-labelledby="commenteditmodal" aria-hidden="true" style="display: none;">
	<div class="commentedit_dialog" id="comment_edit_form" role="document">
		<div class="commentedit_content">
			<div class="commentedit_header">
				<h5 class="commentedit_modal_title" id="commenteditmodal">댓글 수정</h5>
			</div>
			<div class="commentedit_body">
				<form action="/comment/updatecomment" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<input type="hidden" id="bno" name="bno" value=""/>
					<input type="hidden" id="rno" name="rno" value=""/>
				<h5>댓글 작성자 아이디</h5>
		        	<div class="comment_update_group">
			    		<input type="text" id="writer_obj" name="writer" value="" readonly>
			    	</div>
			
				<h5>댓글 내용</h5>
					<div class="comment_update_group">
						<textarea id="comments_obj" name="comments" rows="1" cols="100" value=""></textarea>
					</div>
				
					<button type="submit">댓글 수정완료</button>
				<button type="button" id="modal_close_btn" class="commentedit_close_btn" data-dismiss="modal"  data-bs-target="#comment_edit" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
        $(document).ready(function(){
        	//최초로 게시글 읽기 실행시 댓글 1페이지 화면을 보여준다.
        	var imgmodalbtn=$('.imgfile_modal_btn');
        	var commonmodalbtn=$('.comfile_modal_btn');
        	var imgclosebtn=$('#imgfile_modal_close_btn');
        	var commonclosebtn=$('#cmnfile_modal_close_btn');
        	var imgmodal=$('#imgfile_modal');
        	var commonmodal=$('#cmnfile_modal');
        	var imgview=$('.imgfile_view');
        	var cmnview=$('.cmnfile_view');
			var pageNumValue = 1;
        	loadComments(pageNumValue);
        	loadattachfile();
        	loadbrdusername();
        	$("#boarddeletebtn").on("click",function(e){
        		
        		var csrfToken = $("#_csrf").val();
        		var bno=${board.bno};
        		$.ajax({
        			type:'post',
        			url:'removeBoard',
        			data:{bno: bno},
        	        beforeSend: function(xhr) {
        	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
        	        },
        			success:function(e){
                		var redirect=$("#boarddeletebtn").data("href");
                		window.location.href=redirect;
        				alert(bno+'번 게시물을 삭제하셨습니다');
        			},
        			error:function(error){
    					console.error('게시물 삭제 실패');
        			}
        		});
        	});
        	$("#boardupdatebtn").on("click",function(e){
        		window.location.href=this.dataset.href;
        	});
        	$(".commentedit_close_btn").on("click",function(e){
            	e.preventDefault();
            	console.log("cmtmodal_close_click");
        		const cmtmodal=document.getElementById("comment_edit");
        		cmtmodal.style.display="none";
        		
        	});
        	
        	imgmodalbtn.on("click",function(){
        		imgmodal.modal('show');
        	});
        	commonmodalbtn.on("click",function(){
        		commonmodal.modal('show');
        	});
        	imgclosebtn.on("click",function(){
	        	imgmodal.modal('hide');
		   	});
        	commonclosebtn.on("click",function(){
	        	commonmodal.modal('hide');
        	});
        	
        	cmnview.on("click","button",function(e){
        		var obj= $(this).closest(".cmn_obj");
        		
        		var cmnuri=encodeURIComponent(obj.data("fileuri")+"/"+obj.data("uuid")+"_"+obj.find("p").text());
        		self.location="/download?fileuri="+cmnuri;
        	});
			imgview.on("click","button",function(e){
				var obj=$(this).closest(".img_obj");
				
        		var imguri=encodeURIComponent(obj.data("fileuri")+"/"+obj.data("uuid")+"_"+obj.find("p").text());
        		self.location="/download?fileuri="+imguri;
        	});
			
        	imgview.on("click","img",function(e){
        		var obj=$(this).closest(".img_obj");
        		var imgsrc="/display?fileuri="+encodeURIComponent(obj.data("fileuri")+"/"+obj.data("uuid")+"_"+obj.find("p").text());
        		console.log(imgsrc);
        		window.open(imgsrc,'_blank');
        	});
			
        	
    		function loadattachfile(){
    			var fbno=${board.bno};
    			$.ajax({
    				type: 'get',
    				url: 'fileload',
    				data: {bno: fbno},
    				dataType: 'JSON',
    				success: function(response){
    					if(response['result'] == 'exist'){
    						var filelist=response['attachlist'];
    			        	imgmodal.modal('hide');
    			        	commonmodal.modal('hide');
    						loadattachfileview(filelist);
    					}else{
    			        	imgmodal.modal('hide');
    			        	commonmodal.modal('hide');
    						var imgcountform=$('.imgfilenumber');
    						var comcountform=$('.comfilenumber');
    						imgcountform.text('현재 이미지가 총 0 개 있습니다.');	
    						comcountform.text('현재 일반 파일이 총 0 개 있습니다.');
    					}
    				},
    				error: function(){
    					console.error('첨부파일 불러오기 실패');
    				}
    				
    			});
    		}
        	
        });
        
        
        
		function loadattachfileview(filelist){
			console.log('첨부파일이 총 '+filelist.length+'개 있습니다');
			var imgcountform=$('.imgfilenumber');
			var comcountform=$('.comfilenumber');
			var imgmodalview=$('.imgfile_view');
			var cmnmodalview=$('.cmnfile_view');
			
			var str="";
			var cstr=""
			var imgcount=0;
			var cmcount=0;
			
			//각각의 파일의 이미지 또는 
			for(var i=0;i<filelist.length;i++){
        		var imgmodalbtn=$('.imgfile_modal_btn');
        		var commonmodalbtn=$('.comfile_modal_btn');
				var achfile=filelist[i];
				if(achfile.image===true){
					imgcount=imgcount+1;
					var imgfile=achfile;
					var orgimglink=encodeURIComponent(imgfile.uploadPath+"/"+imgfile.uuid+"_"+imgfile.fileName);
					
					 str = str + '<div class="img_obj" data-fileuri="'+imgfile.uploadPath+'" data-filename="'+imgfile.fileName+'" data-uuid="'+imgfile.uuid+'" data-type="'+imgfile.image+'">';
					 str = str + '<p>'+imgfile.fileName+'</p>';
				   str = str + '<img class="img_class" data-uri="'+imgfile.uploadPath +'" src="/display?fileuri=' + orgimglink + '">';
				   str = str + '<button class="downloadbtn">Download</button>';
				   str = str + '</div>';
				}
				else{
					cmcount=cmcount+1;
					var cmnfile=achfile;
					 cstr = cstr + '<div class="cmn_obj" data-fileuri="'+cmnfile.uploadPath+'" data-filename="'+cmnfile.fileName+'" data-uuid="'+cmnfile.uuid+'" data-type="'+cmnfile.image+'">';
					 cstr = cstr + '<p>'+cmnfile.fileName+'</p>';
				     cstr = cstr + '<button class="downloadbtn">Download</button>';
				     cstr = cstr + '</div>';
				}	
			}

			imgcountform.text('현재 이미지가 총 '+imgcount+' 개 있습니다.');	
			comcountform.text('현재 일반 파일이 총 '+cmcount+' 개 있습니다.');
				imgmodalview.append(str);
				cmnmodalview.append(cstr);
			
		}
		
		
		
		function opendwindow(src){
			window.open(src);
		}
        function loadComments(pageNumValue){
        	var bnoValue = '<c:out value="${board.bno}"/>';
    		$.ajax({
    			type: 'get',
    			url: '/comment/readcommentlist',
    			data: {pagenum: pageNumValue, bno: bnoValue},
    			dataType:'JSON',
    			success: function(resultMap) {
    				var cmtpage=resultMap['cmtpage'];
    				var cmtcnt=resultMap['cmtcnt'];
    				var cmtamount=10;
					displaycomments(cmtpage);
					loadCommentspage(pageNumValue,cmtcnt,cmtamount);
					$(".commentusername").each(function() {
						var rno=$(this).attr("id").split('_')[1];
						var cmtuserid=$(this).text();
						loadcmtusername(rno,cmtuserid);
					});
				},
    			error: function(){
    				console.error('댓글 불러오기 실패');
    			}
    		});
    	}
    
    	function displaycomments(cmtpage){
      		var commentContainer=$('.comment_list');
      		commentContainer.empty();
      		var str="";
      		if(cmtpage==null||cmtpage.replyCnt==0){
      			return;
      		}
      		
      	  	var loggedInUser = '<%= request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "" %>';
      		$.each(cmtpage.list, function(index, comment) {

      			str = str + '<div class="comment_obj">';
      			str = str + '<div class="comment_info">';
      			str = str + '<h4>유저의 이름</h4>';
      			str = str + '<p class="commentusername" id="cmtnamerno_'+comment.rno+'">'+comment.writer+'</p>';
      			str = str + '<h4>유저의 아이디</h4>';
      			str = str + '<p>' + comment.writer + '</p>';
      			str = str + '<h4>댓글 작성 및 수정일</h4>';
        		str = str + '<p>' + formatDateToCustomString(comment.regdate) + '</p>';
        		str = str + '</div>';
      			str = str + '<h4>댓글의 내용</h4>';
        		str = str + '<p>' + comment.comments + '</p>';
        	if(loggedInUser !== '')
        	{
    			str = str + '<input type="button" value="댓글 삭제하기" onclick="removeComment('+ comment.rno +', \''+comment.writer+'\')">';
        		if(comment.writer === loggedInUser)
        		{      		
        			str = str + '<input type="button" value="댓글 수정하기" onclick="readCmt('+ comment.rno +', '+ comment.bno+')">';
        		}
        	}
        	str = str + '</div><br>';
      		}
      		);
      		commentContainer.append(str);
      		
      	}

    	function formatDateToCustomString(date) {
    	    const options = {
    	        year: 'numeric',
    	        month: '2-digit',
    	        day: '2-digit',
    	        hour: '2-digit',
    	        minute: '2-digit',
    	        second: '2-digit',
    	        hour12: false, // 24-hour format
    	    };

    	    const formattedDate = new Intl.DateTimeFormat('en-US', options).format(date);
    	    return formattedDate.replace(/(\d+)\/(\d+)\/(\d+), (\d+):(\d+):(\d+)/, '$3/$1/$2 $4:$5:$6');
    	}

    	function loadCommentspage(pageNumValue, cmtcnt, cmtamount){
    		var commentspageform=$('.comment_class_page');
    		console.log('댓글이 총 '+cmtcnt+'개 있습니다.');
    		
    		var endpage=Math.ceil(pageNumValue/cmtamount)*10;
    		var startpage=endpage-9;
    		var realendpage=Math.ceil(cmtcnt/cmtamount);
    		if(realendpage <= endpage){
    			endpage=realendpage
    		}

    		var prev=startpage>1;
    		var next=endpage<realendpage;
    		
    		//여기에서는 페이지 번호들을 생성을 한다 페이지 번호를 클릭시 게시물 번호와 해당 댓글 페이지 번호 정보를 이용해서 댓글 창을 출력한다	
    		var str = "";
    		commentspageform.empty();
    		if (prev) {
    		    str += '<li class="commentpage prev"><a href="#" onclick="loadComments(' + (startpage - 1) + ');"> prev </a></li>';
    		}
    		for (var num = startpage; num <= endpage; num++) {
    		    str += '<li class="commentpage btn"><a href="#" onclick="loadComments(' + num + ');">' + num + '</a></li>';
    		}
    		if (next) {
    		    str += '<li class="commentpage next"><a href="#" onclick="loadComments(' + (startpage + 1) + ');"> next </a></li>';
    		}
    		commentspageform.append(str);
    		
    	}
    	function loadbrdusername(){
        	var csrfToken = $("#_csrf").val();
        	var ruserid='${board.writer}';
        	var brdrealnameobj=$('.board_username');
    		$.ajax({
    			type:'get',
    			url:'/getuserinfoname',
    			data:{userid: ruserid},
    			dataType: 'json',
    	         beforeSend: function(xhr) {
      	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
      	         },
        		 success:function(result){
        			 var realusername=result['userrealname'];
        			 brdrealnameobj.find('p').text(realusername);
         		 },
         		 error: function(error){
         			console.error("유저정보 가져오기 실패"); 
       			 	brdrealnameobj.find('p').text("nothing");
         		 }
         			 
    			
    		});
    	}
    	function loadcmtusername(rno,userid){
        	var csrfToken = $("#_csrf").val();
    		$.ajax({
    			type:'get',
    			url:'/getuserinfoname',
    			data:{userid: userid},
    			dataType: 'json',
    	         beforeSend: function(xhr) {
      	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
      	         },
        		 success:function(result){
        			 var realusername=result['userrealname'];
        			 var usernameobj=$('#cmtnamerno_'+rno);
        			 usernameobj.text(realusername);
         		 },
         		 error: function(error){
         			console.error("유저정보 가져오기 실패"); 
       			 	var usernameobj=$('#cmtnamerno_'+rno);
       			 	usernameobj.text("nothing");
         		 }
         			 
    			
    		});
    		
    	}
    	
    	function removeComment(rno, writer){
    		var csrfToken = $("#_csrf").val();
    		var isuser=writer;
    		$.ajax({
    			type: 'post',
    			url: '/comment/deletecomment',
    			data: {rno: rno, userid: isuser},
    			dataType:'JSON',
    	        beforeSend: function(xhr) {
    	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
    	        },
    			success: function(response){
    				
    				console.log('댓글 삭제 결과: '+response['result']);
    				loadComments(1);
    			},
    			error: function(error){
    				console.error('댓글 삭제 실패');
    			}
    		});
    		
    	}
    	
    	//댓글 등록과 업데이트 코드
        function readCmt(rno, bno){
        		$.ajax({
        			type: 'get',
        			url: '/comment/readComment',
        			data: {rno: rno},
        			dataType:'JSON',
        			success: function(resultMap) {
        				//댓글을 rno를 통해서 읽어오는 것을 성공할 시 해당 결과값을 이용해서 modal 창을 출력
    					showupdatemodal(resultMap['comment'], bno);
    				},
        			error: function(){
        				console.error('댓글 항목 불러오기 실패');
        			}
        		});
        		
        	}
        function showupdatemodal(comment, bno){
        		//댓글 수정을 위한 모달창을 띄우고 모달창에서 저장버튼 누를시 바로 수정적용
        		const cmtmodal=document.getElementById("comment_edit");

        	    cmtmodal.querySelector('#writer_obj').value=comment.writer;
        	    cmtmodal.querySelector('#comments_obj').value=comment.comments;
        	    cmtmodal.querySelector('#rno').value=comment.rno;
        	    cmtmodal.querySelector('#bno').value=bno;
        		
        		cmtmodal.style.display="block";
        		
        	}
    	
        function goBack(){
        	window.history.back();
        }
        </script>
        
</body>
</html>