<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성 페이지(테스트 단계)</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
          crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5PtkFExj5u9bOyDDn5a+3pu8L+I2LZ"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>        
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyldQxFbSTFpCR78dt4vgLSF6g6yo"
        crossorigin="anonymous"></script>
	<script type="text/javascript" src="/resources/js/createBoardjsfile.js"></script>   
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
	.thumgrid {
  		display: grid;
  		grid-template-columns: repeat(8, 1fr);
  		grid-template-rows: repeat(5, 1fr);
    	overflow-y: scroll;
    	height:0px;
	}
	.filelist{
    	overflow-y: scroll;
    	height:0px;
	}
	
	.result_img_file{
  		display: flex;
  		flex-direction: column;  
  		align-items: center; 
		margin-left: 20px;
	    width: 100%;
  		height: 100%;
	}
	.result_img{
		margin-top: 10px;
		margin-width: 500px;
		margin-height:500px;
	}
	.file_btn{
		margin-top: 20px;
	}
	.file-modal-content{
	}
	.modal_img_file{
  		display: flex;
  		flex-direction: column;  
  		align-items: center; 
		margin-left: 20px;
	    width: 100%;
  		height: 100%;
	}
	.modal_file_btn{
		margin-top: 20px;
	}
	.modal_thumimg{
		margin-top: 10px;
		max-width: 100px;
		max-height: 100px;
	}
	#fileresult_thumb{
  		display: grid;
  		grid-template-columns: repeat(10, 1fr);
  		grid-template-rows: repeat(5, 1fr);
    	overflow-y: scroll;
	}
	#fileUploadModal {
    	top: 50%;
    	transform: translateY(-50%);
    	width: 1000px;
    	height: 1300px;
    	position: absolute;
    	display: none;
    	left: 30%;
    	margin: 0;
  		justify-content: center;
  		align-items: center;
  		background-color: rgba(160, 160, 160, 1);
    	overflow-y: scroll;
	}
	#fileUploadModal_box {
	
	}
	#filemodalbody{
		margin-left: 50px;
		margin-right: 50px;
   		width: 900px;
    	height: 600px;
    	border: 2px solid #000;
    	overflow-y: auto;
	}
	#filemodalbtn{
		margin-top: 80px;
		margin-bottom: 0px;
		margin-left: 230px;
		margin-right: 230px;
	}
	#fileupload_reset,#fileupload_tupload,#fileupload_close{
		align-items:center; 
		text-align: center;
		margin-left:10px;
		width: 150px;
		height: 50px;
		padding: 10px 5px;	
  		cursor: pointer;
	}
	#fileresult_common{
		
	}
	
	</style>
</head>
<body style="overflow: auto">
	<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<sec:authentication property="principal" var="userinfo"/>
	
<div class="insert_top">
	<button id="backlistboard">메인 홈페이지로</button>
	<h2>게시물 작성</h2>
	
	<form role="form" action="/board/saveBoard" method="post">
	<h4>게시판 선택</h4>
		<div class="insert_group">
			<!-- 여기에는 현재 존재하는 게시판 중 하나를 선택할 수 있게 한다 -->
			<select name="boardname">
				<c:forEach var="boardname" items="${boardlist}">
					<option value="${boardname}">${boardname}</option>
				</c:forEach>
			</select>
		</div>
	<h4>제목</h4>
		<div class="insert_group">
			<textarea id="title" name="title" rows="1" cols="100" required></textarea><br><br>
		</div>
		
	<h4>내용</h4>
		<div class="insert_group">
			<textarea id="content" name="content" rows="80" cols="140" required></textarea><br>
		</div>
	
	<h4>작성자의 이름</h4>
		<div class="insert_group">
			<input type="text" id="writer" name="writer" value="${userinfo.username}" readonly><br>
		</div>		
		
		<button type="submit" class="btn_boardinsert" data-beforeunloads="false">작성완료</button>
	</form>
	
	<h4>첨부파일 (다시 업로드 버튼을 누르면 기존의 파일 목록이 초기화됩니다!!)</h4>
		<div class="file_upload">
			<div class="file_upload_attach">
				<button type="button" id="fileupload_btn" data-bs-toggle="modal" data-bs-target="#fileUploadModal">파일 업로드</button>
				<input type="file" id="inputfile" name="upload" multiple>
			</div>
			<div class="file_upload_result">
				<ul class="thumgrid" id="fileresult_thumb">
				
				</ul>
				<ul class="filelist" id="fileresult_common">
				
				</ul>
			</div>
		</div>	
</div>

<!-- 파일 재등록 여부 확인 모달창 -->
<!-- 
<div class="modal fade" id="fileminimodal" tabindex="-1" role="dialog" aria-hidden="true">
	<div role="document">
		<div>
			<div>
			<h5>파일 등록 창을 엽니다.(파일을 이미 등록한 경우, 기존에 등록된 파일이 초기화 됩니다!!!!)</h5>
			</div>
			<div>
			<button></button>
			<button></button>
			</div>
		</div>
	</div>
</div>
-->
 
<!-- 파일등록 모달창 -->
<div class="modal fade" id="fileUploadModal" tabindex="-1" role="dialog" aria-labelledby="fileUploadModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" id="fileUploadModal_box" role="document">
		<div class="file-modal-content">
			<div class="file-modal-header">
			<h5 class="file-modal-title" id="fileUploadModalLabel">파일을 드래그하거나 직접 업로드 버튼을 눌러서 업로드 해주세요</h5>
			</div>
			<div id="filemodalbody" class="file-modal-body">
			<!-- 파일 업로드 영역 -->
			
			</div>
			<input type="file" id="filemodaldirect" name="upload" multiple>
			<h5 class="file-modal-title">파일을 드래그하거나 직접 업로드 버튼을 눌러서 업로드 해주세요</h5>
			<h5 id="fileuplaodrule">zip, js, exe, sh, alz 형태의 확장자를 가진 파일은 업로드가 제한됩니다!!!<br>이미지 파일은 이미지 파일끼리 따로 업로드 해주세요!!!</h5>
			<div id="filemodalresult">
			<!-- 파일 업로드 리스트 영역 -->
				<ul class="thumgrid" id="filemodalresult_thumb">
				
				</ul>
				<ul class="filelist" id="filemodalresult_common">
				
				</ul>
			</div>
			<div id="filemodalfooter">
			<div id="filemodalbtn" class="fileupload_btn_set">
				<button id="fileupload_reset">File Reset</button>
				<button id="fileupload_tupload">File Temp Register</button>
				<button id="fileupload_close" data-dismiss="modal">Exit</button>
			</div>
			</div>
		</div>
	</div>
</div>		
<script>
</script>
</body>
</html>