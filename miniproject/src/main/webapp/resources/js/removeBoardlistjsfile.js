/**
 * removeBoardlist.jsp 파일용 js 코드 
 */
 
$(document).ready(function(){
	var boardlistbtn=$('.removebrdlist');
	var brdrmmodal=$('.boardlistremovedmodal');
	var brdrmbtn=$('.removebrdlist');
	var brdrmacbtn=$('.removebrdlistbtn');
	var brdrmclosebtn=$('.brdrmclose');
	
	boardlistbtn.on("click",function(e){
		var brdnameObj=$(this);
		var brdname=brdnameObj.data("boardname");
		console.log(brdname);
		brdrmmodal.find(".removebrdlistbtn").data("boardname",brdname);
		brdrmmodal.find(".removebrdh").text(brdname);
		brdrmmodal.css("display", "block");

	});
	brdrmclosebtn.on("click",function(e){
		brdrmmodal.css("display", "none");
	});
	
	brdrmacbtn.on("click",function(e){
		var brdname=$(this).data("boardname");
		var csrfToken = $("#_csrf").val();

		$.ajax({
			type:'post',
			url:'/board/removeBoardlistaction',
			data:{brdname: brdname},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				console.log(response['result']);
				if(response['result']==="success"){
					alert(brdname+" 게시판이 삭제되었습니다");
					location.reload(true);
				}
				else{
					alert("게시판 삭제에 실패하였습니다.");
					location.reload(true);
				}
			},
			error: function(error){
				console.error("게시판 삭제에 실패하였습니다.");
				location.reload(true);
			}
		});
		
	});
});
 