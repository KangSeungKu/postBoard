package kr.or.ddit.attafile.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.attafile.repository.AttaFileDao;
import kr.or.ddit.attafile.repository.IAttaFileDao;
import kr.or.ddit.util.MybatisUtil;

public class AttaFileService implements IAttaFileService {
	
	private IAttaFileDao attaFileDao;
	
	public AttaFileService() {
		attaFileDao = new AttaFileDao();
	}
	
	@Override
	public int insertAttaFile(AttaFile attaFile) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = attaFileDao.insertAttaFile(sqlSession, attaFile);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int deleteAttaFile(int attaseq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = attaFileDao.deleteAttaFile(sqlSession, attaseq);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public List<AttaFile> getAttaFileList(int postseq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<AttaFile> list = attaFileDao.getAttaFileList(sqlSession, postseq);
		sqlSession.close();
		return list;
	}

	@Override
	public int deletePostAttaFile(int postseq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = attaFileDao.deletePostAttaFile(sqlSession, postseq);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public AttaFile getAttaFile(int attaseq) {
		SqlSession sqlSession = MybatisUtil.getSession();
		AttaFile attaFile = attaFileDao.getAttaFile(sqlSession, attaseq);
		sqlSession.commit();
		sqlSession.close();
		return attaFile;
	}

}
