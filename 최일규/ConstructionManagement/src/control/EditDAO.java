package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MaterialVO;

public class EditDAO {

	// 자재비등록창 수정
	public MaterialVO getMaterialUpdate(MaterialVO matvo, int m_no) throws Exception {
		// 데이터처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("update material set ");
		sql.append("material=?, mat_count=?, sellstore=?, mat_total=? ");
		sql.append("where m_no=?");
		Connection con = null;
		PreparedStatement pstmt = null;
		MaterialVO matVo = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 수정한 학생 정보를 수정하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, matvo.getMaterial());
			pstmt.setString(2, matvo.getMatCount());
			pstmt.setString(3, matvo.getMatStore());
			pstmt.setString(4, matvo.getMatTotal());
			pstmt.setDouble(5, m_no);

			// SQL문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("수정");
				alert.setHeaderText("수정 완료");
				alert.setContentText("수정 성공");
				alert.showAndWait();
				matVo = new MaterialVO();
			}
		} catch (SQLException e) {
			System.out.println("자재비수정SQL" + e);
		} catch (Exception e) {
			System.out.println("자재비수정DAO" + e );
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					;
				pstmt.close();
				if (con != null)
					;
				con.close();
			} catch (Exception e) {
				System.out.println("자재비수정오브젝트해제"+e);
			}
		}
		return matVo;
	}
	
	// 자재비토탈 수정
		public MaterialVO getMaterialTotalUpdate(MaterialVO matvo, int m_no) throws Exception {
			// 데이터처리를 위한 SQL문
			StringBuffer sql = new StringBuffer();
			sql.append("update material set ");
			sql.append("mat_total=? ");
			sql.append("where m_no=?");
			Connection con = null;
			PreparedStatement pstmt = null;
			MaterialVO matVo = null;

			try {
				// DBUtil이라는 클래스의 getConnection()메소드로 데이터베이스와 연결
				con = DBUtil.getConnection();

				// 수정한 학생 정보를 수정하기 위하여 SQL문장을 생성
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, matvo.getMatTotal());
				pstmt.setDouble(2, m_no);

				// SQL문을 수행 후 처리 결과를 얻어옴
				int i = pstmt.executeUpdate();

				if (i == 1) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("점수 수정");
					alert.setHeaderText("점수 수정 완료");
					alert.setContentText("점수 수정 성공");
					alert.showAndWait();
					matVo = new MaterialVO();
				}
			} catch (SQLException e) {
				System.out.println("e = [" + e + "]");
			} catch (Exception e) {
				System.out.println("e = [" + e + "]");
			} finally {
				try {
					// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
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

