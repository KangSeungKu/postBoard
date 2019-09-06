package kr.or.ddit.attafile.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;

public interface IAttaFileDao {
	int insertAttaFile(SqlSession sqlSession, AttaFile attaFile);
	
	int deleteAttaFile(SqlSession sqlSession, int attaseq);
	
	int deletePostAttaFile(SqlSession sqlSession, int postseq);
	
	List<AttaFile> getAttaFileList(SqlSession sqlSession, int postseq);
	
	AttaFile getAttaFile(SqlSession sqlSession, int attaseq);
}
