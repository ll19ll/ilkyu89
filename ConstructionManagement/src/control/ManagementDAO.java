package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ManagementVO;
import model.MaterialVO;
import model.WorkManVO;

public class ManagementDAO {

	// �ű� ����� ���� ���
	public ManagementVO getConstuctionRegiste(ManagementVO mvo) throws Exception {
		// ������ ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ConstructionManagement ");
		sql.append(" (no, contractor, con_Phone, con_birth, con_residence, con_name, construction_day, meter_type1, meter_type2, meter_type3, meter_count1, meter_count2, meter_count3, building_type, use, construction_pay, director, dir_phone, dir_department, manager, man_phone, man_department, leader, ld_phone, ld_department) ");
		sql.append(" values (ConstructionManagement_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ManagementVO mVo = mvo;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽��� ����
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// �Է¹��� ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mVo.getContractor());
			System.out.println("1");
			pstmt.setString(2, mVo.getConPhone());
			pstmt.setString(3, mVo.getConBirth());
			pstmt.setString(4, mVo.getConResidence());
			System.out.println("1");
			pstmt.setString(5, mVo.getConName());
			pstmt.setString(6, mVo.getConstructionDay().substring(0,10));
			pstmt.setString(7, mVo.getMeterType1());
			pstmt.setString(8, mVo.getMeterType2());
			System.out.println("1");
			pstmt.setString(9, mVo.getMeterType3());
			pstmt.setString(10, mVo.getMeterCount1());
			pstmt.setString(11, mVo.getMeterCount2());
			pstmt.setString(12, mVo.getMeterCount3());
			pstmt.setString(13, mVo.getBuildingType());
			System.out.println("1");
			pstmt.setString(14, mVo.getUse());
			pstmt.setString(15, mVo.getConstructionPay());
			pstmt.setString(16, mVo.getDirector());
			pstmt.setString(17, mVo.getDirPhone());
			System.out.println("1");
			pstmt.setString(18, mVo.getDirDepartment());
			pstmt.setString(19, mVo.getManager());
			pstmt.setString(20, mVo.getManPhone());
			pstmt.setString(21, mVo.getManDepartment());
			System.out.println("1");
			pstmt.setString(22, mVo.getLeader());
			pstmt.setString(23, mVo.getLdPhone());
			System.out.println("1");
			pstmt.setString(24, mVo.getLdDepartment());
			
			

			// SQL���� ���� �� ó������� ������
			int i = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("�ű԰�����������SQL" + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("�ű԰�����������DAO" + e);
			e.printStackTrace();
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("�ű԰����������Ͽ�����Ʈ����" + e);
			}
		}
		return mVo;
	}

	
	
