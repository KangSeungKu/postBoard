package kr.or.ddit.comments.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.Comments;
import kr.or.ddit.comments.repository.CommentsDao;
import kr.or.ddit.comments.repository.ICommentsDao;
import kr.or.ddit.util.MybatisUtil;

public class CommentsService implements ICommentsService {
	
	private ICommentsDao commentsDao;
	
	public CommentsService() {
		commentsDao = new CommentsDao();
	}
	
	@Override
	public int insertComm(Comments comm) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = commentsDao.insertComm(sqlSession, comm);
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int updateComm(Comments comm) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int updateCnt = commentsDao.updateComm(sqlSession, comm);
		sqlSession.commit();
		sqlSession.close();
		
		return updateCnt;
	}

	@Override
	public int deleteComm(Comments comm) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = commentsDao.deleteComm(sqlSession, comm);
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCnt;
	}

	@Override
	public List<Comments> getCommList(int postSeq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<Comments> list = commentsDao.getCommList(sqlSession, postSeq);
		sqlSession.close();
		return list;
	}

}
