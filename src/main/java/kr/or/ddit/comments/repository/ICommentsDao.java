package kr.or.ddit.comments.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.Comments;

public interface ICommentsDao {
	int insertComm(SqlSession sqlSession, Comments comm);
	
	int updateComm(SqlSession sqlSession, Comments comm);
	
	int deleteComm(SqlSession sqlSession, Comments comm);
	
	List<Comments> getCommList(SqlSession sqlSession, int postSeq);
}
