package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.PostBoard;

public interface IPostBoardDao {
	/**
	* Method : insertPostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 생성
	*/
	public int insertPostBoard(SqlSession sqlSession, PostBoard board);
	
	/**
	* Method : updatePostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	public int updatePostBoard(SqlSession sqlSession, PostBoard board);
	
	/**
	* Method : deletePostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param boardSeq
	* @return
	* Method 설명 : 게시판 삭제(delete문을 사용하지 않음)
	*/
	public int deletePostBoard(SqlSession sqlSession, int boardSeq);
	
	/**
	* Method : getPostBoardList
	* 작성자 : PC-16
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체조회
	*/
	public List<PostBoard> getPostBoardList(SqlSession sqlSession);
	
	/**
	* Method : getPostBoard
	* 작성자 : PC-16
	* 변경이력 :
	* @param boardSeq
	* @return
	* Method 설명 : 게시판 단일조회
	*/
	public PostBoard getPostBoard(SqlSession sqlSession, int boardSeq);
}
