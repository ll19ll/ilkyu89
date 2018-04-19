package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MaterialVO;

public class EditDAO {

	// �������â ����
	public MaterialVO getMaterialUpdate(MaterialVO matvo, int m_no) throws Exception {
		// ������ó���� ���� SQL��
		StringBuffer sql = new StringBuffer();
		sql.append("update material set ");
		sql.append("material=?, mat_count=?, sellstore=?, mat_total=? ");
		sql.append("where m_no=?");
		Connection con = null;
		PreparedStatement pstmt = null;
		MaterialVO matVo = null;

		try {
			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// ������ �л� ������ �����ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, matvo.getMaterial());
			pstmt.setString(2, matvo.getMatCount());
			pstmt.setString(3, matvo.getMatStore());
			pstmt.setString(4, matvo.getMatTotal());
			pstmt.setDouble(5, m_no);

			// SQL���� ���� �� ó�� ����� ����
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����");
				alert.setHeaderText("���� �Ϸ�");
				alert.setContentText("���� ����");
				alert.showAndWait();
				matVo = new MaterialVO();
			}
		} catch (SQLException e) {
			System.out.println("��������SQL" + e);
		} catch (Exception e) {
			System.out.println("��������DAO" + e );
		} finally {
			try {
				// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					;
				pstmt.close();
				if (con != null)
					;
				con.close();
			} catch (Exception e) {
				System.out.println("��������������Ʈ����"+e);
			}
		}
		return matVo;
	}
	
	// �������Ż ����
		public MaterialVO getMaterialTotalUpdate(MaterialVO matvo, int m_no) throws Exception {
			// ������ó���� ���� SQL��
			StringBuffer sql = new StringBuffer();
			sql.append("update material set ");
			sql.append("mat_total=? ");
			sql.append("where m_no=?");
			Connection con = null;
			PreparedStatement pstmt = null;
			MaterialVO matVo = null;

			try {
				// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� �����ͺ��̽��� ����
				con = DBUtil.getConnection();

				// ������ �л� ������ �����ϱ� ���Ͽ� SQL������ ����
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, matvo.getMatTotal());
				pstmt.setDouble(2, m_no);

				// SQL���� ���� �� ó�� ����� ����
				int i = pstmt.executeUpdate();

				if (i == 1) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("���� ����");
					alert.setHeaderText("���� ���� �Ϸ�");
					alert.setContentText("���� ���� ����");
					alert.showAndWait();
					matVo = new MaterialVO();
				}
			} catch (SQLException e) {
				System.out.println("e = [" + e + "]");
			} catch (Exception e) {
				System.out.println("e = [" + e + "]");
			} finally {
				try {
					// ������ ���̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					if (pstmt != null)
						;
					pstmt.close();
					if (con != null)
						;
					con.close();
				} catch (Exception e) {
				}
			}
			return matVo;
		}
}

