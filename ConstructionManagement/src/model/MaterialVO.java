package model;

public class MaterialVO {
	private int m_no;
	private String material;
	private String matCount;
	private String matPay;
	private String matStore;
	private String matTotal;
	private int no;

	private String sumMatTotal;
	private String matTotalPay;

	public MaterialVO() {
		super();
	}

	public MaterialVO(String material, String matCount, String matPay, String matStore, String matTotal, int no) {
		super();
		this.material = material;
		this.matCount = matCount;
		this.matPay = matPay;
		this.matStore = matStore;
		this.matTotal = matTotal;
		this.no = no;
	}



	public MaterialVO(String sumMatTotal) {
		super();
		this.sumMatTotal = sumMatTotal;
	}

	
	public String getSumMatTotal() {
		return sumMatTotal;
	}

	public void setSumMatTotal(String sumMatTotal) {
		this.sumMatTotal = sumMatTotal;
	}

	
	public MaterialVO(int m_no, String material, String matCount, String matPay, String matStore, String matTotal,
			int no) {
		super();
		this.m_no = m_no;
		this.material = material;
		this.matCount = matCount;
		this.matPay = matPay;
		this.matStore = matStore;
		this.matTotal = matTotal;
		this.no = no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getMatTotalPay() {
		return matTotalPay;
	}

	public void setMatTotalPay(String matTotalPay) {
		this.matTotalPay = matTotalPay;
	}

}
