package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.PostBoard;

public class PostBoardDao implements IPostBoardDao {

	/**
	* Method : insertPostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param board
	* @return
	* Method 설명 : 게시판 생성
	*/
	@Override
	public int insertPostBoard(SqlSession sqlSession, PostBoard board) {
		return sqlSession.insert("postboard.insertPostBoard", board);
	}

	/**
	* Method : updatePostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	@Override
	public int updatePostBoard(SqlSession sqlSession, PostBoard board) {
		return sqlSession.update("postboard.updatePostBoard", board);
	}

	/**
	* Method : deletePostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param boardSeq
	* @return
	* Method 설명 : 게시판 삭제
	*/
	@Override
	public int deletePostBoard(SqlSession sqlSession, int boardSeq) {
		return sqlSession.delete("postboard.deletePostBoard", boardSeq);
	}

	/**
	* Method : getPostBoardList
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 게시판 전체조회
	*/
	@Override
	public List<PostBoard> getPostBoardList(SqlSession sqlSession) {
		return sqlSession.selectList("postboard.getPostBoardList");
	}

	/**
	* Method : getPostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param boardSeq
	* @return
	* Method 설명 : 게시판 단일조회
	*/
	@Override
	public PostBoard getPostBoard(SqlSession sqlSession, int boardSeq) {
		return sqlSession.selectOne("postboard.getPostBoard", boardSeq);
	}

}
