package kr.or.ddit.attafile.model;

public class AttaFile {
	private int attaseq;
	private String attafilename;
	private String attarealfilename;
	private int postseq;
	
	public AttaFile() {
		
	}
	
	public AttaFile(String attafilename, String attarealfilename) {
		this.attafilename = attafilename;
		this.attarealfilename = attarealfilename;
	}
	
	public AttaFile(int attaseq, String attafilename, String attarealfilename, int postseq) {
		this.attaseq = attaseq;
		this.attafilename = attafilename;
		this.attarealfilename = attarealfilename;
		this.postseq = postseq;
	}
	
	public int getAttaseq() {
		return attaseq;
	}
	public void setAttaseq(int attaseq) {
		this.attaseq = attaseq;
	}
	public String getAttafilename() {
		return attafilename;
	}
	public void setAttafilename(String attafilename) {
		this.attafilename = attafilename;
	}
	public String getAttarealfilename() {
		return attarealfilename;
	}
	public void setAttarealfilename(String attarealfilename) {
		this.attarealfilename = attarealfilename;
	}
	public int getPostseq() {
		return postseq;
	}
	public void setPostseq(int postseq) {
		this.postseq = postseq;
	}
	@Override
	public String toString() {
		return "AttaFile [attaseq=" + attaseq + ", attafilename=" + attafilename + ", attarealfilename="
				+ attarealfilename + ", postseq=" + postseq + "]";
	}
}
