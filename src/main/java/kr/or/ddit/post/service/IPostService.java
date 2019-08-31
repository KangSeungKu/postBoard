package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;

public interface IPostService {
	/**
	* Method : insertPost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param bpost
	* @return
	* Method 설명 :	게시글 생성
	*/
	int insertPost(Post bpost, List<AttaFile> attaList);
	
	/**
	* Method : updatePost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param bpost
	* @return
	* Method 설명 : 게시글 수정
	*/
	int updatePost(Post bpost);
	
	/**
	* Method : deletePost
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param postseq
	* @return
	* Method 설명 : 게시글 삭제(테이블 삭제 안함)
	*/
	int deletePost(int postseq);
	
	/**
	* Method : getPostList
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 해당 게시판의 게시글 전체조회(페이지네이션 적용)
	*/
	Map<String, Object> getPostList(Page page);
	
	/**
	* Method : getPost
	* 작성자 : PC-16
	* 변경이력 :
	* @param postseq
	* @return
	* Method 설명 : 게시글 단일조회
	*/
	Post getPost(int postseq);
}
