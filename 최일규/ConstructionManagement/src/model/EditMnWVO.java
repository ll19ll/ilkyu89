package model;

public class EditMnWVO {
	private int no;
	private int m_no;
	private String material;
	private String matCount;
	private String matPay;
	private String matStore;
	private String matTotal;
	private int w_no;
	private String workMan;
	private String position;
	private String workPay;
	private String workDay;
	private String workTotal;
	
	public EditMnWVO() {
		super();
	}

	public EditMnWVO(int no, int m_no, String material, String matCount, String matPay, String matStore,
			String matTotal, int w_no, String workMan, String position, String workPay, String workDay,
			String workTotal) {
		super();
		this.no = no;
		this.m_no = m_no;
		this.material = material;
		this.matCount = matCount;
		this.matPay = matPay;
		this.matStore = matStore;
		this.matTotal = matTotal;
		this.w_no = w_no;
		this.workMan = workMan;
		this.position = position;
		this.workPay = workPay;
		this.workDay = workDay;
		this.workTotal = workTotal;
	}

	public EditMnWVO(int m_no, String material, String matCount, String matPay, String matStore, String matTotal,
			int w_no, String workMan, String position, String workPay, String workDay, String workTotal) {
		super();
		this.m_no = m_no;
		this.material = material;
		this.matCount = matCount;
		this.matPay = matPay;
		this.matStore = matStore;
		this.matTotal = matTotal;
		this.w_no = w_no;
		this.workMan = workMan;
		this.position = position;
		this.workPay = workPay;
		this.workDay = workDay;
		this.workTotal = workTotal;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMatCount() {
		return matCount;
	}

	public void setMatCount(String matCount) {
		this.matCount = matCount;
	}

	public String getMatPay() {
		return matPay;
	}

	public void setMatPay(String matPay) {
		this.matPay = matPay;
	}

	public String getMatStore() {
		return matStore;
	}

	public void setMatStore(String matStore) {
		this.matStore = matStore;
	}

	public String getMatTotal() {
		return matTotal;
	}

	public void setMatTotal(String matTotal) {
		this.matTotal = matTotal;
	}

	public int getW_no() {
		return w_no;
	}

	public void setW_no(int w_no) {
		this.w_no = w_no;
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

	public String getWorkPay() {
		return workPay;
	}

	public void setWorkPay(String workPay) {
		this.workPay = workPay;
	}

	public String getWorkDay() {
		return workDay;
	}

	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}

	public String getWorkTotal() {
		return workTotal;
	}

	public void setWorkTotal(String workTotal) {
		this.workTotal = workTotal;
	}
	
	
	
}
