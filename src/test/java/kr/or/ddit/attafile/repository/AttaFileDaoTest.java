package kr.or.ddit.attafile.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.util.MybatisUtil;

public class AttaFileDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(AttaFileDaoTest.class);
	
	private IAttaFileDao attaFileDao;
	private SqlSession sqlSession;
	
	//int attaseq = 79;
	
	@Before
	public void setup() {
		attaFileDao = new AttaFileDao();
		sqlSession = MybatisUtil.getSession();
		
		//attaFileDao.deleteAttaFile(sqlSession, attaseq);
	}
	
	@After
	public void tearDown() {
		sqlSession.close();
	}

	@Test
	public void insertAttaFileTest() {
		/***Given***/
		AttaFile attaFile = new AttaFile();
		attaFile.setAttafilename("brown.png");
		attaFile.setAttarealfilename("d:\\upload\\2019\\09\\377a58c8-365b-4bee-a8c0-e8475d48b21d.png");
		attaFile.setPostseq(1);

		/***When***/
		int insertCnt = attaFileDao.insertAttaFile(sqlSession, attaFile);
		sqlSession.commit();

		/***Then***/
		assertEquals(1, insertCnt);
	}

	@Test
	public void getAttaFileListTest() {
		/***Given***/
		int postseq = 1;

		/***When***/
		List<AttaFile> list = attaFileDao.getAttaFileList(sqlSession, postseq);

		/***Then***/
		assertEquals(2, list.size());
	}
	
	@Test
	public void getAttaFileTest() {
		/***Given***/
		int attaseq = 80;

		/***When***/
		AttaFile attaFile = attaFileDao.getAttaFile(sqlSession, attaseq);

		/***Then***/
		assertEquals("brown.png", attaFile.getAttafilename());
	}
}
