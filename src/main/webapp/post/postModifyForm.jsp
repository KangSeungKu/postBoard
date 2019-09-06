<%@page import="kr.or.ddit.user.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basicLib</title>
<%@include file="/commonJsp/basicLib.jsp" %>
<script src="/SE2/js/HuskyEZCreator.js"></script>
<script>
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#modifyBtn").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$('#mMode').val('modifyPost');
				$("#frm").submit();
			}
		}
	})
	
	$("#picture").change(function(){
		var fileInput = document.getElementById("picture");
		var files = fileInput.files;
		
		var existFiles = $('.imgFile').length;
		
		if(files.length + existFiles > 5){
			alert("파일은 5개까지만 입력이 가능합니다.");
			$("#picture").val("");
		}
	});
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}

function delBtn(attaseq) {
	$('#mMode').val('fileDel');
	$('#attaseq').val(attaseq);
	$("#frm").submit();
}
</script>
</head>

<body>
<!-- header -->
<%@include file="/commonJsp/header.jsp" %>
<div class="container-fluid">
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@include file="/commonJsp/left.jsp" %>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<form class="form-horizontal" id="frm" role="form" action="${cp }/postModifyForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="postseq" value="${mPost.postseq }"/>
	<input type="hidden" id="mMode" name="mMode"/>
	<input type="hidden" id="attaseq" name="attaseq"/>
	<div class="col-sm-12 blog-main">
		<h2 class="sub-header">${S_POSTBOARDVO.boardnm }</h2>
	</div>
					<div class="col-sm-12">
						<label for="posttitle" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="posttitle" name="posttitle" value="${mPost.posttitle }">
						</div>
						<hr class="col-sm-10" />
					</div>
					<div class="col-sm-12">
						<label for="postcont" class="col-sm-2 control-label">글 내용</label>
						<div class="col-sm-10">
							<textarea class="form-control" name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;">${mPost.postcont }</textarea>
						</div>
						<hr class="col-sm-10" />
					</div>
					
					<div class="col-sm-12">
						<label for="userId" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-10">
						<c:forEach items="${attaList }" var="afile" varStatus="loop">
							<div class="imgFile col-sm-2">
								${afile.attafilename }
							</div>
							<div class="col-sm-10">
								<button type="button" class="btn btn-default" onclick="delBtn(${afile.attaseq })">삭제</button>
							</div>
						</c:forEach>
						</div>
					</div>
		
					<hr class="col-sm-10" />
					<div class="form-group">
						<label for="userId" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-8">
							<input type="file" class="form-control" id="picture" name="picture" multiple="multiple"
								placeholder="사용자 사진">
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="col-sm-offset-2 col-sm-12">
							<button type="button" id="modifyBtn" class="btn btn-default">저장</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
