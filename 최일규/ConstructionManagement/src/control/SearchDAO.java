package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagementVO;
import model.MaterialVO;
import model.WorkManVO;

public class SearchDAO {

	// contractor를 입력받아 정보 조회
	public ManagementVO getManagementCheck_name(String contractor) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select  no, contractor, con_phone, con_residence, con_name, construction_day from constructionmanagement where contractor like ");
		sql.append("? order by no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagementVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + contractor + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mVo = new ManagementVO();
				mVo.setNo(rs.getInt("no"));
				mVo.setContractor(rs.getString("contractor"));
				mVo.setConPhone(rs.getString("con_phone"));
				mVo.setConResidence(rs.getString("con_residence"));
				mVo.setConName(rs.getString("con_name"));
				mVo.setConstructionDay(rs.getString("construction_day"));

			}
		} catch (SQLException se) {
			System.out.println("이름검색SQL" + se);
		} catch (Exception e) {
			System.out.println("이름검색DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("이름검색오브젝트해제" + se);
			}
		}
		return mVo;
	}

	// construction_day를 입력받아 정보 조회
	public ManagementVO getManagementCheck_day(String constructionDay) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select no, contractor, con_phone, con_residence, con_name, to_char(construction_day,'yyyy-mm-dd') as 날짜 from constructionmanagement where construction_day = ? order by no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagementVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, constructionDay);
			System.out.println(constructionDay);
			rs = pstmt.executeQuery();
			System.out.println(rs.next());
			if (rs.next()) {
				mVo = new ManagementVO();
				mVo.setNo(rs.getInt("no"));
				mVo.setContractor(rs.getString("contractor"));
				mVo.setConPhone(rs.getString("con_phone"));
				mVo.setConResidence(rs.getString("con_residence"));
				mVo.setConName(rs.getString("con_name"));
				mVo.setConstructionDay(rs.getString("날짜"));
			}
		} catch (SQLException se) {
			System.out.println("날짜검색SQL" + se);
		} catch (Exception e) {
			System.out.println("날짜검색DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("날짜검색오브젝트해제" + se);
			}
		}
		return mVo;
	}

	/*
	 * // construction_day와 contractor를 입력받아 정보조회 public ManagementVO
	 * getManagementCheck_all(String constructionDay, String contractor) throws
	 * Exception { StringBuffer sql = new StringBuffer(); sql.
	 * append("select * from constructionmanagement where construction_day >= ? and contractor like ?"
	 * ); sql.append("order by no desc");
	 * 
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * ManagementVO mVo = null; MaterialVO matVo = null; WorkManVO workVo = null;
	 * 
	 * try { con = DBUtil.getConnection(); pstmt =
	 * con.prepareStatement(sql.toString()); pstmt.setString(1, "%" +
	 * constructionDay + "%"); pstmt.setString(2, "%" + contractor + "%");
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { mVo = new ManagementVO(); matVo = new MaterialVO(); workVo =
	 * new WorkManVO(); mVo.setNo(rs.getInt("no"));
	 * mVo.setContractor(rs.getString("contractor"));
	 * mVo.setConPhone(rs.getString("con_phone"));
	 * mVo.setConBirth(rs.getString("con_birth"));
	 * mVo.setConResidence(rs.getString("con_residence"));
	 * mVo.setConName(rs.getString("con_name"));
	 * mVo.setConstructionDay(rs.getString("construction_day"));
	 * mVo.setMeterType1(rs.getString("meter_type1"));
	 * mVo.setMeterType2(rs.getString("meter_type2"));
	 * mVo.setMeterType3(rs.getString("meter_type3"));
	 * mVo.setMeterCount1(rs.getString("meter_count1"));
	 * mVo.setMeterCount2(rs.getString("meter_count2"));
	 * mVo.setMeterCount3(rs.getString("meter_count3"));
	 * mVo.setBuildingType(rs.getString("building_type"));
	 * mVo.setUse(rs.getString("use"));
	 * mVo.setConstructionPay(rs.getString("construction_pay"));
	 * mVo.setDirector(rs.getString("director"));
	 * mVo.setDirPhone(rs.getString("dir_phone"));
	 * mVo.setDirDepartment(rs.getString("dir_department"));
	 * mVo.setManager(rs.getString("manager"));
	 * mVo.setManPhone(rs.getString("man_phone"));
	 * mVo.setManDepartment(rs.getString("man_department"));
	 * mVo.setLeader(rs.getString("leader"));
	 * mVo.setLdPhone(rs.getString("ld_phone"));
	 * mVo.setLdDepartment(rs.getString("ld_department"));
	 * matVo.setMaterial(rs.getString("material"));
	 * matVo.setMatCount(rs.getString("mat_count"));
	 * matVo.setMatPay(rs.getString("mat_pay"));
	 * matVo.setMatStore(rs.getString("sellstore"));
	 * matVo.setMatTotal(rs.getString("mat_total"));
	 * workVo.setWorkMan(rs.getString("workman"));
	 * workVo.setPosition(rs.getString("work_position"));
	 * workVo.setWorkDay(rs.getString("work_day"));
	 * workVo.setWorkPay(rs.getString("work_pay"));
	 * workVo.setWorkTotal(rs.getString("work_total"));
	 * workVo.setWorkTotal(rs.getString("magin"));
	 * mVo.setFileName(rs.getString("fileName")); } } catch (SQLException se) {
	 * System.out.println("이름,날짜 검색SQL" + se); } catch (Exception e) {
	 * System.out.println("이름,날짜검색DAO" + e); } finally { try { if (rs != null)
	 * rs.close(); if (pstmt != null) pstmt.close(); if (con != null) con.close(); }
	 * catch (SQLException se) { System.out.println("이름,날짜 검색 오브젝트해제" + se); } }
	 * return mVo; }
	 */

	// 넘버
	public ArrayList<ManagementVO> getNumber() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select no from constructionmanagement order by no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagementVO mVo = null;
		ArrayList<ManagementVO> list = new ArrayList<>();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mVo = new ManagementVO();
				mVo.setNo(rs.getInt("no"));
				list.add(mVo);
			}

		} catch (SQLException se) {
			System.out.println("넘버SQL" + se);
		} catch (Exception e) {
			System.out.println("넘버DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("넘버 오브젝트해제" + se);
			}
		}
		return list;
	}

	// 공사금액
	public ManagementVO getConPay(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select construction_pay from constructionmanagement where no=?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagementVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mVo = new ManagementVO();
				mVo.setConstructionPay(rs.getString("construction_pay"));
			}

		} catch (SQLException se) {
			System.out.println("공사금액SQL" + se);
		} catch (Exception e) {
			System.out.println("공사금액DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("공사금액 오브젝트해제" + se);
			}
		}
		return mVo;
	}

	// 폴더
	public ManagementVO getFolder(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select contractor from constructionmanagement where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagementVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mVo = new ManagementVO();
				mVo.setContractor(rs.getString("contractor"));

			}

		} catch (SQLException se) {
			System.out.println("넘버SQL" + se);
		} catch (Exception e) {
			System.out.println("넘버DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("넘버 오브젝트해제" + se);
			}
		}
		return mVo;
	}

}
