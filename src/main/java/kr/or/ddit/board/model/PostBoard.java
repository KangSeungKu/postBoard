package kr.or.ddit.board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostBoard {
	private int boardseq;				// 게시판번호
	private String boardnm;				// 게시판 이름
	private String boardusage;			// 사용여부
	private String userid;				// 등록자
	private Date board_dt;				// 등록일시
	
	
	public PostBoard() {
		this.boardusage = "Y";
	}
	public PostBoard(String boardnm, String boardusage, String userid) {
		this.boardnm = boardnm;
		this.boardusage = boardusage;
		this.userid = userid;
	}
	public PostBoard(int boardseq, String boardnm, String boardusage, String userid, Date board_dt) {
		this.boardseq = boardseq;
		this.boardnm = boardnm;
		this.boardusage = boardusage;
		this.userid = userid;
		this.board_dt = board_dt;
	}
	public int getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}
	public String getBoardnm() {
		return boardnm;
	}
	public void setBoardnm(String boardnm) {
		this.boardnm = boardnm;
	}
	public String getBoardusage() {
		return boardusage;
	}
	public void setBoardusage(String boardusage) {
		this.boardusage = boardusage;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getBoard_dt() {
		return board_dt;
	}
	public void setBoard_dt(Date board_dt) {
		this.board_dt = board_dt;
	}
	public String getReg_dt_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.board_dt);
	}
	@Override
	public String toString() {
		return "PostBoard [boardseq=" + boardseq + ", boardnm=" + boardnm + ", boardusage=" + boardusage + ", userid="
				+ userid + ", board_dt=" + board_dt + "]";
	}
}
