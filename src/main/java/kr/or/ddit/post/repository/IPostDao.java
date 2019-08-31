package kr.or.ddit.post.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;

public interface IPostDao {
	/**
	* Method : insertPost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param bpost
	* @return
	* Method 설명 :	게시글 생성
	*/
	int insertPost(SqlSession sqlSession, Post bpost);
	
	/**
	* Method : updatePost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param bpost
	* @return
	* Method 설명 : 게시글 수정
	*/
	int updatePost(SqlSession sqlSession, Post bpost);
	
	/**
	* Method : deletePost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param postseq
	* @return
	* Method 설명 : 게시글 삭제(테이블 삭제 안함)
	*/
	int deletePost(SqlSession sqlSession, int postseq);
	
	/**
	* Method : getPostList
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 해당 게시판의 게시글 전체조회(페이지네이션 적용)
	*/
	List<Post> getPostList(SqlSession sqlSession, Page page);
	
	/**
	* Method : getPostTotalCnt
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param boardseq
	* @return
	* Method 설명 : 전체 사용자 건수 조회
	*/
	int getPostTotalCnt(SqlSession sqlSession, int boardseq);
	
	/**
	* Method : getPost
	* 작성자 : PC-16
	* 변경이력 :
	* @param postseq
	* @return
	* Method 설명 : 게시글 단일조회
	*/
	Post getPost(SqlSession sqlSession, int postseq);
	
	/**
	* Method : dropPost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param postseq
	* @return
	* Method 설명 : 게시글 삭제
	*/
	int dropPost(SqlSession sqlSession, int postseq);
}
