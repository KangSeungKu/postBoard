package kr.or.ddit.comments.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comments {
	private int commseq;
	private String commcont;
	private String commwriter;
	private Date comm_dt;
	private String commdel;
	private int postseq;
	public Comments() {
	}
	public Comments(int commseq, String commcont, String commwriter, Date comm_dt, String commdel, int postseq) {
		this.commseq = commseq;
		this.commcont = commcont;
		this.commwriter = commwriter;
		this.comm_dt = comm_dt;
		this.commdel = commdel;
		this.postseq = postseq;
	}
	public int getCommseq() {
		return commseq;
	}
	public void setCommseq(int commseq) {
		this.commseq = commseq;
	}
	public String getCommcont() {
		return commcont;
	}
	public void setCommcont(String commcont) {
		this.commcont = commcont;
	}
	public String getCommwriter() {
		return commwriter;
	}
	public void setCommwriter(String commwriter) {
		this.commwriter = commwriter;
	}
	public Date getComm_dt() {
		return comm_dt;
	}
	public void setComm_dt(Date comm_dt) {
		this.comm_dt = comm_dt;
	}
	public String getComm_dt_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(comm_dt);
	}
	public String getCommdel() {
		return commdel;
	}
	public void setCommdel(String commdel) {
		this.commdel = commdel;
	}
	public int getPostseq() {
		return postseq;
	}
	public void setPostseq(int postseq) {
		this.postseq = postseq;
	}
	@Override
	public String toString() {
		return "Comments [commseq=" + commseq + ", commcont=" + commcont + ", commwriter=" + commwriter + ", comm_dt="
				+ comm_dt + ", commdel=" + commdel + ", postseq=" + postseq + "]";
	}
	
}
