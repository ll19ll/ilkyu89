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

	// ����â ��ü
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
			System.out.println("������üSQL" + se);
		} catch (Exception e) {
			System.out.println("������üDAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("������ü ������Ʈ����" + e);
			}
		}
		return mVo;
	}

	// �����η�,����â ��ü
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
			System.out.println("���� �η�,���� ��üSQL" + se);
		} catch (Exception e) {
			System.out.println("���� �η�,���� ��üDAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("���� �η�,���� ��ü ������Ʈ����" + e);
			}
		}
		return mnwVo;
	}

	// ����� ��ü ����Ʈ
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
			System.out.println("�������ü����ƮSQL" + se);
		} catch (Exception e) {
			System.out.println("�������ü����ƮDAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("�������ü����Ʈ ������Ʈ����" + e);
			}
		}
		return list;
	}

	// �ΰǺ� ��ü ����Ʈ
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
			System.out.println("�ΰǺ���ü����ƮSQL" + se);
		} catch (Exception e) {
			System.out.println("�ΰǺ���ü����ƮDAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("�ΰǺ���ü����Ʈ ������Ʈ����" + e);
			}
		}
		return list;
	}

	// �ΰǺ� �Ѿ�
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
			System.out.println("�ΰǺ�SQL" + se);
		} catch (Exception e) {
			System.out.println("�ΰǺ�DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("�ΰǺ� ������Ʈ����" + se);
			}
		}
		return mVo;
	}

	// ���� �ΰ� �Ѿ�
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
			System.out.println("���� ����,�ΰǺ�SQL" + se);
		} catch (Exception e) {
			System.out.println("���� ����,�ΰǺ�DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("���� �ΰǺ� ������Ʈ����" + se);
			}
		}
		return eVo;
	}
	
	// ���� ���� �Ѿ�
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
				System.out.println("���� ����,�ΰǺ�SQL" + se);
			} catch (Exception e) {
				System.out.println("���� ����,�ΰǺ�DAO" + e);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException se) {
					System.out.println("���� �ΰǺ� ������Ʈ����" + se);
				}
			}
			return eVo;
		}

	// ���� ���� �̸� ��������
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
			System.out.println("���� ����,�ΰǺ�SQL" + se);
		} catch (Exception e) {
			System.out.println("���� ����,�ΰǺ�DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("���� �ΰǺ� ������Ʈ����" + se);
			}
		}
		return mVo;
	}

	// ����� �Ѿ�
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
			System.out.println("�����SQL" + se);
		} catch (Exception e) {
			System.out.println("�����DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("����� ������Ʈ����" + se);
			}
		}
		return mVo;
	}

	// ���� ��ü ����Ʈ
	public ArrayList<ManagementVO> getManagementTotal() {
		ArrayList<ManagementVO> list = new ArrayList<ManagementVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select no, contractor, con_phone, con_residence, con_name, to_char(construction_day,'yyyy-mm-dd') as ��¥ ");
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
				mVo.setConstructionDay(rs.getString("��¥"));
				list.add(mVo);
			}
		} catch (SQLException se) {
			System.out.println("������ü����ƮSQL" + se);
		} catch (Exception e) {
			System.out.println("������ü����ƮDAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("������ü����Ʈ ������Ʈ����" + e);
			}
		}
		return list;
	}

	// ������ ���̽����� ���̺� �÷��� ����
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from constructionmanagement");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ResultSetMetaData ��ü ���� ����
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
			System.out.println("�����ͺ��̽� ���̺� �÷�����SQL" + se);
		} catch (Exception e) {
			System.out.println("���̹����̽� ���̺� �÷�����DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("�����ͺ��̽� ���̺� �÷����� ������Ʈ ����" + e);
			}
		}
		return columnName;
	}

	// ���� �����
	public ArrayList<BarChartVO> getMonthlyPay() throws Exception {
		ArrayList<BarChartVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select to_char(construction_day,'yyyy-mm') as ��, sum(magin) as ����  ");
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
				bVo.setMon(rs.getString("��"));
				bVo.setPay(rs.getString("����"));
				list.add(bVo);

			}

		} catch (SQLException se) {
			System.out.println("�����SQL" + se);
		} catch (Exception e) {
			System.out.println("�����DAO" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println("����� ������Ʈ����" + se);
			}
		}
		return list;
	}
}
