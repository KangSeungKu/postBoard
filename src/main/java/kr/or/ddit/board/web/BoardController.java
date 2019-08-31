package kr.or.ddit.board.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.PostBoard;
import kr.or.ddit.board.service.IPostBoardService;
import kr.or.ddit.board.service.PostBoardService;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private IPostBoardService postBoardService;
	private IPostService postService;
	
	@Override
		public void init() throws ServletException {
			postBoardService = new PostBoardService();
			postService = new PostService();
		}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardSeq = Integer.parseInt(request.getParameter("menuBoardSeq"));
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pagesize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
		
		Page p = new Page(page, pagesize, boardSeq);
		request.setAttribute("pageVo", p);
		
		Map<String, Object> resultMap = postService.getPostList(p);
		List<Post> postList = (List<Post>) resultMap.get("postList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		PostBoard board = postBoardService.getPostBoard(boardSeq);
		
		HttpSession session = request.getSession();
		session.setAttribute("S_POSTBOARDVO", board);
		
		logger.debug("Session : {}", session.getAttribute("S_POSTBOARDVO"));
		
		request.setAttribute("postList", postList);
		request.setAttribute("paginationSize", paginationSize);
		request.getRequestDispatcher("/postboard/postboard.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
