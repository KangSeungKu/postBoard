package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.attafile.repository.AttaFileDao;
import kr.or.ddit.attafile.repository.IAttaFileDao;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.repository.IPostDao;
import kr.or.ddit.post.repository.PostDao;
import kr.or.ddit.util.MybatisUtil;

public class PostService implements IPostService {
	
	private IPostDao postDao;
	private IAttaFileDao attaFileDao;
	
	public PostService() {
		postDao = new PostDao();
		attaFileDao = new AttaFileDao();
	}
	
	@Override
	public int insertPost(Post bpost, List<AttaFile> attaList) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = postDao.insertPost(sqlSession, bpost);
		
		if(attaList.size() > 0) {			
			for(AttaFile file : attaList) {
				file.setPostseq(bpost.getPostseq());
				attaFileDao.insertAttaFile(sqlSession, file);
			}
		}
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int updatePost(Post bpost) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int updateCnt = postDao.updatePost(sqlSession, bpost);
		sqlSession.commit();
		sqlSession.close();
		
		return updateCnt;
	}

	@Override
	public int deletePost(int postseq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = postDao.deletePost(sqlSession, postseq);
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCnt;
	}

	@Override
	public Map<String, Object> getPostList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		SqlSession sqlSession = MybatisUtil.getSession();
		
		List<Post> list = postDao.getPostList(sqlSession, page);
		int totalCnt = postDao.getPostTotalCnt(sqlSession, page.getBoardseq());
		
		map.put("postList", list);
		map.put("paginationSize", (int)Math.ceil((double)totalCnt / page.getPagesize()));
		
		sqlSession.close();
		
		return map;
	}

	@Override
	public Post getPost(int postseq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		Post bpost = postDao.getPost(sqlSession, postseq);
		sqlSession.close();
		
		return bpost;
	}

	@Override
	public int insertReplyPost(Post bpost, List<AttaFile> attaList) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = postDao.insertReplyPost(sqlSession, bpost);
		
		if(attaList.size() > 0) {			
			for(AttaFile file : attaList) {
				file.setPostseq(bpost.getPostseq());
				attaFileDao.insertAttaFile(sqlSession, file);
			}
		}
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int getMaxPostNum() {
		SqlSession sqlSession = MybatisUtil.getSession();
		int maxseq = postDao.getMaxPostNum(sqlSession);
		sqlSession.close();
		
		return maxseq;
	}

}
