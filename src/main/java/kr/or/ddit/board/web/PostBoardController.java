package kr.or.ddit.board.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import kr.or.ddit.user.model.User;

@WebServlet("/postBoard")
public class PostBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostBoardController.class);
	
	private IPostBoardService postBoardService;
	
	@Override
	public void init() throws ServletException {
		postBoardService = new PostBoardService();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/postboard/postBoardForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User userVo = (User) session.getAttribute("S_USERVO");
		
		String func = request.getParameter("boardFunc");
		String boardnm = request.getParameter("boardnm");
		String boardusage = request.getParameter("boardusage");
		
		logger.debug("PostBoard : ({}) {} {} {}", func, boardnm, boardusage, userVo.getUserId());
		
		PostBoard board = new PostBoard(boardnm, boardusage, userVo.getUserId());
		
		if(func.equals("inse")) {	
			postBoardService.insertPostBoard(board);
		}else {
			int boardNum = Integer.parseInt(request.getParameter("boardSeq"));
			board.setBoardseq(boardNum);
			postBoardService.updatePostBoard(board);
		}
		
		doGet(request, response);
	}

}
