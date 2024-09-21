/**
 * boardjoin.jsp 파일용 js 코드   
 */
$(document).ready(function(){
	var idset=$('#joinidset');
	var submitbtn=$('#joinbtn');
	var idckbtn=$('#idcheck');
	var idckbox=$('#isidchk');
	var submitform=$("form");
	
	idckbtn.on("click",function(e){
		var inputid=$('#id').val();
		var csrfToken = $("#_csrf").val();
		
		if(inputid === ''||inputid.length === 0){
			alert('아이디를 입력해주세요')
		}
		else if(inputid.length<4){
			alert('충분한 길이의 아이디를 입력해주세요');
		}
		else{
			//ajax를 이용해서 아이디 중복여부 확인
			$.ajax({
				type:'get',
				url:'/idcheckaction',
				data:{ id:inputid},
				dataType:'json',
			    beforeSend: function(xhr) {
			         xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			    },
				success: function(response){
					if(response['result']==="success"){
						idckbox.prop('checked', true);
						idckbtn.prop('disabled', true);
					}else{
						alert("이미 아이디가 존재합니다.");
					}
				},
				error: function(error){
					console.error("아이디 확인 실패");
				}
			});
		}
	});
	
	submitbtn.on("click",function(e){
		var inputpw=$('#passwd').val();
		var inputname=$('#username').val();
		var inputphone=$('#phone').val();
		var csrfToken = $("#_csrf").val();
		if(!idckbox.prop('checked')){
			alert("아이디를 다시 확인해주세요");
			e.preventDefault();
			
		}
		else if(inputpw.length===0||inputname.length===0||inputphone.length===0){

			alert("작성 정보들을 다시 확인해주세요");
			
		}
		
			//console.log(inputpw);
			//console.log(inputname);
			//console.log(inputphone);
			$.ajax({
				type:'get',
				url:'/etcdatacheckaction',
				data:{passwd:inputpw, username:inputname, phonenumber:inputphone},
				dataType:'json',
			    beforeSend: function(xhr) {
			         xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			    },
				success: function(response){

					console.log(inputpw);
					console.log(inputname);
					console.log(inputphone);
					console.log(response['result']);
					if(response['result']==='failure'){
						alert("작성 정보들을 다시 확인해주세요");
						e.preventDefault();	
					}
					if(response['result']==='success')
					{
						alert("회원가입이 완료되었습니다");
						submitform.submit();
					}
				},
				error: function(error){
					console.error("기타 데이터 확인 실패");
					alert("작성 정보들을 다시 확인해주세요");
				}
			});

		
	});

	
	var bkloginbtn=$('#backloginbtn');
	bkloginbtn.on("click",function(e){
		var loginhref=bkloginbtn.data("href");
		window.location.href=loginhref;
	});
	
});
