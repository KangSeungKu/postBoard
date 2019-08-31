package kr.or.ddit.attafile.repository;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;

public class AttaFileDao implements IAttaFileDao {

	@Override
	public int insertAttaFile(SqlSession sqlSession, AttaFile attaFile) {
		return sqlSession.insert("attaFile.insertAttaFile", attaFile);
	}

}
