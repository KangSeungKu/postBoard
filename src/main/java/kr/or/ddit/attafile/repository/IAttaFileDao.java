package kr.or.ddit.attafile.repository;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;

public interface IAttaFileDao {
	int insertAttaFile(SqlSession sqlSession, AttaFile attaFile);
}
