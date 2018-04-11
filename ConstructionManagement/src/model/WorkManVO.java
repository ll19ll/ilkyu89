package model;

public class WorkManVO {
	private int w_no;
	private String workMan;
	private String position;
	private String workPay;
	private String workDay;
	private String workTotal;
	private int no;

	private String sumWorkTotal;
	private String workTotalPay;
	
	public WorkManVO() {
		super();
	}

	

	public WorkManVO(int w_no, String workMan, String position, String workPay, String workDay, String workTotal,
			int no) {
		super();
		this.w_no = w_no;
		this.workMan = workMan;
		this.position = position;
		this.workPay = workPay;
		this.workDay = workDay;
		this.workTotal = workTotal;
		this.no = no;
	}



	public WorkManVO(String sumWorkTotal) {
		super();
		this.sumWorkTotal = sumWorkTotal;
	}


	public String getSumWorkTotal() {
		return sumWorkTotal;
	}



	public void setSumWorkTotal(String sumWorkTotal) {
		this.sumWorkTotal = sumWorkTotal;
	}


	public WorkManVO(String workMan, String position, String workPay, String workDay, String workTotal, int no) {
		super();
		this.workMan = workMan;
		this.position = position;
		this.workPay = workPay;
		this.workDay = workDay;
		this.workTotal = workTotal;
		this.no = no;
	}



	public int getW_no() {
		return w_no;
	}

	public void setW_no(int w_no) {
		this.w_no = w_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWorkMan() {
		return workMan;
	}

	public void setWorkMan(String workMan) {
		this.workMan = workMan;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkDay() {
		return workDay;
	}

	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}

	public String getWorkPay() {
		return workPay;
	}

	public void setWorkPay(String workPay) {
		this.workPay = workPay;
	}

	public String getWorkTotal() {
		return workTotal;
	}

	public void setWorkTotal(String workTotal) {
		this.workTotal = workTotal;
	}

	public String getWorkTotalPay() {
		return workTotalPay;
	}

	public void setWorkTotalPay(String workTotalPay) {
		this.workTotalPay = workTotalPay;
	}

}
