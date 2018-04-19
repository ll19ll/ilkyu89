package model;

public class ManagementVO {

	private int no;
	private String contractor; // 계약자명
	private String conPhone; // 계약자 연락처
	private String conBirth; // 계약자 생년월일
	private String conResidence; // 계약자 주소
	private String conName; // 계약명
	private String constructionDay; // 공사날짜
	private String meterType1; // 계량기 등급
	private String meterType2;
	private String meterType3;
	private String meterCount1; // 계량기 갯수
	private String meterCount2;
	private String meterCount3;
	private String buildingType; // 건물형태
	private String use; // 용도
	private String constructionPay; // 공사금액
	private String director; // 공사감독 이름
	private String dirPhone; // 공사감독 연락처
	private String dirDepartment; // 공사감독 소속
	private String manager; // 시공관리자 이름
	private String manPhone; // 시공관리자 연락처
	private String manDepartment; // 시공관리자 소속
	private String leader; // 현장소장 이름
	private String ldPhone; // 현장소장 연락처
	private String ldDepartment; // 현장소장 소속
	private String magin; // 마진
	private String fileName = null;

	public ManagementVO() {
		super();

	}


	public ManagementVO(String fileName) {
		super();
		this.fileName = fileName;
	}


	
	public ManagementVO(int no, String conPhone, String conBirth, String conResidence, String conName,
			String meterType1, String meterType2, String meterType3, String meterCount1, String meterCount2,
			String meterCount3, String buildingType, String use, String constructionPay, String director,
			String dirPhone, String dirDepartment, String manager, String manPhone, String manDepartment, String leader,
			String ldPhone, String ldDepartment) {
		super();
		this.no = no;
		this.conPhone = conPhone;
		this.conBirth = conBirth;
		this.conResidence = conResidence;
		this.conName = conName;
		this.meterType1 = meterType1;
		this.meterType2 = meterType2;
		this.meterType3 = meterType3;
		this.meterCount1 = meterCount1;
		this.meterCount2 = meterCount2;
		this.meterCount3 = meterCount3;
		this.buildingType = buildingType;
		this.use = use;
		this.constructionPay = constructionPay;
		this.director = director;
		this.dirPhone = dirPhone;
		this.dirDepartment = dirDepartment;
		this.manager = manager;
		this.manPhone = manPhone;
		this.manDepartment = manDepartment;
		this.leader = leader;
		this.ldPhone = ldPhone;
		this.ldDepartment = ldDepartment;
	}


	public ManagementVO(int no, String contractor, String conPhone, String conBirth, String conResidence,
			String conName, String constructionDay, String meterType1, String meterType2, String meterType3,
			String meterCount1, String meterCount2, String meterCount3, String buildingType, String use,
			String constructionPay, String director, String dirPhone, String dirDepartment, String manager,
			String manPhone, String manDepartment, String leader, String ldPhone, String ldDepartment, String magin,
			String fileName) {
		super();
		this.no = no;
		this.contractor = contractor;
		this.conPhone = conPhone;
		this.conBirth = conBirth;
		this.conResidence = conResidence;
		this.conName = conName;
		this.constructionDay = constructionDay;
		this.meterType1 = meterType1;
		this.meterType2 = meterType2;
		this.meterType3 = meterType3;
		this.meterCount1 = meterCount1;
		this.meterCount2 = meterCount2;
		this.meterCount3 = meterCount3;
		this.buildingType = buildingType;
		this.use = use;
		this.constructionPay = constructionPay;
		this.director = director;
		this.dirPhone = dirPhone;
		this.dirDepartment = dirDepartment;
		this.manager = manager;
		this.manPhone = manPhone;
		this.manDepartment = manDepartment;
		this.leader = leader;
		this.ldPhone = ldPhone;
		this.ldDepartment = ldDepartment;
		this.magin = magin;
		this.fileName = fileName;
	}

	public ManagementVO(String contractor, String conPhone, String conBirth, String conResidence, String conName,
			String constructionDay, String meterType1, String meterType2, String meterType3, String meterCount1,
			String meterCount2, String meterCount3, String buildingType, String use, String constructionPay,
			String director, String dirPhone, String dirDepartment, String manager, String manPhone,
			String manDepartment, String leader, String ldPhone, String ldDepartment, String magin, String fileName) {
		super();
		this.contractor = contractor;
		this.conPhone = conPhone;
		this.conBirth = conBirth;
		this.conResidence = conResidence;
		this.conName = conName;
		this.constructionDay = constructionDay;
		this.meterType1 = meterType1;
		this.meterType2 = meterType2;
		this.meterType3 = meterType3;
		this.meterCount1 = meterCount1;
		this.meterCount2 = meterCount2;
		this.meterCount3 = meterCount3;
		this.buildingType = buildingType;
		this.use = use;
		this.constructionPay = constructionPay;
		this.director = director;
		this.dirPhone = dirPhone;
		this.dirDepartment = dirDepartment;
		this.manager = manager;
		this.manPhone = manPhone;
		this.manDepartment = manDepartment;
		this.leader = leader;
		this.ldPhone = ldPhone;
		this.ldDepartment = ldDepartment;
		this.magin = magin;
		this.fileName = fileName;
	}

	
	public ManagementVO(int no, String magin, String fileName) {
		super();
		this.no = no;
		this.magin = magin;
		this.fileName = fileName;
	}


