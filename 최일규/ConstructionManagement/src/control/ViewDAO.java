package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BarChartVO;
import model.EditMnWVO;
import model.EditVO;
import model.ManagementVO;
import model.MaterialVO;
import model.WorkManVO;

public class ViewDAO {

	// 수정창 전체
	public ManagementVO getEditTotal(int no) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from constructionmanagement where no = ?");

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
				mVo = new ManagementVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDate(7) + "", rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
						rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
						rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25),
						rs.getString(26), rs.getString(27));

			}
		} catch (SQLException se) {
			System.out.println("수정전체SQL" + se);
		} catch (Exception e) {
			System.out.println("수정전체DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("수정전체 오브젝트해제" + e);
			}
		}
		return mVo;
	}

	// 수정인력,자재창 전체
	public EditMnWVO getEditMnWTotal(int no) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select * from MATERIAL,(select WORKMAN.W_NO,WORKMAN.WORKMAN,WORKMAN.WORK_POSITION,WORKMAN.WORK_PAY,WORKMAN.WORK_DAY,WORKMAN.WORK_TOTAL from WORKMAN) where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EditMnWVO mnwVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mnwVo = new EditMnWVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getString(13));

			}
		} catch (SQLException se) {
			System.out.println("수정 인력,자재 전체SQL" + se);
		} catch (Exception e) {
			System.out.println("수정 인력,자재 전체DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("수정 인력,자재 전체 오브젝트해제" + e);
			}
		}
		return mnwVo;
	}

	// 자재비 전체 리스트
	public ArrayList<MaterialVO> getMaterialTotal(int no) {
		ArrayList<MaterialVO> list = new ArrayList<MaterialVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select m_no, material, mat_count, mat_total, sellstore ");
		sql.append("from material where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MaterialVO matVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				matVo = new MaterialVO();
				matVo.setM_no(rs.getInt("m_no"));
				matVo.setMaterial(rs.getString("material"));
				matVo.setMatCount(rs.getString("mat_count"));
				matVo.setMatTotal(rs.getString("mat_total"));
				matVo.setMatStore(rs.getString("sellstore"));
				list.add(matVo);
			}
		} catch (SQLException se) {
			System.out.println("자재비전체리스트SQL" + se);
		} catch (Exception e) {
			System.out.println("자재비전체리스트DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("자재비전체리스트 오브젝트해제" + e);
			}
		}
		return list;
	}

	// 인건비 전체 리스트
	public ArrayList<WorkManVO> getWorkManTotal(int no) {
		ArrayList<WorkManVO> list = new ArrayList<WorkManVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select w_no, workman, work_position, work_day, work_total ");
		sql.append("from workman where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorkManVO workVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				workVo = new WorkManVO();
				workVo.setW_no(rs.getInt("w_no"));
				workVo.setWorkMan(rs.getString("workman"));
				workVo.setPosition(rs.getString("work_position"));
				workVo.setWorkDay(rs.getString("work_day"));
				workVo.setWorkPay(rs.getString("work_total"));
				list.add(workVo);
			}
		} catch (SQLException se) {
			System.out.println("인건비전체리스트SQL" + se);
		} catch (Exception e) {
			System.out.println("인건비전체리스트DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("인건비전체리스트 오브젝트해제" + e);
			}
		}
		return list;
	}

	// 인건비 총액
	public WorkManVO getWorkManPayTotal(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(work_total) from workman where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorkManVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mVo = new WorkManVO();
				mVo.setWorkTotalPay(rs.getString("sum(work_total)"));

			}

		} catch (SQLException se) {
			System.out.println("인건비SQL" + se);
		} catch (Exception e) {
			System.out.println("인건비DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("인건비 오브젝트해제" + se);
			}
		}
		return mVo;
	}

	// 수정 인건 총액
	public EditVO WorkPayTotal(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT sum(work_total) from WORKMAN where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EditVO eVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eVo = new EditVO();
				eVo.setWorkTotal(rs.getString("sum(work_total)"));

			}

		} catch (SQLException se) {
			System.out.println("수정 자재,인건비SQL" + se);
		} catch (Exception e) {
			System.out.println("수정 자재,인건비DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("수정 인건비 오브젝트해제" + se);
			}
		}
		return eVo;
	}
	
	// 수정 자재 총액
		public EditVO MatPayTotal(int no) throws Exception {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT sum(mat_total) from material where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			EditVO eVo = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					eVo = new EditVO();
					eVo.setMatTotal(rs.getString("sum(mat_total)"));

				}

			} catch (SQLException se) {
				System.out.println("수정 자재,인건비SQL" + se);
			} catch (Exception e) {
				System.out.println("수정 자재,인건비DAO" + e);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException se) {
					System.out.println("수정 인건비 오브젝트해제" + se);
				}
			}
			return eVo;
		}

	// 수정 파일 이름 가져오기
	public ManagementVO fileName(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select filename from CONSTRUCTIONMANAGEMENT where no = ?");

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
				mVo.setFileName(rs.getString("filename"));

			}

		} catch (SQLException se) {
			System.out.println("수정 자재,인건비SQL" + se);
		} catch (Exception e) {
			System.out.println("수정 자재,인건비DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("수정 인건비 오브젝트해제" + se);
			}
		}
		return mVo;
	}

	// 자재비 총액
	public MaterialVO getMaterialPayTotal(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(mat_total) from material where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MaterialVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mVo = new MaterialVO();
				mVo.setMatTotalPay(rs.getString("sum(mat_total)"));

			}

		} catch (SQLException se) {
			System.out.println("자재비SQL" + se);
		} catch (Exception e) {
			System.out.println("자재비DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("자재비 오브젝트해제" + se);
			}
		}
		return mVo;
	}

	// 메인 전체 리스트
	public ArrayList<ManagementVO> getManagementTotal() {
		ArrayList<ManagementVO> list = new ArrayList<ManagementVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select no, contractor, con_phone, con_residence, con_name, to_char(construction_day,'yyyy-mm-dd') as 날짜 ");
		sql.append("from constructionmanagement order by construction_day desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagementVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mVo = new ManagementVO();
				mVo.setNo(rs.getInt("no"));
				mVo.setContractor(rs.getString("contractor"));
				mVo.setConPhone(rs.getString("con_phone"));
				mVo.setConResidence(rs.getString("con_residence"));
				mVo.setConName(rs.getString("con_name"));
				mVo.setConstructionDay(rs.getString("날짜"));
				list.add(mVo);
			}
		} catch (SQLException se) {
			System.out.println("메인전체리스트SQL" + se);
		} catch (Exception e) {
			System.out.println("메인전체리스트DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("메인전체리스트 오브젝트해제" + e);
			}
		}
		return list;
	}

	// 데이터 베이스에서 테이블 컬럼의 갯수
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from constructionmanagement");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println("데이터베이스 테이블 컬럼갯수SQL" + se);
		} catch (Exception e) {
			System.out.println("데이버베이스 테이블 컬럼갯수DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("데이터베이스 테이블 컬럼갯수 오브젝트 해제" + e);
			}
		}
		return columnName;
	}

	// 월별 매출액
	public ArrayList<BarChartVO> getMonthlyPay() throws Exception {
		ArrayList<BarChartVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select to_char(construction_day,'yyyy-mm') as 월, sum(magin) as 매출  ");
		sql.append("from CONSTRUCTIONMANAGEMENT ");
		sql.append("group by to_char(construction_day,'yyyy-mm')");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BarChartVO bVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bVo = new BarChartVO();
				bVo.setMon(rs.getString("월"));
				bVo.setPay(rs.getString("매출"));
				list.add(bVo);

			}

		} catch (SQLException se) {
			System.out.println("매출액SQL" + se);
		} catch (Exception e) {
			System.out.println("매출액DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("매출액 오브젝트해제" + se);
			}
		}
		return list;
	}
}
