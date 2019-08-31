package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.model.PostBoard;

public class PostBoardServiceTest {
	private IPostBoardService postBoardService;
	
	@Before
	public void setup() {
		postBoardService = new PostBoardService();
	}
	
	
	public void insertPostBoardTest() throws ParseException {
		/***Given***/
		PostBoard board = new PostBoard();
		board.setBoardnm("공지사항");
		board.setUserid("brown");
		board.setBoard_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-29"));

		/***When***/
		int insertCnt = postBoardService.insertPostBoard(board);

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
		int updateCnt = postBoardService.updatePostBoard(board);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deletePostBoardTest() {
		/***Given***/
		int boardSeq = 3;

		/***When***/
		int deleteCnt = postBoardService.deletePostBoard(boardSeq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void getPostBoardListTest() {
		/***Given***/

		/***When***/
		List<PostBoard> list = postBoardService.getPostBoardList();

		/***Then***/
		assertEquals(3, list.size());
	}

	@Test
	public void getPostBoardTest() {
		/***Given***/
		int boardSeq = 1;

		/***When***/
		PostBoard postBoard = postBoardService.getPostBoard(boardSeq);

		/***Then***/
		assertEquals("자유게시판", postBoard.getBoardnm());
	}

}
