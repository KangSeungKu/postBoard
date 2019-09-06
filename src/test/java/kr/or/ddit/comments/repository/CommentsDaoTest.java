package kr.or.ddit.comments.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.comments.model.Comments;
import kr.or.ddit.util.MybatisUtil;

public class CommentsDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(CommentsDaoTest.class);
	
	private ICommentsDao commentsDao;
	private SqlSession sqlSession;
	
	@Before
	public void setup() {
		commentsDao = new CommentsDao();
		sqlSession = MybatisUtil.getSession();
	}
	
	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	
	public void insertCommTest() {
		/***Given***/
		Comments comm = new Comments();
		comm.setCommcont("댓글입니다.");
		comm.setCommwriter("brown");
		comm.setPostseq(1);

		/***When***/
		int insertCnt = commentsDao.insertComm(sqlSession, comm);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateCommTest() {
		/***Given***/
		Comments comm = new Comments();
		comm.setCommcont("댓글입니다.(수정)");
		comm.setCommwriter("brown");
		comm.setPostseq(1);
		comm.setCommseq(5);

		/***When***/
		int updateCnt = commentsDao.updateComm(sqlSession, comm);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deleteCommTest() {
		/***Given***/
		Comments comm = new Comments();
		comm.setCommcont("댓글입니다.(수정)");
		comm.setCommwriter("brown");
		comm.setPostseq(1);
		comm.setCommseq(5);

		/***When***/
		int deleteCnt = commentsDao.deleteComm(sqlSession, comm);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void getCommList() {
		/***Given***/
		int postSeq = 1;

		/***When***/
		List<Comments> list = commentsDao.getCommList(sqlSession, postSeq);

		/***Then***/
		assertEquals(1, list.size());
	}
}
