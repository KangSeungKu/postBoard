package kr.or.ddit.post.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;

public class PostDao implements IPostDao {

	@Override
	public int insertPost(SqlSession sqlSession, Post bpost) {
		return sqlSession.insert("bPost.insertPost", bpost);
	}

	@Override
	public int updatePost(SqlSession sqlSession, Post bpost) {
		return sqlSession.update("bPost.updatePost", bpost);
	}

	@Override
	public int deletePost(SqlSession sqlSession, int postseq) {
		return sqlSession.delete("bPost.deletePost", postseq);
	}

	@Override
	public List<Post> getPostList(SqlSession sqlSession, Page page) {
		return sqlSession.selectList("bPost.getPostList", page);
	}
	
	@Override
	public int getPostTotalCnt(SqlSession sqlSession, int boardseq) {
		return sqlSession.selectOne("bPost.getPostTotalCnt", boardseq);
	}

	@Override
	public Post getPost(SqlSession sqlSession, int postseq) {
		return sqlSession.selectOne("bPost.getPost", postseq);
	}

	@Override
	public int dropPost(SqlSession sqlSession, int postseq) {
		return sqlSession.delete("bPost.dropPost", postseq);
	}

	@Override
	public int insertReplyPost(SqlSession sqlSession, Post bpost) {
		return sqlSession.insert("bPost.insertReplyPost", bpost);
	}

	@Override
	public int getMaxPostNum(SqlSession sqlSession) {
		return sqlSession.selectOne("bPost.getMaxPostNum");
	}

}