	public ManagementVO(String contractor, String conPhone, String conBirth, String conResidence, String conName,
			String constructionDay, String meterType1, String meterType2, String meterType3, String meterCount1,
			String meterCount2, String meterCount3, String buildingType, String use, String constructionPay,
			String director, String dirPhone, String dirDepartment, String manager, String manPhone,
			String manDepartment, String leader, String ldPhone, String ldDepartment) {
		super();
		this.contractor = contractor;
		this.conPhone = conPhone;
		this.conBirth = conBirth;
		this.conResidence = conResidence;
		this.conName = conName;
		this.constructionDay = constructionDay;
		this.meterType1 = meterType1;
		this.meterType2 = meterType2;
		this.meterType3 = meterType3;
		this.meterCount1 = meterCount1;
		this.meterCount2 = meterCount2;
		this.meterCount3 = meterCount3;
		this.buildingType = buildingType;
		this.use = use;
		this.constructionPay = constructionPay;
		this.director = director;
		this.dirPhone = dirPhone;
		this.dirDepartment = dirDepartment;
		this.manager = manager;
		this.manPhone = manPhone;
		this.manDepartment = manDepartment;
		this.leader = leader;
		this.ldPhone = ldPhone;
		this.ldDepartment = ldDepartment;
	}


	public ManagementVO(String contractor, String conPhone, String conBirth, String conResidence, String conName,
			String constructionDay, String meterType1, String meterType2, String meterType3, String meterCount1,
			String meterCount2, String meterCount3, String buildingType, String use, String constructionPay,
			String director, String dirPhone, String dirDepartment, String manager, String manPhone,
			String manDepartment, String leader, String ldPhone, String ldDepartment, String fileName) {
		super();
		this.contractor = contractor;
		this.conPhone = conPhone;
		this.conBirth = conBirth;
		this.conResidence = conResidence;
		this.conName = conName;
		this.constructionDay = constructionDay;
		this.meterType1 = meterType1;
		this.meterType2 = meterType2;
		this.meterType3 = meterType3;
		this.meterCount1 = meterCount1;
		this.meterCount2 = meterCount2;
		this.meterCount3 = meterCount3;
		this.buildingType = buildingType;
		this.use = use;
		this.constructionPay = constructionPay;
		this.director = director;
		this.dirPhone = dirPhone;
		this.dirDepartment = dirDepartment;
		this.manager = manager;
		this.manPhone = manPhone;
		this.manDepartment = manDepartment;
		this.leader = leader;
		this.ldPhone = ldPhone;
		this.ldDepartment = ldDepartment;
		this.fileName = fileName;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getContractor() {
		return contractor;
	}


	public void setContractor(String contractor) {
		this.contractor = contractor;
	}


	public String getConPhone() {
		return conPhone;
	}


	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}


	public String getConBirth() {
		return conBirth;
	}


	public void setConBirth(String conBirth) {
		this.conBirth = conBirth;
	}


	public String getConResidence() {
		return conResidence;
	}


	public void setConResidence(String conResidence) {
		this.conResidence = conResidence;
	}


	public String getConName() {
		return conName;
	}


	public void setConName(String conName) {
		this.conName = conName;
	}


	public String getConstructionDay() {
		return constructionDay;
	}


	public void setConstructionDay(String constructionDay) {
		this.constructionDay = constructionDay;
	}


	public String getMeterType1() {
		return meterType1;
	}


	public void setMeterType1(String meterType1) {
		this.meterType1 = meterType1;
	}


	public String getMeterType2() {
		return meterType2;
	}


	public void setMeterType2(String meterType2) {
		this.meterType2 = meterType2;
	}


	public String getMeterType3() {
		return meterType3;
	}


	public void setMeterType3(String meterType3) {
		this.meterType3 = meterType3;
	}


	public String getMeterCount1() {
		return meterCount1;
	}


	public void setMeterCount1(String meterCount1) {
		this.meterCount1 = meterCount1;
	}


	public String getMeterCount2() {
		return meterCount2;
	}


	public void setMeterCount2(String meterCount2) {
		this.meterCount2 = meterCount2;
	}


	public String getMeterCount3() {
		return meterCount3;
	}


	public void setMeterCount3(String meterCount3) {
		this.meterCount3 = meterCount3;
	}


	public String getBuildingType() {
		return buildingType;
	}


	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}


	public String getUse() {
		return use;
	}


	public void setUse(String use) {
		this.use = use;
	}


	public String getConstructionPay() {
		return constructionPay;
	}


	public void setConstructionPay(String constructionPay) {
		this.constructionPay = constructionPay;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getDirPhone() {
		return dirPhone;
	}


	public void setDirPhone(String dirPhone) {
		this.dirPhone = dirPhone;
	}


	public String getDirDepartment() {
		return dirDepartment;
	}


	public void setDirDepartment(String dirDepartment) {
		this.dirDepartment = dirDepartment;
	}


	public String getManager() {
		return manager;
	}


	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getManPhone() {
		return manPhone;
	}


	public void setManPhone(String manPhone) {
		this.manPhone = manPhone;
	}


	public String getManDepartment() {
		return manDepartment;
	}


	public void setManDepartment(String manDepartment) {
		this.manDepartment = manDepartment;
	}


	public String getLeader() {
		return leader;
	}


	public void setLeader(String leader) {
		this.leader = leader;
	}


	public String getLdPhone() {
		return ldPhone;
	}


	public void setLdPhone(String ldPhone) {
		this.ldPhone = ldPhone;
	}


	public String getLdDepartment() {
		return ldDepartment;
	}


	public void setLdDepartment(String ldDepartment) {
		this.ldDepartment = ldDepartment;
	}


	public String getMagin() {
		return magin;
	}


	public void setMagin(String magin) {
		this.magin = magin;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}