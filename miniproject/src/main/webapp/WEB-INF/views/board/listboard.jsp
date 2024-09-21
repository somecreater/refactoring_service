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
<title>this is world board</title>
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
	<script type="text/javascript" src="/resources/js/listboardjsfile.js"></script>      
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
		.hrefbtn{
			height:40px;
		}
     	.listboardtop{
     		display: flex;
     		justify-content: space-between;
     	}
        .boardlist {        	
    		background-color: #f0f0f0;
    		padding: 10px; 
    		border: 1px solid #ccc; 
   			margin-bottom: 10px; 
   			margin-left:5px;
            margin-top: 20px;
        }
        .boardmake_list,.loginuser_info{
    		background-color: #f0f0f0;
    		padding: 10px; 
    		border: 1px solid #ccc; 
   			margin-bottom: 10px; 
   			margin-left:5px;
        }
		.boardmake_create,.boardmake_delete,.boardmake_list,.member_setting{
			 float: left;
		}
		.right_div{
			margin-top: 30px;
			float: right;
		}
        .default_board_top {
            text-align: center;
        }

        .board_table {
            width: 100%;
        }
        
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .page-item {
            margin: 0 5px;
        }
    </style>
          
</head>
<body>
	<input id="_csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<sec:authentication property="principal" var="userinfo"/>
<div class="listboardtop">
	<div>
	<button class="hrefbtn" id="reloadlistboard" data-href="listboard">종합 게시판</button>
	</div>
<div>
	<sec:authorize access="!isAuthenticated()">
		<button class="hrefbtn" id="boardlogin" data-href="/loginboard">로그인</button>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
	<div class="loginuser_info">
		<sec:authorize access="hasAuthority('master')">
			<h3>관리자 입니다</h3>
		</sec:authorize>
		<h4 class="userId">${userinfo.username}</h4>
		<h4 class="username"></h4>
		<button class="boardmypage" data-href="/myPage">마이 페이지</button>
		<button class="boardlogout" data-href="/logoutaction">로그아웃</button>
	</div>
	</sec:authorize>
</div>

</div>	

	<div class="boardlist">
	<div class=default_board_top align="center">
		<table boarder="1" class="board_table">
			<thead>
			<tr>
				<th>#게시물 번호</th>
				<th>게시판 이름</th>				
				<th>게시물 제목</th>
				<th>작성자의 아이디(이름)</th>
				<th>등록 날짜</th>
				<th>수정 날짜</th>
				<th>댓글 숫자</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.bno}</td>					
					<td>${board.boardname}</td>
					<td><a href="readBoard?bno=${board.bno}"> ${board.title}</a></td>
					<td class="boardusername" id="writer_${board.bno}">${board.writer}</td>
					<td><fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${board.udate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					<td>${board.comment_num}</td>					
				</tr>
			</c:forEach>
			</tbody>
		</table>		
	</div>
	</div>
	<div class="pagination">
    <c:if test="${page.prev}">
        <li class="page-item previous">
            <a href="${page.startPage-1}">Prev </a>
        </li>
    </c:if>
    
    <c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
        <li class="page-item btn">
            <a href="${num}">${num}</a>
        </li>
    </c:forEach>
    
    <c:if test="${page.next}">
        <li class="page-item next">
            <a href="${page.endPage+1}"> Next</a>
        </li>
    </c:if>
	</div>
	
	<div class="boardsearch form">
	<form action="/board/listboard" method="get" class="boardsearch srh">			
		<select name="type">
			<option value="" <c:out value="${page.srh.type == null ? 'selected' : ''}"/>>--</option>
			<option value="G" <c:out value="${page.srh.type == 'G' ? 'selected' : ''}"/> >게시판이름</option>
			<option value="T" <c:out value="${page.srh.type == 'T' ? 'selected' : ''}"/>>제목</option>
			<option value="C" <c:out value="${page.srh.type == 'C' ? 'selected' : ''}"/>>내용</option>
			<option value="W" <c:out value="${page.srh.type == 'W' ? 'selected' : ''}"/>>작성자의 아이디</option>
			<option value="TC" <c:out value="${page.srh.type == 'TC' ? 'selected' : ''}"/>>제목+내용</option>
			<option value="GW" <c:out value="${page.srh.type == 'GW' ? 'selected' : ''}"/>>게사판이름+작성자의 아이디</option>
		</select>
    	<input type="text" name="keyword" value='<c:out value="${page.srh.keyword}"/>' placeholder="검색어 입력"/>
    	<input type="hidden" name="pageNum" value='<c:out value="${page.srh.pageNum}"/>'>
    	<input type="hidden" name="amount" value='<c:out value="${page.srh.amount}"/>'>
        <button type="submit" class="boardsearch search">검색</button>
    </form>
    
    <div>
	<select name="brdlisttype">
			<option>존재하는 게시판 목록</option>
		<c:forEach var="brdname" items="${brdlistname}">
			<option>${brdname.boardname}</option>
		</c:forEach>
	</select>
	</div>
	</div>
	
	<div class="right_div">
	<div class="boardmake_insert">
		<button onclick="location.href='createBoard'">게시판에 글쓰기</button>
		<button class="usernamebtn">사용자 이름보기</button>
	</div><br>
			
	<div class="friend_add">
		<input id="frdtext" type="text" name="friendid" placeholder="친구 아이디 입력">
		<button id="frdaddbtn">친구 추가하기</button>
	</div>
	</div>
	
	<sec:authorize access="isAuthenticated()">
	<div class="boardmake_list">
    	<div class="boardmake_select"><button onclick="location.href='selectBoardlist'">게시판 리스트 살펴보기</button></div>
    	<div class="boardmake_create"><button onclick="location.href='createBoardlist'">게시판 만들기(가입이후 한달 이후에 가능합니다)</button></div><br>
    	<div class="boardmake_delete"><button onclick="location.href='removeBoardlist'">게시판 삭제하기(게시판에 대한 권한이 있는 사람만 가능)</button></div><br>
		<sec:authorize access="hasAuthority('master')">
			<div class="member_setting" onclick="location.href='/authmanage'"><button>관리자 페이지 들어가기</button></div>
		</sec:authorize>
	</div>
	</sec:authorize>	
<form id='boardmove' method='get'>
<!-- 타입하고 키워드 추가 -->
	<input type='hidden' name='type' value='${page.srh.type}'>
	<input type='hidden' name='keyword' value='${page.srh.keyword}'>
    <input type='hidden' name='pageNum' value='${page.srh.pageNum}'>
    <input type='hidden' name='amount' value='${page.srh.amount}'>
</form>
</body>
</html>