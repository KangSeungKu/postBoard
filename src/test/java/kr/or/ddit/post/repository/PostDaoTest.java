package kr.or.ddit.post.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.attafile.repository.AttaFileDao;
import kr.or.ddit.attafile.repository.IAttaFileDao;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.repository.IPostDao;
import kr.or.ddit.post.repository.PostDao;
import kr.or.ddit.util.MybatisUtil;

public class PostDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(PostDaoTest.class);
	
	private IPostDao postDao;
	private IAttaFileDao attaDao;
	private SqlSession sqlSession;
	
	@Before
	public void setup() {
		postDao = new PostDao();
		attaDao = new AttaFileDao();
		sqlSession = MybatisUtil.getSession();
	}
	
	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	@Test
	public void insertPostTest() {
		/***Given***/
		//int postseq = 1;
		//postDao.dropPost(sqlSession, postseq);
		
		Post bpost = new Post();
		bpost.setBoardseq(1);
		bpost.setPosttitle("공부 열심히 하라능");
		bpost.setPostcont("힘내세요");
		bpost.setUserid("brown");
		bpost.setPostdel("Y");
		
		AttaFile attaFile = new AttaFile();
		attaFile.setAttafilename("pom.png");
		attaFile.setAttarealfilename("alfjdlskalfkssldfj.png");

		/***When***/
		int insertCnt = postDao.insertPost(sqlSession, bpost);
		attaFile.setPostseq(bpost.getPostseq());
		int aInsertCnt = attaDao.insertAttaFile(sqlSession, attaFile);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, insertCnt);
		assertEquals(1, aInsertCnt);
	}
	
	
	public void updatePostTest() {
		/***Given***/
		Post bpost = new Post();
		bpost.setBoardseq(1);
		bpost.setPosttitle("공부 열심히 하라능");
		bpost.setPostcont("힘내세요");
		bpost.setUserid("brown");
		bpost.setPostdel("Y");
		
		Post bpost2 = new Post();
		bpost2.setBoardseq(1);
		bpost2.setPosttitle("공부 열심히 하라능");
		bpost2.setPostcont("힘내세요(수정)");
		bpost2.setUserid("brown");
		bpost2.setPostdel("N");

		/***When***/
		postDao.insertPost(sqlSession, bpost);
		int updateCnt = postDao.updatePost(sqlSession, bpost2);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	public void deletePostTest() {
		/***Given***/
		int postseq = 2;

		/***When***/
		int deleteCnt = postDao.deletePost(sqlSession, postseq);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
	public void getPostListTest() {
		/***Given***/
		int boardseq = 1;
		Page page = new Page();
		page.setBoardseq(boardseq);
		page.setPage(1);
		page.setPagesize(10);

		/***When***/
		List<Post> list = postDao.getPostList(sqlSession, page);

		/***Then***/
		assertEquals(10, list.size());
	}

	
	public void getPostTotalCntTest() {
		/***Given***/
		int boardseq = 1;

		/***When***/
		int totalCnt = postDao.getPostTotalCnt(sqlSession, boardseq);

		/***Then***/
		assertEquals(11, totalCnt);
	}
	
	public void getPostTest() {
		/***Given***/
		int postseq = 3;

		/***When***/
		Post bpost = postDao.getPost(sqlSession, postseq);

		/***Then***/
		assertEquals("공부 열심히 하라능", bpost.getPosttitle());
	}
}
