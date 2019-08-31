package kr.or.ddit.post.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.attafile.model.AttaFile;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;

public class PostServiceTest {
	private IPostService postService;
	
	@Before
	public void setup() {
		postService = new PostService();
	}
	
	@Test
	public void insertPostTest() {
		/***Given***/
		Post bpost = new Post();
		bpost.setBoardseq(1);
		bpost.setPosttitle("공부 열심히 하라능");
		bpost.setPostcont("힘내세요");
		bpost.setUserid("brown");
		bpost.setPostdel("Y");
		
		ArrayList<AttaFile> attaList = new ArrayList<AttaFile>();
		AttaFile file = new AttaFile();
		file.setAttafilename("ryan.png");
		file.setAttarealfilename("d:\\upload\\2019\\08\\0bf880e0-c78c-49de-8411-d8ce5179d766.png");
		attaList.add(file);
		
		AttaFile file2 = new AttaFile();
		file2.setAttafilename("sally.png");
		file2.setAttarealfilename("d:\\upload\\2019\\08\\a612292f-c46d-4f6d-871b-767d12f92830.png");
		attaList.add(file2);
		
		/***When***/
		int insertCnt = postService.insertPost(bpost, attaList);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	public void updatePostTest() {
		/***Given***/
		Post bpost = new Post();
		bpost.setPostseq(2);
		bpost.setBoardseq(1);
		bpost.setPosttitle("공부 열심히 하라능");
		bpost.setPostcont("힘내세요(수정)");
		bpost.setUserid("brown");
		bpost.setPostdel("N");

		/***When***/
		int updateCnt = postService.updatePost(bpost);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	public void deletePostTest() {
		/***Given***/
		int postseq = 2;

		/***When***/
		int deleteCnt = postService.deletePost(postseq);

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
		Map<String, Object> resultMap = postService.getPostList(page);
		List<Post> list = (List<Post>) resultMap.get("postList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/***Then***/
		assertEquals(10, list.size());
		assertEquals(2, paginationSize);
	}

	
	public void getPostTest() {
		/***Given***/
		int postseq = 1;

		/***When***/
		Post bpost = postService.getPost(postseq);

		/***Then***/
		assertEquals("공부 하기 싫다", bpost.getPosttitle());
	}
}
