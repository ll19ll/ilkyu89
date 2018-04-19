package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteDAO {
	// ���� ����
	public void getMainDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from constructionmanagement where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽� ����
			con = DBUtil.getConnection();

			// SQL���� ���� �� ó�� ����� ������
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ���� ���� �� ó������� ������
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������� ����");
				alert.setHeaderText("������� ���� �Ϸ�");
				alert.setContentText("������� ���� ����");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("������� ����");
				alert.setHeaderText("������� ���� ����");
				alert.setContentText("������� ���� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("������SQL" + e);
		} catch (Exception e) {
			System.out.println("������DAO" + e);
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("������� ������Ʈ ����" + e);
			}
		}
	}

	// ���� �η� ����
	public void getMainWorkDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from workman where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽� ����
			con = DBUtil.getConnection();

			// SQL���� ���� �� ó�� ����� ������
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ���� ���� �� ó������� ������
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("������DAO" + e);
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("������� ������Ʈ ����" + e);
			}
		}
	}

	// ���� ���� ����
	public void getMainMatDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from material where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽� ����
			con = DBUtil.getConnection();

			// SQL���� ���� �� ó�� ����� ������
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ���� ���� �� ó������� ������
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("������DAO" + e);
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("������� ������Ʈ ����" + e);
			}
		}
	}

	// ���� ����
	public void getMaterialDelete(int m_no) throws Exception {
		// ������ ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("delete from material where m_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽� ����
			con = DBUtil.getConnection();

			// SQL���� ���� �� ó�� ����� ������
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, m_no);
			System.out.println("1.");
			System.out.println(m_no);

			// SLQ���� ���� �� ó������� ������
			int i = pstmt.executeUpdate();
			System.out.println("2.");
			System.out.println(i);

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�������� ����");
				alert.setHeaderText("�������� ���� �Ϸ�");
				alert.setContentText("�������� ���� ����");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�������� ����");
				alert.setHeaderText("�������� ���� ����");
				alert.setContentText("�������� ���� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("�������SQL" + e);
		} catch (Exception e) {
			System.out.println("�������DAO" + e);
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("������� ������Ʈ ����" + e);
			}
		}
	}

	// �η� ����
	public void getWorkManDelete(int no) throws Exception {
		// ������ ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("delete from workman where w_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽� ����
			con = DBUtil.getConnection();

			// SQL���� ���� �� ó�� ����� ������
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			System.out.println(1);

			// SLQ���� ���� �� ó������� ������
			int i = pstmt.executeUpdate();
			System.out.println(i);
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�η����� ����");
				alert.setHeaderText("�η����� ���� �Ϸ�");
				alert.setContentText("�η����� ���� ����");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�η����� ����");
				alert.setHeaderText("�η����� ���� ����");
				alert.setContentText("�η����� ���� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("�η���������SQL" + e);
		} catch (Exception e) {
			System.out.println("�η���������DAO" + e);
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("�η��������� ������Ʈ ����" + e);
			}
		}
	}

	// ���� ����
	public void getFileDelete(int no) throws Exception {
		// ������ ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("update constructionmanagement set filename = null where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� ������ ���̽� ����
			con = DBUtil.getConnection();

			// SQL���� ���� �� ó�� ����� ������
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ���� ���� �� ó������� ������
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� �Ϸ�");
				alert.setContentText("���� ���� ����");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� ����");
				alert.setContentText("���� ���� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("���ϻ���SQL" + e);
		} catch (Exception e) {
			System.out.println("���ϻ���DAO" + e);
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("���ϻ��� ������Ʈ ����" + e);
			}
		}
	}
}
