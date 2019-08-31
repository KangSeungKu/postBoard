package kr.or.ddit.user.repository;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.User;

public interface IUserDao {
	
	/**
	* Method : getUser
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 단일 사용자 조회
	*/
	User getUser(SqlSession sqlSession, String userId);
}
