/**
 * createBoard.jsp 파일용 js 코드
 */
 
$(document).ready(function(){
	var inputFile=$('#inputfile')
	var filemodalbtn=$('#fileupload_btn');
	var filemodal=$('#fileUploadModal');
	var filemodalclose=$('#fileupload_close');
	var filemodalreset=$('#fileupload_reset');
	var filemodalregister=$('#fileupload_tupload');
	var filemodaltopresult=$('#filemodalresult');
	var filemodalresult=$('#filemodalresult_common');
	var imgfilemodalresult=$('#filemodalresult_thumb');
	
	var filetopresult=$('.file_upload_result')
	var fileresult=$('#fileresult_common');
	var imgfileresult=$('#fileresult_thumb');
	
	var submitform=$("form[role='form']");
	var backlist=$('#backlistboard');
	
	backlist.on("click",function(e){
		window.location.href="/board/listboard";
	});
	
	//게시물 작성완료 버튼 누를시 파일 요소 추가해서 저장
	$("button[type='submit']").on("click", function(e){

	    e.preventDefault();
		const dataPath=[];
		const dataUuid=[];
		const dataName=[];
		const dataType=[];
		const commondataPath=[];
		const commondataUuid=[];
		const commondataName=[];
		const commondataType=[];
		const showfilelist=document.querySelectorAll(".result_img_file");
		const showcommonfilelist=document.querySelectorAll(".result_file");

	    var str = "";
		var i=0;
		if(showfilelist.length>0){
			for(i=0;i<showfilelist.length;i++){
				const button=showfilelist[i].querySelector(".file_btn");
				const name=showfilelist[i].querySelector("span").textContent;
				dataPath.push(button.dataset.path);
				dataUuid.push(button.dataset.uuid);
				dataName.push(name);
				dataType.push(button.dataset.image);
			}
			for(i=0;i<showfilelist.length;i++){
				var dataname=dataName[i];
				var datapath=dataPath[i];
				var datauuid=dataUuid[i];
				var datatype=dataType[i];
				
				str = str + "<input type='hidden' name='attachlist["+i+"].fileName' value='"+dataname+"'>";
			    str = str + "<input type='hidden' name='attachlist["+i+"].uuid' value='"+datauuid+"'>";
			    str = str + "<input type='hidden' name='attachlist["+i+"].uploadPath' value='"+datapath+"'>";
			    str = str + "<input type='hidden' name='attachlist["+i+"].image' value='"+datatype+"'>";
			}
		}
		var j=0;
		if(showcommonfilelist.length>0){
			for(j=i;j<showcommonfilelist.length+i;j++){
				const button=showcommonfilelist[j-i].querySelector(".file_btn");
				const name=showcommonfilelist[j-i].querySelector("span").textContent;
				commondataPath.push(button.dataset.path);
				commondataUuid.push(button.dataset.uuid);
				commondataName.push(name);
				commondataType.push(button.dataset.image);
			}
			
			for(j=i;j<showcommonfilelist.length+i;j++){
				var commondataname=commondataName[j-i];
				var commondatapath=commondataPath[j-i];
				var commondatauuid=commondataUuid[j-i];
				var commondatatype=commondataType[j-i];
				
				str = str + "<input type='hidden' name='attachlist["+j+"].fileName' value='"+commondataname+"'>";
			    str = str + "<input type='hidden' name='attachlist["+j+"].uuid' value='"+commondatauuid+"'>";
			    str = str + "<input type='hidden' name='attachlist["+j+"].uploadPath' value='"+commondatapath+"'>";
			    str = str + "<input type='hidden' name='attachlist["+j+"].image' value='"+commondatatype+"'>";
			}
		}
		
		if(showfilelist.length===0 && showcommonfilelist.length===0){
			
		}
		submitform.append(str);
		submitform.submit();		
	});
	
	
	filemodaltopresult.on("click", "button",function(e){
		var orgobjfile= $(this).data("path");
		var objtype= $(this).data("image");
		var objuuid= $(this).data("uuid");
		var objui=$(this).closest("li");
		var objname = objui.find("span").text();
		
		var objfile=encodeURIComponent(orgobjfile+"/"+objuuid+"_"+objname);
		var csrfToken = $("#_csrf").val();
		
		$.ajax({
			type:'post',
			url:'/deletefile',
			data:{fileuri: objfile, filetype: objtype},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				alert(response['result']);
				objui.remove();

				const filelist=document.querySelectorAll(".modal_img_file");
				const commonfilelist=document.querySelectorAll(".modal_file");
				if(filelist.length===0){
					imgfilemodalresult.css("height","0px");
					imgfilemodalresult.empty();
				}
				if(commonfilelist.length===0){
					filemodalresult.css("height","0px");
					filemodalresult.empty();
				}
			},
			error: function(error){
				console.error('파일삭제 오류');
			}
		});	
	});
	filetopresult.on("click", "button",function(e){
		var orgobjfile= $(this).data("path");
		var objtype= $(this).data("image");
		var objuuid= $(this).data("uuid");
		var objui=$(this).closest("li");
		var objname = objui.find("span").text();
		var objfile=encodeURIComponent(orgobjfile+"/"+objuuid+"_"+objname);
		
		var csrfToken = $("#_csrf").val();

		$.ajax({
			type:'post',
			url:'/deletefile',
			data:{fileuri: objfile, filetype: objtype},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				alert(response['result']);
				objui.remove();
				const showfilelist=document.querySelectorAll(".result_img_file");
				const showcommonfilelist=document.querySelectorAll(".result_file");
				if(showfilelist.length===0){
					imgfileresult.css("height","0px");
					imgfileresult.empty();
				}
				if(showcommonfilelist.length===0){
					fileresult.css("height","0px");
					fileresult.empty();
				}
			},
			error: function(error){
				console.error('파일삭제 오류');
			}
		});	
		
	});
	
	filemodalbtn.click(function(){
		filemodalresult.css("height","0px");
		imgfilemodalresult.css("height","0px");
		filemodalresult.empty();
		imgfilemodalresult.empty();
		filemodal.modal('show');
	});
	

	function fileclear(){
		//모달창이 열린상태이면
		var filemodalobj=$('#fileUploadModal');
		var filemodalopen=filemodalobj.css('display') === 'block';
		if(filemodalopen){
			modalfiledelete();
			resultfiledelete();
		}
		else{
			//모달창이 숨겨진 상태이면
			resultfiledelete();	
		}
	}
	filemodalreset.click(function(){
		modalfiledelete();
	});
	filemodalclose.click(function(){
		modalfiledelete();
		filemodal.modal('hide');
	});
	function modalfiledelete(){
		const dataPath=[];
		const dataName=[];
		const dataUuid=[];
		const dataType=[];
		const commondataPath=[];
		const commondataType=[];
		const commondataName=[];
		const commondataUuid=[];
		
		const filelist=document.querySelectorAll(".modal_img_file");
		const commonfilelist=document.querySelectorAll(".modal_file");
		
		if(filelist.length>0){
		for (let i = 0; i < filelist.length; i++) {
			const button=filelist[i].querySelector(".modal_file_btn");
			const name=filelist[i].querySelector("span").textContent;
			dataPath.push(button.dataset.path);
		    dataType.push(button.dataset.image);
		    dataUuid.push(button.dataset.uuid);
		    dataName.push(name);
		}
		deletefilelist(dataPath,dataType,dataUuid,dataName);
		for(let i=0;i<filelist.length;i++){
			filelist[i].remove();
		}

		imgfilemodalresult.css("height","0px");
		imgfilemodalresult.empty();
		}
		if(commonfilelist.length>0){
			for (let i = 0; i < commonfilelist.length; i++) {
			const button=commonfilelist[i].querySelector(".modal_file_btn");
			const name=commonfilelist[i].querySelector("span").textContent;
			commondataPath.push(button.dataset.path);
		    commondataType.push(button.dataset.image);
	    	commondataUuid.push(button.dataset.uuid)
	    	commondataName.push(name);
		}
		deletefilelist(commondataPath,commondataType,commondataUuid,commondataName);
		for(let i=0;i<commonfilelist.length;i++){
			commonfilelist[i].remove();
		}

		filemodalresult.css("height","0px");
		filemodalresult.empty();
				
		}
		if(commonfilelist.length === 0 && filelist.length === 0){			
			alert('파일이 없습니다');
		}
	}
	function resultfiledelete(){
		const dataPath=[];
		const dataName=[];
		const dataUuid=[];
		const dataType=[];
		const commondataPath=[];
		const commondataType=[];
		const commondataName=[];
		const commondataUuid=[];
		
		const showfilelist=document.querySelectorAll(".result_img_file");
		const showcommonfilelist=document.querySelectorAll(".result_file");
		
		if(showfilelist.length>0){
			for (let i = 0; i < showfilelist.length; i++) {
				const button=showfilelist[i].querySelector(".file_btn");
				const name=showfilelist[i].querySelector("span").textContent;
				dataPath.push(button.dataset.path);
			    dataType.push(button.dataset.image);
			    dataUuid.push(button.dataset.uuid);
			    dataName.push(name);
			}
			deletefilelist(dataPath,dataType,dataUuid,dataName);
			for(let i=0;i<showfilelist.length;i++){
				showfilelist[i].remove();
			}

			imgfileresult.css("height","0px");
			imgfileresult.empty();
			}
			if(showcommonfilelist.length>0){
				for (let i = 0; i < showcommonfilelist.length; i++) {
				const button=showcommonfilelist[i].querySelector(".file_btn");
				const name=showcommonfilelist[i].querySelector("span").textContent;
				commondataPath.push(button.dataset.path);
			    commondataType.push(button.dataset.image);
		    	commondataUuid.push(button.dataset.uuid)
		    	commondataName.push(name);
			}
			deletefilelist(commondataPath,commondataType,commondataUuid,commondataName);
			for(let i=0;i<showcommonfilelist.length;i++){
				showcommonfilelist[i].remove();
			}

			fileresult.css("height","0px");
			fileresult.empty();
					
			}
			if(showcommonfilelist.length === 0 && showfilelist.length === 0){			
				alert('파일이 없습니다');
			}
			
	}
	
	function deletefilelist(dataPath,dataType,dataUuid,dataName){
		 for (let i = 0; i < dataPath.length; i++){
			 const orgfileuri=dataPath[i];
			 const img=dataType[i];
			 const fileuuid=dataUuid[i];
			 const filename=dataName[i]
			 
			 const fileuri=encodeURIComponent(orgfileuri+"/"+fileuuid+"_"+filename);
			 var csrfToken = $("#_csrf").val();
			 
			 $.ajax({
					type:'post',
					url:'/deletefile',
					data:{fileuri: fileuri, filetype: img},
					dataType:'JSON',
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
			        },
					success: function(response){
						alert(response['result']);
					},
					error: function(error){
						console.error('파일삭제 오류');
					}
				});	
			 
		 }		
	}
	
	filemodalregister.click(function(){
		//각종 필요한 리스트 요소(4가지)를 이용해서 같은 방식으로 실제 등록화면에 반영
		const dataPath=[];
		const dataUuid=[];
		const dataName=[];
		const dataType=[];
		const commondataPath=[];
		const commondataUuid=[];
		const commondataName=[];
		const commondataType=[];
		const filelist=document.querySelectorAll(".modal_img_file");
		const commonfilelist=document.querySelectorAll(".modal_file");
		
		
		
		var str="";
		
		if(filelist.length>0){
			imgfileresult.css("height","1000px");
			for(var i=0;i<filelist.length;i++){
				const button=filelist[i].querySelector(".modal_file_btn");
				const name=filelist[i].querySelector("span").textContent;
				dataPath.push(button.dataset.path);
				dataUuid.push(button.dataset.uuid);
				dataName.push(name);
				dataType.push(button.dataset.image);
			}
			for(var i=0;i<filelist.length;i++){
			var dataname=dataName[i];
			var datapath=dataPath[i];
			var datauuid=dataUuid[i];
			var datatype=dataType[i];
			var imgfileuri=encodeURIComponent(datapath+"/"+datauuid+"_"+dataname);
			
			str=str+"<li class='result_img_file'>"+"<span>"+dataname+"</span>";
			str=str+"<img class='reuslt_img' src='/display?fileuri="+imgfileuri+"'>";
			str=str+"<button class='file_btn' type='button' data-path="+datapath+" data-name="+dataname+" data-uuid="+datauuid+" data-image="+datatype+">delete</button>";
			str=str+"</li>";
			}
			imgfileresult.append(str);
		}
		str="";
		if(commonfilelist.length>0){
			fileresult.css("height","500px");
			for(var i=0;i<commonfilelist.length;i++){
				const button=commonfilelist[i].querySelector(".modal_file_btn");
				const name=commonfilelist[i].querySelector("span").textContent;
				
				commondataPath.push(button.dataset.path);
				commondataUuid.push(button.dataset.uuid);
				commondataName.push(name);
				commondataType.push(button.dataset.image);
			}
			for(var i=0;i<commonfilelist.length;i++){
			var commondataname=commondataName[i];
			var commondatapath=commondataPath[i];
			var commondatauuid=commondataUuid[i];
			var commondatatype=commondataType[i];
				
			str=str+"<li class='result_file'>";
			str=str+"<span class='filename'>"+commondataname+"</span>";
			str=str+"<button class='file_btn' type='button' data-path=\'"+commondatapath+"\' data-name="+commondataname+" data-uuid="+commondatauuid+" data-image="+commondatatype+">delete</button>";
			str=str+"</li>";
			}
			fileresult.append(str);
		}
		
		if(filelist.length === 0 && commonfilelist.length === 0){
			alert('파일이 없습니다')
		}
		
		filemodal.modal('hide');
	})
	
	const modal_file_drag=$('#filemodalbody');
	modal_file_drag.on("dragover",function(e){
	    console.log("파일 드래그 감지");
	    e.preventDefault();
	});
	
	const modal_file_direct=$('#filemodaldirect');
	modal_file_direct.on("change",function(e){
		console.log("파일 직접 등록 완료");
	    e.preventDefault();
	    
		var csrfToken = $("#_csrf").val();
		var formData = new FormData();
	    var files =this.files;
	    for(var i=0;i<files.length;i++){
		    console.log(files[i].name);	
		    console.log(files[i].size);
		    formData.append("uploadFile",files[i]);
	    }

	    $.ajax({
	    	type: 'post',
			url:'/uploadFile',
			data: formData,
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
            contentType: false,
            processData: false,
            success: function(response){
            	var resultresponse=response['result'];
				var resultfilelist=response['attachfilelist'];
				var thumbnailefilelist=response['thumbnaillist'];
				displayfilelist(resultresponse,resultfilelist,thumbnailefilelist);
            },
            error: function(error){
            	console.error('파일 업로드 에러!!');
            }
	    });
	});
	
	modal_file_drag.on("drop",function(e){
		console.log("파일 드롭 완료");
	    e.preventDefault();
		var csrfToken = $("#_csrf").val();
		var formData = new FormData();
	    var files = e.originalEvent.dataTransfer.files;
	    for(var i=0;i<files.length;i++){
		    console.log(files[i].name);	
		    console.log(files[i].size);
		    formData.append("uploadFile",files[i]);
	    }
	    $.ajax({
	    	type: 'post',
			url:'/uploadFile',
			data: formData,
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
            contentType: false,
            processData: false,
            success: function(response){
            	var resultresponse=response['result'];
				var resultfilelist=response['attachfilelist'];
				var thumbnailefilelist=response['thumbnaillist'];
				displayfilelist(resultresponse,resultfilelist,thumbnailefilelist);
            },
            error: function(error){
            	console.error('파일 업로드 에러!!');
            }
	    });
	    //여기서 드롭을 할때, 실제로 서버에 업로드 아래 result 화면에 순서대로 나열된 리스트 형식으로 보여주면 될듯(이미지+파일이름)
	    
	});
		
	
	function displayfilelist(resultresponse,resultfilelist,thumbnailefilelist){
		//hidden input에 필요한 값들을 넣으면 될듯
		var str="";

		if(resultresponse === 'upload_fail' || resultfilelist === null)
		{
			console.log('지원하지 않는 파일');
			str=str+'<div><p>업로드에 실패하였습니다. 다시 시도해주세요</p></div>';	
			filemodalresult.append(str);
		}
		//모든 파일은 기본적으로 x버튼을 추가해서 추후에 해당 버튼을 클릭시 삭제를 수행하도록한다.
		
		for(var i=0;i<resultfilelist.length;i++){
		if(resultfilelist[i].image===true)
		{
			//이미지 파일 리스트인 경우	
			//썸네일 파일을 생성해서 모달창 아래쪽에 최대 5*4인 격자무늬로 보여줌
			//이미지 경로를 받아서 섬네일을 보여주는 컨트롤러 메소드 필요
			imgfilemodalresult.css("height","300px");
				var imgfile=resultfilelist[i];
				var thumbfileuri=encodeURIComponent(imgfile.uploadPath+"/th_"+imgfile.uuid+"_"+imgfile.fileName);
				var imgfileuri=encodeURIComponent(imgfile.uploadPath+"/"+imgfile.uuid+"_"+imgfile.fileName);
				console.log(thumbfileuri);
				
				//파일의 이미지 여부, 파일의 경로정보, 파일의 이름, 파일의 uuid 등을 따로 저장해야(나중에 파일 등록버튼 누를시 필요)
				
				str=str+"<li class='modal_img_file'>"+"<span>"+imgfile.fileName+"</span>";
				str=str+"<img class='modal_thumimg' src='/display?fileuri="+thumbfileuri+"'>";
				str=str+"<button class='modal_file_btn' type='button' data-path="+imgfile.uploadPath+" data-name="+imgfile.fileName+" data-uuid="+imgfile.uuid+" data-image="+imgfile.image+">delete</burron>";
				str=str+"</li>";
				
				
			
			imgfilemodalresult.append(str);
		}
		if(resultfilelist[i].image===false)
		{
			//이미지 파일 리스트가 아닌 경우	
			//파일명 그대로 순서대로 모달창 아래쪽에 목록으로 보여줌
			str="";
			filemodalresult.css("height","300px");
				var normalfile=resultfilelist[i];
				var normalfileuri=encodeURIComponent(normalfile.uploadPath+"/"+normalfile.uuid+"_"+normalfile.fileName);
				var normalfilelink=normalfileuri
				
				console.log(normalfileuri);
				
				//파일의 이미지 여부, 파일의 경로정보, 파일의 이름, 파일의 uuid 등을 따로 저장해야(나중에 파일 등록버튼 누를시 필요)
				
				str=str+"<li class='modal_file'>";
				str=str+"<span class='filename'>"+normalfile.fileName+"</span>";
				str=str+"<button class='modal_file_btn' type='button' data-path=\'"+normalfile.uploadPath+"\' data-name="+normalfile.fileName+" data-uuid="+normalfile.uuid+" data-image="+normalfile.image+">delete</burron>";
				str=str+"</li>";
				
			filemodalresult.append(str);
		}
		}
	}
	
	
});