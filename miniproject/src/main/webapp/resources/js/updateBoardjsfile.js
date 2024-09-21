/**
 * updateBoard.jsp 파일용 js 코드   
 */
 
$(document).ready(function(){
    var bno = '${board.bno}';
	var imgumodal=$('#update_imgfile_modal');
	var cmnumodal=$('#update_cmnfile_modal');
	var imgview=$('.upimgfile_layout');
	var cmnview=$('.updatecmnfile_view');
	var imgmodalbtn=$('.imgfile_modal_btn');
	var cmnmodalbtn=$('.comfile_modal_btn');
	var imgmodalclbtn=$('#update_imgfile_modal_close_btn');
	var cmnmodalclbtn=$('#update_cmnfile_modal_close_btn');
	
	const imguposition=$('#imgdragupdate');
	const cmnuposition=$('#cmndragupdate');
	var direcimg=$('#imgfileupdatedirect');
	var direccmn=$('#cmnfileupdatedirect');

	var submitform=$("form[role='form']");
	
	loadattachfile(bno);
	$("button[type='submit']").on("click", function(e){
		const imgdatapath=[];
		const imgdatauuid=[];
		const imgdataname=[];
		const imgdatatype=[];
		const datapath=[];
		const datauuid=[];
		const dataname=[];
		const datatype=[];
		const imgdataobj=document.querySelectorAll(".upimg_obj");
		const dataobj=document.querySelectorAll(".upcmn_obj");
		var str="";
		var i=0;
		if(imgdataobj.length>0){
		for(i=0;i<imgdataobj.length;i++){
			var imgfile=imgdataobj[i].querySelector(".up_obj");
			var name=imgdataobj[i].querySelector("p").textContent;
			imgdatapath.push(imgfile.dataset.path);
			imgdatauuid.push(imgfile.dataset.uuid);
			imgdataname.push(name);
			imgdatatype.push(imgfile.dataset.image);
		}
		for(i=0;i<imgdataobj.length;i++){
			var idataname=imgdataname[i];
			var idatauuid=imgdatauuid[i];
			var idatapath=imgdatapath[i];
			var idatatype=imgdatatype[i];
			
			str = str + "<input type='hidden' name='attachlist["+i+"].fileName' value='"+idataname+"'>";
		    str = str + "<input type='hidden' name='attachlist["+i+"].uuid' value='"+idatauuid+"'>";
		    str = str + "<input type='hidden' name='attachlist["+i+"].uploadPath' value='"+idatapath+"'>";
		    str = str + "<input type='hidden' name='attachlist["+i+"].image' value='"+idatatype+"'>";
		}
		}
		
		var j=0;
		if(dataobj.length>0){
		for(var j=i;j<dataobj.length+i;j++){
			var file=dataobj[j-i].querySelector(".up_obj");
			var name=dataobj[j-i].querySelector("p").textContent;
			datapath.push(file.dataset.path);
			datauuid.push(file.dataset.uuid);
			dataname.push(name);
			datatype.push(file.dataset.image);
		}
		for(var j=i;j<dataobj.length+i;j++){
			console.log(j-i);
			var cdataname=dataname[j-i];
			var cdatauuid=datauuid[j-i];
			var cdatapath=datapath[j-i];
			var cdatatype=datatype[j-i];
			
			str = str + "<input type='hidden' name='attachlist["+j+"].fileName' value='"+cdataname+"'>";
		    str = str + "<input type='hidden' name='attachlist["+j+"].uuid' value='"+cdatauuid+"'>";
		    str = str + "<input type='hidden' name='attachlist["+j+"].uploadPath' value='"+cdatapath+"'>";
		    str = str + "<input type='hidden' name='attachlist["+j+"].image' value='"+cdatatype+"'>";
		}
		}
		console.log(str);
		submitform.append(str);
		submitform.submit();		
		
	});
	
	
	imgview.on("click", "button",function(e){
		var fileuuid=$(this).data("uuid");
		var fileimg=$(this).data("image");
		var filepath=$(this).data("path");
		var obj=$(this).closest("div");
		var filename=obj.find("p").text();
		var csrfToken = $("#_csrf").val();
		var orgfileuri=encodeURIComponent(filepath+"/"+fileuuid+"_"+filename);
		
		$.ajax({
			type:'post',
			url:'/deletefile',
			data:{fileuri:orgfileuri,filetype:fileimg},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				obj.remove();
				const filelist=document.querySelectorAll(".upimg_obj");
				if(filelist.length===0){
					imgview.css("height","0px");
					imgview.empty();
				}
			},
			error: function(error){
				console.error('파일삭제 오류');
			}
				
		});
	});
	cmnview.on("click", "button",function(e){
		var fileuuid=$(this).data("uuid");
		var fileimg=$(this).data("image");
		var filepath=$(this).data("path");
		var obj=$(this).closest("div");
		var filename=obj.find("p").text();
		var orgfileuri=encodeURIComponent(filepath+"/"+fileuuid+"_"+filename);		
		var csrfToken = $("#_csrf").val();
		
		$.ajax({
			type:'post',
			url:'/deletefile',
			data:{fileuri:orgfileuri,filetype:fileimg},
			dataType:'JSON',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
			success: function(response){
				obj.remove();
				const filelist=document.querySelectorAll(".upcmn_obj");
				if(filelist.length===0){
					cmnview.css("height","0px");
					cmnview.empty();
				}
			},
			error: function(error){
				console.error('파일삭제 오류');
			}
				
		});
	});
	
	
	imguposition.on("dragover",function(e){
		console.log('파일 감지');
		 e.preventDefault();
	});
	cmnuposition.on("dragover",function(e){
		console.log('파일 감지');
		 e.preventDefault();
	});
	imguposition.on("drop",function(e){
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
			type:'post', 
			url:'/uploadFile', 
			data:formData, 
			dataType:'json', 
			beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
            contentType: false,
            processData: false,
			success:function(response){
				resultfilelist=response['attachfilelist'];
				thumfilelist=response['thumbnaillist'];
				upresult=response['result'];
				if(upresult==="upload_success"){
					dispalyfileview(resultfilelist);
				}else{
					
				}
			},
            error: function(error){
            	console.error('파일 업로드 에러!!');
            }
		});
	});
	cmnuposition.on("drop",function(e){
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
			type:'post', 
			url:'/uploadFile', 
			data:formData, 
			dataType:'json', 
			beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
            contentType: false,
            processData: false,
			success:function(response){
				resultfilelist=response['attachfilelist'];
				thumfilelist=response['thumbnaillist'];
				upresult=response['result'];
				if(upresult==="upload_success"){
					dispalyfileview(resultfilelist);
				}else{
					
				}
				
			},
            error: function(error){
            	console.error('파일 업로드 에러!!');
            }
		});
	});
	direcimg.on("change",function(e){
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
			type:'post', 
			url:'/uploadFile', 
			data:formData, 
			dataType:'json',
			beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
            contentType: false,
            processData: false,
			success:function(response){
				resultfilelist=response['attachfilelist'];
				thumfilelist=response['thumbnaillist'];
				upresult=response['result'];
				if(upresult==="upload_success"){
					dispalyfileview(resultfilelist);
				}else{
					
				}
				
			},
            error: function(error){
            	console.error('파일 업로드 에러!!');
            }
		});
	});
	direccmn.on("change",function(e){
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
			type:'post', url:'/uploadFile', data:formData, dataType:'json', 
			beforeSend: function(xhr) {
	            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	        },
            contentType: false,
            processData: false,
			success:function(response){
				resultfilelist=response['attachfilelist'];
				thumfilelist=response['thumbnaillist'];
				upresult=response['result'];
				if(upresult==="upload_success"){
					dispalyfileview(resultfilelist);
				}
				else{
					
				}
			},
            error: function(error){
            	console.error('파일 업로드 에러!!');
            }
		});
	});
	
	
	function loadattachfile(bno){
		var vbno=bno;
	$.ajax({
			type:'get',
			url:'fileload',
			data:{bno: vbno},
			dataType:'json',
			success: function(response){
				if(response['result']==='exist'){
					achfilelist=response['attachlist'];
					dispalyfileview(achfilelist);
				}else{
					console.log('파일이 존재하지 않습니다.');
				}
			},
			error: function(){
				console.error('파일을 불러오는 것을 실패하였다');
			}
		});
	}
	
	function dispalyfileview(achfilelist){
		var vachfilelist=achfilelist;
		var str="";
		var vstr="";
		for(var i=0;i<vachfilelist.length;i++){
			var file=vachfilelist[i];
			if(file.image===true){
				//<div><p></p><img></img><button></button></div>
				//파일의 이름과 uuid, 'th_', 경로를 조합해서 썸네일 이미지 정보를 가져옴
				var orguri=file.uploadPath;
				var thuri=encodeURIComponent(file.uploadPath+"/th_"+file.uuid+"_"+file.fileName);
				str=str+'<div class="upimg_obj"><p>'+file.fileName+'</p>';
				str=str+'<img src="/display?fileuri='+thuri+'"></img>';
				str=str+'<button class="up_obj" type="button" data-path="'+file.uploadPath+'" data-uuid="'+file.uuid+'" data-image="'+file.image+'" data-name="'+file.fileName+'">delete</button>';
				str=str+'</div>';
			}
			else{
				//<div><p></p><button></button></div>
				//파일의 이름만 보여준다.
				vstr=vstr+'<div class="upcmn_obj"><p>'+file.fileName+'</p>';
				vstr=vstr+'<button class="up_obj" type="button" data-path="'+file.uploadPath+'" data-uuid="'+file.uuid+'" data-image="'+file.image+'" data-name="'+file.fileName+'">delete</button>';
				vstr=vstr+'</div>';
			}
		}
		imgview.append(str);
		cmnview.append(vstr);		
	}
	
	imgmodalbtn.on("click",function(){
		imgumodal.modal('show');
	});
	cmnmodalbtn.on("click",function(){
		cmnumodal.modal('show');
	});
	imgmodalclbtn.on("click",function(e){
		imgumodal.modal('hide'); 
	});
	cmnmodalclbtn.on("click",function(e){
		cmnumodal.modal('hide');
	});
});
	
	function goBack(){
    	window.history.back();
	}