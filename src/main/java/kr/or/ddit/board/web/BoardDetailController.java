package kr.or.ddit.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.attafile.service.AttaFileService;
import kr.or.ddit.attafile.service.IAttaFileService;
import kr.or.ddit.board.model.PostBoard;
import kr.or.ddit.comments.model.Comments;
import kr.or.ddit.comments.service.CommentsService;
import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.user.model.User;

@WebServlet("/detail")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory.getLogger(BoardDetailController.class);
	
	private IPostService postService;
	private IAttaFileService attaFileService;
	private ICommentsService commService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attaFileService = new AttaFileService();
		commService = new CommentsService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int postSeq = Integer.parseInt(request.getParameter("postseq"));
		Post bPost = postService.getPost(postSeq);
		List<AttaFile> attaList = attaFileService.getAttaFileList(postSeq);
		List<Comments> commList = commService.getCommList(postSeq);
		
		request.setAttribute("commList", commList);
		request.setAttribute("attaList", attaList);
		request.setAttribute("bPost", bPost);
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mode = request.getParameter("pmode");
		logger.debug("{}", mode);
		if(mode.equals("del")) {
			try {
				HttpSession session = request.getSession();
				PostBoard boardVo = (PostBoard) session.getAttribute("S_POSTBOARDVO");
				
				int postseq = Integer.parseInt(request.getParameter("delpostseq"));
				int deleteCnt = postService.deletePost(postseq);
				if(deleteCnt == 1) {
					response.sendRedirect(request.getContextPath() + "/board?menuBoardSeq=" + boardVo.getBoardseq());
				}else {
					doGet(request, response);
				}
			}catch(Exception e) {
				doGet(request, response);
			}
		}else if(mode.equals("insComm")) {
			HttpSession session = request.getSession();
			
			int seq = Integer.parseInt(request.getParameter("commpostseq"));
			String comment = request.getParameter("commcomment");
			User user = (User) session.getAttribute("S_USERVO");
			
			logger.debug("seq : {}, comment : {}, writer : {}", seq, comment, user.getUserId());
			Comments comm = new Comments();
			comm.setCommwriter(user.getUserId());
			comm.setCommcont(comment);
			comm.setPostseq(seq);
			
			int insertCnt = commService.insertComm(comm);
			if(insertCnt == 1) {
				response.sendRedirect(request.getContextPath() + "/detail?postseq=" + seq);
			}
		}else if(mode.equals("commdele")) {
			int seq = Integer.parseInt(request.getParameter("commseq"));
			int delpostseq = Integer.parseInt(request.getParameter("commdelpostseq"));
			logger.debug("seq : {}", seq);
			Comments comm = new Comments();
			comm.setCommseq(seq);
			
			int deleteCnt = commService.deleteComm(comm);
			if(deleteCnt == 1) {
				response.sendRedirect(request.getContextPath() + "/detail?postseq=" + delpostseq);
			}
		}
		
	}

}
