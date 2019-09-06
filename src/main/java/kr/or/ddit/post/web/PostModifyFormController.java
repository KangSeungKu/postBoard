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
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.attafile.service.AttaFileService;
import kr.or.ddit.attafile.service.IAttaFileService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.FileuploadUtil;

@WebServlet("/postModifyForm")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5)
public class PostModifyFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IPostService postService;
	private IAttaFileService attaService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostModifyFormController.class);
    
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attaService = new AttaFileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postseq = Integer.parseInt(request.getParameter("modipostseq"));
		Post mPost = postService.getPost(postseq);
		List<AttaFile> attaList = attaService.getAttaFileList(postseq);
		
		logger.debug("{}", mPost);
		request.setAttribute("mPost", mPost);
		request.setAttribute("attaList", attaList);
		request.getRequestDispatcher("/post/postModifyForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mode = request.getParameter("mMode");
		logger.debug("{}", mode);
		if(mode.equals("fileDel")) {
			int seq = Integer.parseInt(request.getParameter("postseq"));
			int attaseq = Integer.parseInt(request.getParameter("attaseq"));
			int deleteCnt = attaService.deleteAttaFile(attaseq);
			
			logger.debug("{} {} {}", mode, attaseq, deleteCnt);
			if(deleteCnt == 1) {
				response.sendRedirect(request.getContextPath() + "/postModifyForm?modipostseq=" + seq);
			}
		}else if(mode.equals("modifyPost")) {
			int seq = Integer.parseInt(request.getParameter("postseq"));
			String title = request.getParameter("posttitle");
			String content = request.getParameter("smarteditor");
			
			Post mPost = new Post();
			mPost.setPostseq(seq);
			mPost.setPosttitle(title);
			mPost.setPostcont(content);
			
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
						file.setPostseq(seq);
						p.write(path);
						logger.debug("{}", file);
						attaService.insertAttaFile(file);
					}
				}
			}
			
			
			int updateCnt = postService.updatePost(mPost);
			logger.debug("{} {}", updateCnt, mPost);
			if(updateCnt == 1) {
				response.sendRedirect(request.getContextPath() + "/detail?postseq=" + seq);
			}else {
				doGet(request, response);
			}
		}
	}

}
