package kr.or.ddit.attafile.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attafile.model.AttaFile;

public interface IAttaFileService {
	int insertAttaFile(AttaFile attaFile);
	
	int deleteAttaFile(int attaseq);
	
	int deletePostAttaFile(int postseq);
	
	List<AttaFile> getAttaFileList(int postseq);
	
	AttaFile getAttaFile(int attaseq);
}
