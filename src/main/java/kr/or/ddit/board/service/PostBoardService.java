package kr.or.ddit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.PostBoard;
import kr.or.ddit.board.repository.IPostBoardDao;
import kr.or.ddit.board.repository.PostBoardDao;
import kr.or.ddit.util.MybatisUtil;

public class PostBoardService implements IPostBoardService{
	private IPostBoardDao postBoardDao;
	
	public PostBoardService() {
		postBoardDao = new PostBoardDao();
	}
	
	@Override
	public int insertPostBoard(PostBoard board) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = postBoardDao.insertPostBoard(sqlSession, board);
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int updatePostBoard(PostBoard board) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int updateCnt = postBoardDao.updatePostBoard(sqlSession, board);
		sqlSession.commit();
		sqlSession.close();
		
		return updateCnt;
	}

	@Override
	public int deletePostBoard(int boardSeq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = postBoardDao.deletePostBoard(sqlSession, boardSeq);
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCnt;
	}

	@Override
	public List<PostBoard> getPostBoardList() {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<PostBoard> list = postBoardDao.getPostBoardList(sqlSession);
		sqlSession.close();
		
		return list;
	}

	@Override
	public PostBoard getPostBoard(int boardSeq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		PostBoard board = postBoardDao.getPostBoard(sqlSession, boardSeq);
		sqlSession.close();
		
		return board;
	}

}
