/**
 * listboard.jsp 파일용 js 코드
 */
 
   $(document).ready(function(){
        let boardmove = $("#boardmove");
        var brdusrnamebtn=$('.usernamebtn');
        $(".page-item a").on("click", function(e){
        	e.preventDefault();
        	console.log("page click");
        	boardmove.find("input[name='pageNum']").val($(this).attr("href"));
        	boardmove.attr("action","listboard");
        	boardmove.submit();
        });
        
        loaduserinfo();	
        var topboardbtn=$('#reloadlistboard');
        var toploginbtn=$('#boardlogin');
        var logoutbtn=$('.boardlogout');
		var mypagebtn=$('.boardmypage');
        
		topboardbtn.on("click",function(e){
        	window.location.href=topboardbtn.data("href");	
        });
        toploginbtn.on("click",function(e){
        	window.location.href=toploginbtn.data("href");	
        });
        mypagebtn.on("click",function(e){
        	window.location.href=mypagebtn.data("href");	
        });
        
        logoutbtn.on("click",function(e){
        	var href=logoutbtn.data("href");
    		var csrfToken = $("#_csrf").val();
        	 $.ajax({
        		 type:'post',
        		 url:'/logoutaction',
     	         beforeSend: function(xhr) {
     	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
     	         },
        		 success:function(){
        			location.reload();
        			alert("로그아웃");
        		 },
        		 error: function(){
        			console.error("로그아웃 실패"); 
        		 }
        			 
        	 });
        });
        
        function loaduserinfo(){
        	var useri= document.querySelector('h4.userId');
        	var username=document.querySelector('h4.username');
    		var csrfToken = $("#_csrf").val();
    		
        	if(useri === null){
        	}else{
        		var useristr=useri.textContent;
        		console.log(useristr);
        		$.ajax({
        			type:'get',
        			url:'/getuserinfoname',
        			data:{userid: useristr},
        			dataType: 'json',
        	         beforeSend: function(xhr) {
          	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
          	         },
            		 success:function(result){
            			 var realusername=result['userrealname'];
            			 username.textContent=realusername+' 님 안녕하세요.';
             		 },
             		 error: function(error){
             			console.error("유저정보 가져오기 실패"); 
             		 }
             			 
        			
        		});
        	}
        }

        brdusrnamebtn.on("click",function(e){
            $(".boardusername").each(function() {
                var bno = $(this).closest("tr").find("td:first").text();
                var writerid = $(this).text();
                loadwriternamelist(bno, writerid);
            }); 
        });
        
        var frdadd=$("#frdaddbtn");
        frdadd.on("click",function(e){
        	var fid=$("#frdtext").val();
    		var csrfToken = $("#_csrf").val();
    		var uid=document.querySelector('h4.userId').textContent;
    		$.ajax({
    			type:'post',
        		url:'/insertfriend',
        		data:{userid: uid, fuserid: fid},
        		dataType: 'json',
        	    beforeSend: function(xhr) {
          	    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
          	    },
            	success:function(result){
            		alert("친구 추가 성공!!!");	
             	},
             	error: function(error){
             		console.error("친구 추가 실패"); 
             	}
    		});
        });
    });
    

    function loadwriternamelist(bno, writerid){
    	var csrfToken = $("#_csrf").val();
    	console.log(writerid);
		$.ajax({
			type:'get',
			url:'/getuserinfoname',
			data:{userid: writerid},
			dataType: 'json',
	         beforeSend: function(xhr) {
  	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
  	         },
    		 success:function(result){
    			 var realusername=result['userrealname'];
    	         $("#writer_" + bno).text(realusername);
     		 },
     		 error: function(error){
     			console.error("유저정보 가져오기 실패"); 
     			if(writerid!==null){
                	$("#writer_" + bno).text(writerid);
     			}
     			else if(writerid === null){
                	$("#writer_" + bno).text('존재하지 않는 아이디');
     			}
     		 }
		});
    }
    