package kr.or.ddit.attafile.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attafile.model.AttaFile;

public class AttaFileServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(AttaFileServiceTest.class);
	
	private IAttaFileService attaService;
	
	@Before
	public void setup() {
		attaService = new AttaFileService();
	}
	
	@Test
	public void insertAttaFileTest() {
		/***Given***/
		AttaFile attaFile = new AttaFile();
		attaFile.setAttafilename("brown.png");
		attaFile.setAttarealfilename("d:\\upload\\2019\\09\\377a58c8-365b-4bee-a8c0-e8475d48b21d.png");
		attaFile.setPostseq(1);

		/***When***/
		int insertCnt = attaService.insertAttaFile(attaFile);

		/***Then***/
		assertEquals(1, insertCnt);
	}

	@Test
	public void getAttaFileListTest() {
		/***Given***/
		int postseq = 1;

		/***When***/
		List<AttaFile> list = attaService.getAttaFileList(postseq);

		/***Then***/
		assertEquals(3, list.size());
	}
	
	@Test
	public void getAttaFileTest() {
		/***Given***/
		int attaseq = 81;

		/***When***/
		AttaFile attaFile = attaService.getAttaFile(attaseq);

		/***Then***/
		assertEquals("brown.png", attaFile.getAttafilename());
	}
}