	// �η��������
	public WorkManVO getWorkManRegiste(WorkManVO workvo, int no) throws Exception {
		// ������ ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("insert into WorkMan ");
		sql.append("values (workman_seq.nextval,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		WorkManVO workVo = workvo;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽��� ����
			con = DBUtil.getConnection();

			// �Է¹��� ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, workVo.getWorkMan());
			pstmt.setString(2, workVo.getPosition());
			pstmt.setString(3, workVo.getWorkPay());
			pstmt.setString(4, workVo.getWorkDay());
			pstmt.setString(5, workVo.getWorkTotal());
			pstmt.setInt(6, no);

			// SQL���� ���� �� ó������� ������
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("�η��������SQL" + e);
		} catch (Exception e) {
			System.out.println("�η��������DAO" + e);
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("�η�������Ͽ�����Ʈ����" + e);
			}
		}
		return workVo;
	}
	
	// �����������
	public MaterialVO getMaterialRegiste(MaterialVO matvo, int no) throws Exception {
		// ������ ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("insert into material ");
		sql.append("values (?,material_seq.nextval,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MaterialVO matVo = matvo;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽��� ����
			con = DBUtil.getConnection();

			// �Է¹��� ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.setString(2, matVo.getMaterial());
			pstmt.setString(3, matVo.getMatCount());
			pstmt.setString(4, matVo.getMatPay());
			pstmt.setString(5, matVo.getMatStore());
			pstmt.setString(6, matVo.getMatTotal());
			
			

			// SQL���� ���� �� ó������� ������
			int i = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("�����������SQL: " + e);
		} catch (Exception e) {
			System.out.println("�����������DAO: " + e);
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("����������Ͽ�����Ʈ����: " + e);
			}
		}
		return matVo;
	}
	
	// ����,�����̸� ������Ʈ
		public ManagementVO getMaginFileUpdate(int no, String magin, String fileName ) throws Exception {
			// ������ ó���� ���� SQL��
			StringBuffer sql = new StringBuffer();
			sql.append("update constructionmanagement ");
			sql.append("set magin = ?,filename = ? ");
			sql.append("where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ManagementVO mVo = null;

			try {
				// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽��� ����
				con = DBUtil.getConnection();

				// �Է¹��� ������ ó���ϱ� ���Ͽ� SQL������ ����
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, magin);
				pstmt.setString(2, fileName);
				pstmt.setInt(3, no);
				
				// SQL���� ���� �� ó������� ������
				int i = pstmt.executeUpdate();
				if(i == 1) {
					System.out.println("��������");
				}
			} catch (SQLException e) {
				System.out.println("�����������SQL: " + e);
			} catch (Exception e) {
				System.out.println("�����������DAO: " + e);
			} finally {
				try {
					// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println("����������Ͽ�����Ʈ����: " + e);
				}
			}
			return mVo;
		}
		
		
		//���� ������Ʈ
		public ManagementVO getMaginUpdate(int no, String magin) throws Exception {
			// ������ ó���� ���� SQL��
			StringBuffer sql = new StringBuffer();
			sql.append("update constructionmanagement ");
			sql.append("set magin = ? ");
			sql.append("where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ManagementVO mVo = null;

			try {
				// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽��� ����
				con = DBUtil.getConnection();

				// �Է¹��� ������ ó���ϱ� ���Ͽ� SQL������ ����
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, magin);
				pstmt.setInt(2, no);
				
				// SQL���� ���� �� ó������� ������
				int i = pstmt.executeUpdate();
				if(i == 1) {
					System.out.println("��������");
				}
			} catch (SQLException e) {
				System.out.println("������������SQL: " + e);
			} catch (Exception e) {
				System.out.println("������������DAO: " + e);
			} finally {
				try {
					// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println("������������������Ʈ����: " + e);
				}
			}
			return mVo;
		}
		
		//������� ���� ������Ʈ
		public ManagementVO getConstuctionUpdate(ManagementVO mvo) throws Exception {
			// ������ ó���� ���� SQL��
			StringBuffer sql = new StringBuffer();
			sql.append("update ConstructionManagement ");
			sql.append("set con_Phone = ?, con_birth = ?, con_residence = ?, con_name = ?, meter_type1 = ?, meter_type2 = ?, meter_type3 = ?, meter_count1 = ?, meter_count2 = ?, meter_count3 = ?, building_type = ?, use = ?, construction_pay = ?, director = ?, dir_phone = ?, dir_department = ?, manager = ?, man_phone = ?, man_department = ?, leader = ?, ld_phone = ?, ld_department = ? ");
			sql.append("where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ManagementVO mVo = mvo;

			try {
				// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽��� ����
				con = DBUtil.getConnection();

				// sVo = new StudentVO();

				// �Է¹��� ������ ó���ϱ� ���Ͽ� SQL������ ����
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, mVo.getConPhone());
				pstmt.setString(2, mVo.getConBirth());
				pstmt.setString(3, mVo.getConResidence());
				System.out.println("1");
				pstmt.setString(4, mVo.getConName());
				pstmt.setString(5, mVo.getMeterType1());
				pstmt.setString(6, mVo.getMeterType2());
				System.out.println("1");
				pstmt.setString(7, mVo.getMeterType3());
				pstmt.setString(8, mVo.getMeterCount1());
				pstmt.setString(9, mVo.getMeterCount2());
				pstmt.setString(10, mVo.getMeterCount3());
				pstmt.setString(11, mVo.getBuildingType());
				System.out.println("1");
				pstmt.setString(12, mVo.getUse());
				pstmt.setString(13, mVo.getConstructionPay());
				pstmt.setString(14, mVo.getDirector());
				pstmt.setString(15, mVo.getDirPhone());
				System.out.println("1");
				pstmt.setString(16, mVo.getDirDepartment());
				pstmt.setString(17, mVo.getManager());
				pstmt.setString(18, mVo.getManPhone());
				pstmt.setString(19, mVo.getManDepartment());
				System.out.println("1");
				pstmt.setString(20, mVo.getLeader());
				pstmt.setString(21, mVo.getLdPhone());
				System.out.println("1");
				pstmt.setString(22, mVo.getLdDepartment());
				pstmt.setInt(23, mVo.getNo());
				
				

				// SQL���� ���� �� ó������� ������
				int i = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("�����������������SQL" + e);
			} catch (Exception e) {
				System.out.println("�����������������DAO" + e);
			} finally {
				try {
					// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println("�����������������������Ʈ����" + e);
				}
			}
			return mVo;
		}
}

