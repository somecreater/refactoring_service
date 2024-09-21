/**
 * idsearch.jsp 파일용 js 코드     
 */

$(document).ready(function(){
	var loginbtn=$('#backlogin');
	var authidmodal=$('#authid');
	var authpassmodal=$('#authpass');
	var passreset=$('#psac');
	loginbtn.on("click",function(e){
		var login=loginbtn.data("href");
		window.location.href=login;
	});
	
	var idsearchbtn=$('.idsearchbtn');
	var passsearchbtn=$('.passsearchbtn');
	idsearchbtn.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var inemail=$(".idemail").val();
		var inphone=$(".idphone").val();
		console.log(inemail);
		$.ajax({
			type:'get',
			url:'/searchauth',
			data:{email: inemail,phone:inphone},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				if(response['result']==='success'){
					alert("메일을 전송하였습니다.");
					authidmodal.css("display","block");
				}
				else if(response['result']==='subsuccess'){
					alert("메일 전송이 정상적으로 되지 않았지만, 입력된 데이터 토대로 확인한 결과, 현재 존재하는 아이디는 "+response['userid']+" 입니다.");
				}
				else{
					alert("메일의 주소나 휴대폰 번호가 잘못되었거나, 회원이 아닙니다.");
				}
			},
			error: function(error){	
				console.error("메일 전송을 실패했습니다.");
			}
		});
	});
	passsearchbtn.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var paemail=$(".passemail").val();
		var paid=$(".passid").val();
		var paphone=$(".passphone").val();
		$.ajax({
			type:'get',
			url:'/searchpass',
			data:{userid:paid, email:paemail, phone:paphone},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				if(response['result']==='success'){
					authpassmodal.css("display","block");
				}
				else if(response['result']==='failure'){
					alert('입력된 값이 부적절 합니다.')
				}
			},
			error: function(error){				
				console.error("입력된 정보가 맞지 않습니다.");
			}
		});
		
	});
	//비밀번호 변경 기능은 완료 되었으나 별도의 인증이 필요 할수도
	passreset.on("click",function(e){
		var csrfToken = $("#_csrf").val();	
		var pass=$(".passinput").val();
		var repass=$(".passreinput").val();
		var reid=$(".idreinput").val();
		$.ajax({
			type:'post',
			url:'/resetpassword',
			data:{userid:reid, newpass:pass, renewpass:repass},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				if(response['result']==='notpass'){
					alert('패스워드가 부적절한 형식입니다.');
				}
				else if(response['result']==='notmatch'){
					alert('패스워드가 서로 일치하지 않습니다.');
				}
				else if(response['result']==='failure'){
					alert('서버 오류로 비밀번호 변경에 실패하였습니다.')
				}else if(response['result']==='success'){
					alert('비밀번호가 정상적으로 변경되었습니다.');
					window.location.href="/loginboard";
				}
			},
			error: function(error){				
				console.error("비밀번호 변경에 실패하였습니다.");
			}
		});
		
	});
	
});