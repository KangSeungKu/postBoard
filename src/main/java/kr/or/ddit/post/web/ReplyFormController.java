package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.board.model.PostBoard;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.FileuploadUtil;

@WebServlet("/replyForm")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5)
public class ReplyFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory.getLogger(ReplyFormController.class);
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postseq = Integer.parseInt(request.getParameter("reppostseq"));
		Post bpost = postService.getPost(postseq);
		
		request.setAttribute("rPost", bpost);
		request.getRequestDispatcher("/post/replyForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		PostBoard postBoardVo = (PostBoard) session.getAttribute("S_POSTBOARDVO");
		int boardseq = postBoardVo.getBoardseq();
		String posttitle = request.getParameter("posttitle");
		String postcont = request.getParameter("smarteditor");
		User userVo = (User) session.getAttribute("S_USERVO");
		String userid = userVo.getUserId();
		String postdel = "Y";
		int postseq = Integer.parseInt(request.getParameter("rpostseq"));
		int gn = Integer.parseInt(request.getParameter("rgn"));
		Post pt = new Post(boardseq, posttitle, postcont, userid, postdel, postseq, gn);
		logger.debug("{}", pt);
		List<AttaFile> attaList = new ArrayList<AttaFile>();
		
		String filename = "";
		String path = "";
		Collection<Part> parts = request.getParts();
		for(Part p : parts) {
			if(p.getSize() > 0) {
				if("picture".equals(p.getName())) {		
					filename = FileuploadUtil.getFilename(
							p.getHeader("Content-Disposition"));						// 사용자가 업로드한 파일명
					String realFilename = UUID.randomUUID().toString();
					String ext = FileuploadUtil.getFileExtension(
							p.getHeader("Content-Disposition"));
					path = FileuploadUtil.getPath() + realFilename + ext;
					AttaFile file = new AttaFile(filename, path);
					attaList.add(file);
					p.write(path);
				}
			}
		}
		
		logger.debug("{} {}", pt, attaList);
		int replyCnt = postService.insertReplyPost(pt, attaList);
		
		int nextSeq = postService.getMaxPostNum();
		response.sendRedirect(request.getContextPath() + "/detail?postseq=" + nextSeq);
	
	}

}
