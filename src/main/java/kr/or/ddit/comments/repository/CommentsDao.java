package kr.or.ddit.comments.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.Comments;

public class CommentsDao implements ICommentsDao {

	@Override
	public int insertComm(SqlSession sqlSession, Comments comm) {
		return sqlSession.insert("comm.insertComm", comm);
	}

	@Override
	public int updateComm(SqlSession sqlSession, Comments comm) {
		return sqlSession.update("comm.updateComm", comm);
	}

	@Override
	public int deleteComm(SqlSession sqlSession, Comments comm) {
		return sqlSession.update("comm.deleteComm", comm);
	}

	@Override
	public List<Comments> getCommList(SqlSession sqlSession, int postSeq) {
		return sqlSession.selectList("comm.getCommList", postSeq);
	}

}
