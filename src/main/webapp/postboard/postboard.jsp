<%@page import="kr.or.ddit.user.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	// 문서 로딩이 완료되고 나서
	$(document).ready(function(){
		
		// 사용자 정보 클릭시 이벤트 핸들러
		$(".userTr").on("click", function(){
			console.log("userTr click");
			
			// 클릭된 tr 태그의 자식태그(td)중 첫 번째 텍스트 문자열
			
			// td태그의 텍스트 가져오기(두번째 자식)
			var tdText = $($(this).children()[1]).text();
			console.log("tdText : " + tdText);
			
			// input태그에 저장된 값 확인
			var inputValue = $(this).find("input").val();
			console.log("inputValue : " + inputValue);
			
			// data속성으로 값 가져오기
			// data속성명은 소문자로 치환된다!!
			// data-userId --> $(this).data("userid");
			// 대소문자 주의!!!!!
			var dataValue = $(this).data("postseq");
			console.log("dataValue : " + dataValue);
			
			// input 태그에 값 설정
			$("#postseq").val(dataValue);
			
			// form 태그이용 전송
			console.log("serialize : " + $("#frm").serialize());
			
			//$("#frm").submit();
		});
	});
</script>

</head>

<body>
		<%-- 게시글 번호를 보내줘야 함 --%>
<form id="frm" action="${cp }/" method="get">
	<input type="hidden" id="postseq" name="postseq"/>
</form>

<!-- header -->
<%@include file="/commonJsp/header.jsp" %>
<div class="container-fluid">
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@include file="/commonJsp/left.jsp" %>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${S_POSTBOARDVO.boardnm }</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>게시글 번호</th>
					<th>제목</th>
					<th>작성자아이디</th>
					<th>작성일시</th>
				</tr>
				
							<%-- 게시글 리스트 --%>
							
				<c:forEach items="${postList }" var="bpost" varStatus="loop">
					<tr class="userTr" data-postseq="${bpost.postseq }">
						<td>${(pageVo.page-1) * pageVo.pagesize + loop.count }</td>
						<td>
							<c:forEach begin="0" end="${fn:substring(bpost.posttitle,0,1) }" step="1">
								<c:if test="${fn:substring(bpost.posttitle,0,1) != 0}">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
							</c:forEach>
							<c:if test="${bpost.parentseq != 0 }">→&nbsp;</c:if>
							<c:choose>
								<c:when test="${bpost.postdel == 'Y' }">
									${fn:substring(bpost.posttitle,1,fn:length(bpost.posttitle)) }
								</c:when>
								<c:otherwise>
									[삭제된 게시글 입니다.]
								</c:otherwise>
							</c:choose>
						</td>
						<td>${bpost.userid }</td>
						<td>${bpost.post_dt_fmt }</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>

		<a href="${cp }/postForm" class="btn btn-default pull-right">새글 등록</a>

		<%-- 페이지 이동(사이즈 10) --%>
		<div class="text-center">
			<ul class="pagination">
				<%-- 처음페이지 가기 --%>
				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled">
							<span aria-hidden="true">&laquo;</span>
		      			</li>
					</c:when>
					<c:otherwise>
						<li>							
					      	<a href="${cp }/board?menuBoardSeq=${S_POSTBOARDVO.boardseq }&page=1" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
		      				</a>
		      			</li>
					</c:otherwise>
				</c:choose>
				
				<%-- 이전페이지 가기 --%>
				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled">
							<span aria-hidden="true">&lt;</span>
		      			</li>
					</c:when>
					<c:otherwise>
						<li>							
					      	<a href="${cp }/board?menuBoardSeq=${S_POSTBOARDVO.boardseq }&page=${pageVo.page-1 }" aria-label="Previous">
								<span aria-hidden="true">&lt;</span>
		      				</a>
		      			</li>
					</c:otherwise>
				</c:choose>
				
				<%-- 페이지 표시 --%>
				<c:forEach begin="1" end="${paginationSize }" var="page">				
					<c:choose>
						<c:when test="${page == pageVo.page }">
							<li class="active"><span>${page }</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${cp }/board?menuBoardSeq=${S_POSTBOARDVO.boardseq }&page=${page }">${page }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<%-- 다음페이지 가기 --%>
				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled">
							<span aria-hidden="true">&gt;</span>
			      		</li>
					</c:when>
					<c:otherwise>
						<li>							
					      	<a href="${cp }/board?menuBoardSeq=${S_POSTBOARDVO.boardseq }&page=${pageVo.page+1 }" aria-label="Next">
								<span aria-hidden="true">&gt;</span>
		      				</a>
		      			</li>
					</c:otherwise>
				</c:choose>
				
				<%-- 마지막페이지 가기 --%>
				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled">
							<span aria-hidden="true">&raquo;</span>
			      		</li>
					</c:when>
					<c:otherwise>
						<li>							
					      	<a href="${cp }/board?menuBoardSeq=${S_POSTBOARDVO.boardseq }&page=${paginationSize }" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
		      				</a>
		      			</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>
