<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardlist information</title>
    <!-- Bootstrap CSS -->
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
	<script type="text/javascript" src="/resources/js/selectBoardlistjsfile.js"></script>     
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
			.boardlisttabletop{
				display: flex;
    			text-align: center;
				width:1500px;
				padding:10px;
			}
			
			.boardlisttable{
    			padding: 10px;
    			text-align: center;
    			margin: 0 auto;
				max-height:1000px;
    			overflow-y: scroll;
			}
			#brdtable{
				table-layout:fixed;
  				text-align: center;
  				border-spacing: 20px 20px;
				width: 100%
			}
			.boardlistsubject{
				width:500px;
				
			}
			td{
				word-wrap:break-word;
			}
			
			.brdlisteditmodal{
    			background-color: #f0f0f0;
    			padding: 10px; 
    			border: 1px solid #ccc; 
   				margin-bottom: 10px; 
   				margin-left:5px;
			}
			
          </style>
</head>
<body>
	<sec:authentication property="principal" var="userinfo"/>
	<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	
	<div>
	<button onclick="location.href='listboard'">홈화면으로 돌아가기</button>
	</div>
	
	<div class="boardlisttabletop">
		<div class="boardlisttable">
			<table id="brdtable">
				<thead>
					<tr>
						<th>게시판 일련번호</th>
						<th>게시판 이름</th>
						<th>게시판 등록일자</th>
						<th>게시판 생성아이디</th>
						<th>게시판에 대한 설명</th>
						<th>게시판 관리자 목록</th>
						<th>게시판 수정버튼</th>
					</tr>
					<tbody id="boardlisttablebody">
					<c:forEach var="boardlist" items="${boardlistset}">
						<tr class="brdlisttable">
							<td>${boardlist.boardnum}</td>
							<td class="boardlistname">${boardlist.boardname}</td>
							<td>${boardlist.regdate}</td>
							<td>${boardlist.reguserid}</td>
							<td class="boardlistsubject">${boardlist.boardsubject}</td>
							<sec:authorize access="(hasAuthority('master') || hasAuthority('${boardlist.boardname}'))">
								<td class="brdlistauth"></td>
							</sec:authorize>
							<sec:authorize access="hasAuthority('${boardlist.boardname}') || hasAuthority('master')">
									<td><button class="brdlistupdatebtn">게시판 설명 수정하기</button></td>
							</sec:authorize>				
							<sec:authorize access="!hasAuthority('${boardlist.boardname}') && !hasAuthority('master')">
									<td></td>
							</sec:authorize>
						</tr>
					</c:forEach>
					</tbody>
				</thead>
			</table>
		</div>
	</div>
	
	<div class="brdlisteditmodal" id="editmodal" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true" style="display: none;">
		<div class="brdliedit" id="brdeditform" role="document">
			<div class="brdlieditinner">
				<div class="brdlieditheader">
					<button class="brdlieditclose">닫기</button>
					<h4 class="brdlistname"></h4>
					<h4>해당 게시판의 설명을 수정합니다.</h4>
				</div>
				<div class="brdlieditbody">
					<textarea id="brdlistsub" rows="10" cols="100" style="resize: none;"></textarea>
				</div>
				<div class="brdlieditfooter">
					<button class="brdlsiteditbtn">수정하기</button>
				</div>
			</div>
		</div>
	</div>	
<script>
</script>
</body>
</html>