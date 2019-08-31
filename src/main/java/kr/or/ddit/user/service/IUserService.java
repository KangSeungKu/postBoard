package kr.or.ddit.user.service;

import kr.or.ddit.user.model.User;

public interface IUserService {
	
	/**
	* Method : getUser
	* 작성자 : PC-16
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 단일 사용자 조회
	*/
	User getUser(String userId);
}
