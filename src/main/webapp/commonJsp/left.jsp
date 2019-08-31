<%@page import="kr.or.ddit.board.model.PostBoard"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.service.PostBoardService"%>
<%@page import="kr.or.ddit.board.service.IPostBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	IPostBoardService postBoardService = new PostBoardService();
	List<PostBoard> boardList = postBoardService.getPostBoardList();
	request.setAttribute("boardList", boardList);
%>
<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp }/postBoard">게시판 생성<span class="sr-only">(current)</span></a></li>
		
		<%-- boardList --%>
		<c:forEach items="${boardList }" var="board">
			<c:if test="${board.boardusage == 'Y' }">
				<li class="active"><a href="${cp }/board?menuBoardSeq=${board.boardseq }">${board.boardnm }<span class="sr-only">(current)</span></a></li>
			</c:if>
		</c:forEach>
		<%-- <li class="active"><a href="${cp }/">자유 게시판<span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp }/">QnA게시판<span class="sr-only">(current)</span></a></li> --%>
		
</ul>