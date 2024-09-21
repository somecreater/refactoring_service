/**
 * authmanage.jsp 파일용 js 코드    
 */
$(document).ready(function(){
	var aubtn=$('.authbtn');
	var banbtn=$('.banbtn');
	var authclose=$('#authmodalclose');
	var banclose=$('#banmodalclose');
	var authui=$('.authlistview');
	var authmodal=$('#authmodal');
	var authmodaluserid=$('.authmodaluserid');
	var banmodal=$('#banmodal');
	var banmodaluserid=$('.banmodaluserid');
	var authsubmitbtn=$('.authsubmit');
	
	aubtn.on("click",function(e){
		var userinfo=$(this).closest('tr');
		var userid=userinfo.find('td:first').text();
	    var authlist=userinfo.find('.authlist p').map(function() {
            return $(this).text().trim();
        }).get();
		
		var str="";
		var idstr=""
		authui.empty();
		authmodaluserid.empty();
		
		idstr=idstr+"<h3>"+userid+"</h3>"
        $.each(authlist, function(index, value) {
            str += "<div class='authelement'>";
            str += "<p>" + value + "</p>";
            str += "<button class='authelementdelete' data-userid='"+userid+"'>권한 삭제</button>";
            str += "</div>";
        });
		authmodaluserid.append(idstr);
		authui.append(str);
		authmodal.css("display","block");
	});
	banbtn.on("click",function(e){
		var userinfo=$(this).closest('tr');
		var banuserid=userinfo.find('td:first').text();
		var idstr="";
		var bancontent;
		banmodaluserid.empty();
		idstr=idstr+"<h3>"+banuserid+"</h3>"
		banmodaluserid.append(idstr);
		
		//ajax 실행으로 차단 현황을 가져온다.
		$.ajax({
			type:'get',
			url:'/getban',
			data:{userid: banuserid},
			dataType:'json',
			success:function(response){
				if(response['banuserobj']!==null){
					bancontent=response['banuserobj'];
					displayban(bancontent);
				}else{
					alert('차단 목록에 없습니다.');
				}
			},
			error:function(){
				console.error('차단 현황관련 오류');
			}
		});
		
		banmodal.css("display","block");
	});
	function displayban(bancontent){
		var banui=$('.bansituation');
		var startdate=displaytime(bancontent.startdate);
		var enddate=displaytime(bancontent.enddate);
		var str="";
		banui.empty();
		str=str+"<h4>차단 아이디</h4>";
		str=str+"<p>"+bancontent.userid+"</p>";
		str=str+"<h4>차단 사유</h4>";
		str=str+"<p>"+bancontent.banreason+"</p>";
		str=str+"<h4>차단 시작 일자</h4>";
		str=str+"<p>"+startdate+"</p>";
		str=str+"<h4>차단 종료 일자</h4>";
		str=str+"<p>"+enddate+"</p>";
		banui.append(str);
	}
	function displaytime(time){
		var date=new Date(time);
		var year=date.getFullYear();
		var month=('0'+(date.getMonth() + 1)).slice(-2); 
		var day=('0'+date.getDate()).slice(-2); 
		var formattedDate=year+'-'+month+'-'+day;
		return formattedDate;

	}
	authclose.on("click",function(e){
		authmodal.css("display","none");
	});
	banclose.on("click",function(e){
		banmodal.css("display","none");
	});
	
	var bansubtn=$('.bansubmitdiv');
	var bandebtn=$('.bancanclediv');
	authui.on("click","button",function(e){
		var csrfToken = $("#_csrf").val();
		var authvalue=$(this).closest('.authelement').find('p').text();
		var authid=$(this).data('userid');
		console.log(authid);
		console.log(authvalue);
		//ajax로 권한 삭제 메소드 수행
		$.ajax({
			type:'post',
			url:'/authdeaction',
			data:{userid:authid,auth:authvalue},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				if(response['result']==='success'){
					alert("권한 삭제 완료, 작업 완료시 페이지 새로고침");
				}else{
					alert("권한 삭제 실패")
				}
			},
			error: function(error){
				console.error('권한 삭제 오류');
			}
				
		});
	});
	var authbtndiv=$('.authbtnset');
	authbtndiv.on("click","button",function(e){
		var csrfToken = $("#_csrf").val();
		var authValue=$('.inputauth').val();
		var authuserid=$('.authmodaluserid').find('h3').text();
		console.log(authValue);
		console.log(authuserid);
		
		//ajax로 권한 추가 메소드 수행
		$.ajax({
			type:'post',
			url:'/authaction',
			data:{userid:authuserid,auth:authValue},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				if(response['result']==='success'){
				alert("권한 추가 완료, 작업 완료시 페이지 새로고침")
				}else{
					alert("권한 추가 실패");
				}
			},
			error: function(error){
				console.error('권한 추가 오류');
			}
				
		});
	});
	bansubtn.on("click","button",function(e){
		var csrfToken = $("#_csrf").val();
		var banTime=parseInt($('.inputbantime').val());
		var banReason=$('.inputbanreason').val();
		var banuserid=$('.banmodaluserid').find('h3').text();
		console.log(banTime);
		console.log(banReason);
		console.log(banuserid);
		
		//ajax로 차단 메소드 수행
		$.ajax({
			type:'post',
			url:'/banaction',
			data:{userid:banuserid,banreason:banReason,bantime:banTime},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				if(response['result']==='success'){
					alert("차단 성공, 작업 완료시 페이지 재로딩");
				}else{
					alert("차단 실패");
				}
			},
			error: function(error){
				console.error('차단 오류');
			}
				
		});
	});
	bandebtn.on("click","button",function(e){
		var csrfToken = $("#_csrf").val();
		var banuserid=$('.banmodaluserid').find('h3').text();
		console.log(banuserid);
		
		//ajax로 차단 해제 메소드 수행
		$.ajax({
			type:'post',
			url:'/bandeaction',
			data:{userid:banuserid},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				if(response['result']==='success'){
					alert("차단 해체 성공, 작업 완료시 페이지 재로딩");
				}else{
					alert("차단 해제 실패");
				}
			},
			error: function(error){
				console.error('차단 해제 오류');
			}
				
		});
	});
});