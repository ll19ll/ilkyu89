package model;

public class EditVO {
	private int no;
	private String matTotal;
	private String workTotal;
	
	public EditVO() {
		super();
	}

	public EditVO(int no, String matTotal, String workTotal) {
		super();
		this.no = no;
		this.matTotal = matTotal;
		this.workTotal = workTotal;
	}

	public EditVO(String matTotal, String workTotal) {
		super();
		this.matTotal = matTotal;
		this.workTotal = workTotal;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMatTotal() {
		return matTotal;
	}

	public void setMatTotal(String matTotal) {
		this.matTotal = matTotal;
	}

	public String getWorkTotal() {
		return workTotal;
	}

	public void setWorkTotal(String workTotal) {
		this.workTotal = workTotal;
	}

	
	
}
