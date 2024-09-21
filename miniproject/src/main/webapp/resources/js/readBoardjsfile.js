/**
 * readBoard.jsp 파일용 js 코드 
 */		
		function loadattachfileview(filelist){
			console.log('첨부파일이 총 '+filelist.length+'개 있습니다');
			var imgcountform=$('.imgfilenumber');
			var comcountform=$('.comfilenumber');
			var imgmodalview=$('.imgfile_view');
			var cmnmodalview=$('.cmnfile_view');
			
			var str="";
			var cstr=""
			var imgcount=0;
			var cmcount=0;
			
			//각각의 파일의 이미지 또는 
			for(var i=0;i<filelist.length;i++){
        		var imgmodalbtn=$('.imgfile_modal_btn');
        		var commonmodalbtn=$('.comfile_modal_btn');
				var achfile=filelist[i];
				if(achfile.image===true){
					imgcount=imgcount+1;
					var imgfile=achfile;
					var orgimglink=encodeURIComponent(imgfile.uploadPath+"/"+imgfile.uuid+"_"+imgfile.fileName);
					
					 str = str + '<div class="img_obj" data-fileuri="'+imgfile.uploadPath+'" data-filename="'+imgfile.fileName+'" data-uuid="'+imgfile.uuid+'" data-type="'+imgfile.image+'">';
					 str = str + '<p>'+imgfile.fileName+'</p>';
				   str = str + '<img class="img_class" data-uri="'+imgfile.uploadPath +'" src="/display?fileuri=' + orgimglink + '">';
				   str = str + '<button class="downloadbtn">Download</button>';
				   str = str + '</div>';
				}
				else{
					cmcount=cmcount+1;
					var cmnfile=achfile;
					 cstr = cstr + '<div class="cmn_obj" data-fileuri="'+cmnfile.uploadPath+'" data-filename="'+cmnfile.fileName+'" data-uuid="'+cmnfile.uuid+'" data-type="'+cmnfile.image+'">';
					 cstr = cstr + '<p>'+cmnfile.fileName+'</p>';
				     cstr = cstr + '<button class="downloadbtn">Download</button>';
				     cstr = cstr + '</div>';
				}	
			}

			imgcountform.text('현재 이미지가 총 '+imgcount+' 개 있습니다.');	
			comcountform.text('현재 일반 파일이 총 '+cmcount+' 개 있습니다.');
				imgmodalview.append(str);
				cmnmodalview.append(cstr);
			
		}
		
		
		
		function opendwindow(src){
			window.open(src);
		}
    	
    
    	function displaycomments(cmtpage){
      		var commentContainer=$('.comment_list');
      		commentContainer.empty();
      		var str="";
      		if(cmtpage==null||cmtpage.replyCnt==0){
      			return;
      		}
      		
      	  	var loggedInUser = '<%= request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "" %>';
      		$.each(cmtpage.list, function(index, comment) {

      			str = str + '<div class="comment_obj">';
      			str = str + '<div class="comment_info">';
      			str = str + '<h4>유저의 이름</h4>';
      			str = str + '<p class="commentusername" id="cmtnamerno_'+comment.rno+'">'+comment.writer+'</p>';
      			str = str + '<h4>유저의 아이디</h4>';
      			str = str + '<p>' + comment.writer + '</p>';
      			str = str + '<h4>댓글 작성 및 수정일</h4>';
        		str = str + '<p>' + formatDateToCustomString(comment.regdate) + '</p>';
        		str = str + '</div>';
      			str = str + '<h4>댓글의 내용</h4>';
        		str = str + '<p>' + comment.comments + '</p>';
        	if(loggedInUser !== '')
        	{
    			str = str + '<input type="button" value="댓글 삭제하기" onclick="removeComment('+ comment.rno +', \''+comment.writer+'\')">';
        		if(comment.writer === loggedInUser)
        		{      		
        			str = str + '<input type="button" value="댓글 수정하기" onclick="readCmt('+ comment.rno +', '+ comment.bno+')">';
        		}
        	}
        	str = str + '</div><br>';
      		}
      		);
      		commentContainer.append(str);
      		
      	}

    	function formatDateToCustomString(date) {
    	    const options = {
    	        year: 'numeric',
    	        month: '2-digit',
    	        day: '2-digit',
    	        hour: '2-digit',
    	        minute: '2-digit',
    	        second: '2-digit',
    	        hour12: false, // 24-hour format
    	    };

    	    const formattedDate = new Intl.DateTimeFormat('en-US', options).format(date);
    	    return formattedDate.replace(/(\d+)\/(\d+)\/(\d+), (\d+):(\d+):(\d+)/, '$3/$1/$2 $4:$5:$6');
    	}

    	function loadCommentspage(pageNumValue, cmtcnt, cmtamount){
    		var commentspageform=$('.comment_class_page');
    		console.log('댓글이 총 '+cmtcnt+'개 있습니다.');
    		
    		var endpage=Math.ceil(pageNumValue/cmtamount)*10;
    		var startpage=endpage-9;
    		var realendpage=Math.ceil(cmtcnt/cmtamount);
    		if(realendpage <= endpage){
    			endpage=realendpage
    		}

    		var prev=startpage>1;
    		var next=endpage<realendpage;
    		
    		//여기에서는 페이지 번호들을 생성을 한다 페이지 번호를 클릭시 게시물 번호와 해당 댓글 페이지 번호 정보를 이용해서 댓글 창을 출력한다	
    		var str = "";
    		commentspageform.empty();
    		if (prev) {
    		    str += '<li class="commentpage prev"><a href="#" onclick="loadComments(' + (startpage - 1) + ');"> prev </a></li>';
    		}
    		for (var num = startpage; num <= endpage; num++) {
    		    str += '<li class="commentpage btn"><a href="#" onclick="loadComments(' + num + ');">' + num + '</a></li>';
    		}
    		if (next) {
    		    str += '<li class="commentpage next"><a href="#" onclick="loadComments(' + (startpage + 1) + ');"> next </a></li>';
    		}
    		commentspageform.append(str);
    		
    	}
    	function loadbrdusername(){
        	var csrfToken = $("#_csrf").val();
        	var ruserid='${board.writer}';
        	var brdrealnameobj=$('.board_username');
    		$.ajax({
    			type:'get',
    			url:'/getuserinfoname',
    			data:{userid: ruserid},
    			dataType: 'json',
    	         beforeSend: function(xhr) {
      	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
      	         },
        		 success:function(result){
        			 var realusername=result['userrealname'];
        			 brdrealnameobj.find('p').text(realusername);
         		 },
         		 error: function(error){
         			console.error("유저정보 가져오기 실패"); 
       			 	brdrealnameobj.find('p').text("nothing");
         		 }
         			 
    			
    		});
    	}
    	function loadcmtusername(rno,userid){
        	var csrfToken = $("#_csrf").val();
    		$.ajax({
    			type:'get',
    			url:'/getuserinfoname',
    			data:{userid: userid},
    			dataType: 'json',
    	         beforeSend: function(xhr) {
      	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
      	         },
        		 success:function(result){
        			 var realusername=result['userrealname'];
        			 var usernameobj=$('#cmtnamerno_'+rno);
        			 usernameobj.text(realusername);
         		 },
         		 error: function(error){
         			console.error("유저정보 가져오기 실패"); 
       			 	var usernameobj=$('#cmtnamerno_'+rno);
       			 	usernameobj.text("nothing");
         		 }
         			 
    			
    		});
    		
    	}
    	
    	function removeComment(rno, writer){
    		var csrfToken = $("#_csrf").val();
    		var isuser=writer;
    		$.ajax({
    			type: 'post',
    			url: '/comment/deletecomment',
    			data: {rno: rno, userid: isuser},
    			dataType:'JSON',
    	        beforeSend: function(xhr) {
    	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
    	        },
    			success: function(response){
    				
    				console.log('댓글 삭제 결과: '+response['result']);
    				loadComments(1);
    			},
    			error: function(error){
    				console.error('댓글 삭제 실패');
    			}
    		});
    		
    	}
    	
    	//댓글 등록과 업데이트 코드
        function readCmt(rno, bno){
        		$.ajax({
        			type: 'get',
        			url: '/comment/readComment',
        			data: {rno: rno},
        			dataType:'JSON',
        			success: function(resultMap) {
        				//댓글을 rno를 통해서 읽어오는 것을 성공할 시 해당 결과값을 이용해서 modal 창을 출력
    					showupdatemodal(resultMap['comment'], bno);
    				},
        			error: function(){
        				console.error('댓글 항목 불러오기 실패');
        			}
        		});
        		
        	}
        function showupdatemodal(comment, bno){
        		//댓글 수정을 위한 모달창을 띄우고 모달창에서 저장버튼 누를시 바로 수정적용
        		const cmtmodal=document.getElementById("comment_edit");

        	    cmtmodal.querySelector('#writer_obj').value=comment.writer;
        	    cmtmodal.querySelector('#comments_obj').value=comment.comments;
        	    cmtmodal.querySelector('#rno').value=comment.rno;
        	    cmtmodal.querySelector('#bno').value=bno;
        		
        		cmtmodal.style.display="block";
        		
        	}
    	
        function goBack(){
        	window.history.back();
        }