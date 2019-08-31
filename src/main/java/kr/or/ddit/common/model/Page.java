package kr.or.ddit.common.model;

public class Page {
	private int page;
	private int pagesize;
	private int boardseq;

	public Page() {
	}
	
	public Page(int page, int pagesize, int boardseq) {
		this.page = page;
		this.pagesize = pagesize;
		this.boardseq = boardseq;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getBoardseq() {
		return boardseq;
	}

	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}
	
}
