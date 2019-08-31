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

<script>
	$(document).ready(function(){
		
		// 사용자 등록 버튼 클릭 이벤트 핸들러
		$("#regBtn").on("click", function(){
			
			// submit;
			$("#frm").submit();
		});
		
	});
	
	function modiBtn(boardseq) {
		$("#frm" + boardseq).submit();
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
			<form class="form-horizontal" id="frm" role="form" action="${cp }/postBoard" method="post">
				<div class="form-group">
					<label for="userId" class="col-sm-2 control-label">게시판 이름</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="boardnm" name="boardnm">
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="boardusage">
							<option value="Y">사용</option>
						    <option value="N">미사용</option>
						</select>
					</div>
					<div class="col-sm-2">
						<input type="hidden" name="boardFunc" value="inse"/>
						<button type="button" id="regBtn" class="btn btn-primary">생성</button>
					</div>
				</div>
			</form>
			
			<c:forEach items="${boardList }" var="board">
				<form class="form-horizontal" id="frm${board.boardseq }" role="frm" action="${cp }/postBoard" method="post">
				<div class="form-group">
					<label for="userId" class="col-sm-2 control-label">게시판 이름</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="boardnm" name="boardnm"
							value="${board.boardnm }">
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="boardusage">
							<c:choose>
								<c:when test="${board.boardusage == 'Y' }">
									<option value="Y">사용</option>
						    		<option value="N">미사용</option>
								</c:when>
								<c:otherwise>
									<option value="Y">사용</option>
						    		<option value="N" selected="selected">미사용</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
					<div class="col-sm-2">
						<input type="hidden" name="boardFunc" value="modi"/>
						<input type="hidden" name="boardSeq" value="${board.boardseq }"/>
						<button type="button" class="btn btn-default" onclick="modiBtn(${board.boardseq })">수정</button>
					</div>
				</div>
			</form>
			</c:forEach>
		</div>
	</div>
</div>
</body>
</html>
