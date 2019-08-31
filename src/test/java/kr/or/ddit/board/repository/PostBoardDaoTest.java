package kr.or.ddit.board.repository;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.PostBoard;
import kr.or.ddit.util.MybatisUtil;

public class PostBoardDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(PostBoardDaoTest.class);
	
	private IPostBoardDao postBoardDao;
	private SqlSession sqlSession;
	
	@Before
	public void setup() {
		postBoardDao = new PostBoardDao();
		sqlSession = MybatisUtil.getSession();
	}
	
	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	
	public void insertPostBoardTest() throws ParseException {
		/***Given***/
		PostBoard board = new PostBoard();
		board.setBoardnm("공지사항");
		board.setUserid("brown");
		board.setBoard_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-29"));

		/***When***/
		int insertCnt = postBoardDao.insertPostBoard(sqlSession, board);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	public void updatePostBoardTest() throws ParseException {
		/***Given***/
		PostBoard board = new PostBoard();
		board.setBoardseq(3);
		board.setBoardnm("공지사항(수정)");
		board.setUserid("brown");
		board.setBoard_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-29"));

		/***When***/
		int updateCnt = postBoardDao.updatePostBoard(sqlSession, board);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deletePostBoardTest() {
		/***Given***/
		int boardSeq = 3;

		/***When***/
		int deleteCnt = postBoardDao.deletePostBoard(sqlSession, boardSeq);
		sqlSession.commit();
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void getPostBoardListTest() {
		/***Given***/

		/***When***/
		List<PostBoard> list = postBoardDao.getPostBoardList(sqlSession);

		/***Then***/
		assertEquals(3, list.size());
	}

	@Test
	public void getPostBoardTest() {
		/***Given***/
		int boardSeq = 1;

		/***When***/
		PostBoard postBoard = postBoardDao.getPostBoard(sqlSession, boardSeq);

		/***Then***/
		assertEquals("자유게시판", postBoard.getBoardnm());
	}
}
