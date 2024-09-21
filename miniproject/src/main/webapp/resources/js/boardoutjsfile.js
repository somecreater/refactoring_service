/**
 * boardout.jsp 파일용 js 코드  
 */

$(document).ready(function(){
	var backbtn=$('#backbtn');
	var brdout=$('.boardoutbtn');
	var chckbox=$('#chckdelete');
	backbtn.on("click",function(e){
		window.location.href=backbtn.data("href");
	});
	brdout.on("click",function(e){
		var inchck=chckbox.prop('checked');
		var inid=$('.deleteuserid').val();
		var inpass=$('.deleteuserpass').val();
		console.log(inchck);
		console.log(inid);
		console.log(inpass);

		//ajax를 이용해서 회원 탈퇴를 수행
		$.ajax({
			type:'post',
			url:'/boardoutaction',
			data:{id:inid,pass:inpass,datareset:inchck},
			dataType:'json',
			success:function(response){
				alert('아이디 탈퇴 처리가 되었습니다.');
				console.log(response['result']);
				
				window.location.href="/logoutaction";
			},
			error:function(){
				alert('아이디 탈퇴에 실패했습니다.')
			}	
		});
	});
});