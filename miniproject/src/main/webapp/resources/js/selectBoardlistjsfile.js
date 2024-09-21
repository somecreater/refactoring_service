/**
 * selectBoardlist.jsp 파일용 js 코드  
 */
 
$(document).ready(function(){
	var updatebtn=$('.brdlistupdatebtn');
	var brdlist=$('.brdlisttable');
	var brdnamelist=brdlist.find('.boardlistname');
	var principal="${userinfo.username}";
	var brdeditmodal=$('.brdlisteditmodal');
	var brdeditclbtn=$('.brdlieditclose');
	var brdeditbtn=$('.brdlsiteditbtn');
	updatebtn.on("click",function(e){
		//ajax 통신을 통해서 게시판 관련정보를 db에서 수정하고 페이지 재로딩해서 반영
		var brdlistnum=parseInt($(this).closest('tr').find('td:first').text());
		var brdlistname=$(this).closest('tr').find('.boardlistname').text();
		var brdlistsub=$(this).closest('tr').find('.boardlistsubject').text();
		var csrfToken = $("#_csrf").val();
		
		brdeditmodal.find('.brdlistname').html(brdlistname);
		brdeditmodal.find('#brdlistsub').val(brdlistsub);
		
		brdeditmodal.css("display", "block");
		console.log(brdlistsub);
		
		
	});
	brdeditbtn.on("click",function(e){
		var csrfToken = $("#_csrf").val();
		var brdname=$('.brdlistname').html();
		var brdsub=$('#brdlistsub').val();
		console.log(brdsub);
		$.ajax({

			type:'post',
			url:'/board/updatebrdlistsubac',
			data:{brdname: brdname, brdsub: brdsub},
			dataType:'json',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			},
			success: function(response){
				console.log(response['result']);
				if(response['result']==="success"){
					alert(brdname+" 게시판이 업데이트 되었습니다");
					location.reload(true);
				}
				else{
					alert("게시판 업데이트에 실패하였습니다.");
					location.reload(true);
				}
			},
			error: function(error){
				console.error("게시판 삭제에 실패하였습니다.");
				location.reload(true);
			}
		})
	});
	
	brdeditclbtn.on("click",function(e){
		brdeditmodal.css("display", "none");
	});
	
	$('.boardlistname').each(function(){
		var brdname=$(this).text();	
		var authvalue;
        var currentelement = $(this);
        if(principal!==null){
		$.ajax({
			type:'get',
			url:'getauthlist',
			data:{boardname: brdname},
			dataType:'json',
			success:function(response){
				authvalue=response['authlist'];
				currentelement.closest('tr').find('.brdlistauth').text(authvalue);
			},
			error:function(){
				authvalue=null
			}
		});
        }
	});
	

});

 