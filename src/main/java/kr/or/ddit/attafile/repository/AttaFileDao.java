package kr.or.ddit.attafile.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;

public class AttaFileDao implements IAttaFileDao {

	@Override
	public int insertAttaFile(SqlSession sqlSession, AttaFile attaFile) {
		return sqlSession.insert("attaFile.insertAttaFile", attaFile);
	}

	@Override
	public int deleteAttaFile(SqlSession sqlSession, int attaseq) {
		return sqlSession.delete("attaFile.deleteAttaFile", attaseq);
	}

	@Override
	public List<AttaFile> getAttaFileList(SqlSession sqlSession, int postseq) {
		return sqlSession.selectList("attaFile.getAttaFileList", postseq);
	}

	@Override
	public int deletePostAttaFile(SqlSession sqlSession, int postseq) {
		return sqlSession.delete("attaFile.deletePostAttaFile", postseq);
	}

	@Override
	public AttaFile getAttaFile(SqlSession sqlSession, int attaseq) {
		return sqlSession.selectOne("attaFile.getAttaFile", attaseq);
	}

}
