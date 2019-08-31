package kr.or.ddit.dbConnection;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.PostBoard;
import kr.or.ddit.board.service.IPostBoardService;
import kr.or.ddit.board.service.PostBoardService;

/**
 * Servlet implementation class PostBoardListServlet
 */
@WebServlet( urlPatterns = "/postBoardListInit", loadOnStartup = 2)
public class PostBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IPostBoardService postBoardService;
	
	{
		postBoardService = new PostBoardService();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PostBoardListServlet.class);
	
    @Override
    	public void init(ServletConfig config) throws ServletException {
		/*
		 * ServletContext application = config.getServletContext(); //List<PostBoard>
		 * postBoardList = postBoardService.getPostBoardList();
		 * application.setAttribute("boardList", postBoardService.getPostBoardList());
		 * logger.debug("PostBoardListServlet init() : {}",
		 * postBoardService.getPostBoardList().toString());
		 */
    	}
}
