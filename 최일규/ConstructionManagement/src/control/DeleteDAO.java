package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteDAO {
	// 메인 삭제
	public void getMainDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from constructionmanagement where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스 연결
			con = DBUtil.getConnection();

			// SQL문을 수행 후 처리 결과를 가져옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ문을 수행 후 처리결과를 가져옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("계약정보 삭제");
				alert.setHeaderText("계약정보 삭제 완료");
				alert.setContentText("계약정보 삭제 성공");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("계약정보 삭제");
				alert.setHeaderText("계약정보 삭제 실패");
				alert.setContentText("계약정보 삭제 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("계약삭제SQL" + e);
		} catch (Exception e) {
			System.out.println("계약삭제DAO" + e);
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("자재삭제 오브젝트 해제" + e);
			}
		}
	}

	// 메인 인력 삭제
	public void getMainWorkDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from workman where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스 연결
			con = DBUtil.getConnection();

			// SQL문을 수행 후 처리 결과를 가져옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ문을 수행 후 처리결과를 가져옴
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계약삭제DAO" + e);
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("자재삭제 오브젝트 해제" + e);
			}
		}
	}

	// 메인 자재 삭제
	public void getMainMatDelete(int no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from material where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스 연결
			con = DBUtil.getConnection();

			// SQL문을 수행 후 처리 결과를 가져옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ문을 수행 후 처리결과를 가져옴
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("계약삭제DAO" + e);
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("자재삭제 오브젝트 해제" + e);
			}
		}
	}

	// 자재 삭제
	public void getMaterialDelete(int m_no) throws Exception {
		// 데이터 처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from material where m_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스 연결
			con = DBUtil.getConnection();

			// SQL문을 수행 후 처리 결과를 가져옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, m_no);
			System.out.println("1.");
			System.out.println(m_no);

			// SLQ문을 수행 후 처리결과를 가져옴
			int i = pstmt.executeUpdate();
			System.out.println("2.");
			System.out.println(i);

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("자재정보 삭제");
				alert.setHeaderText("자재정보 삭제 완료");
				alert.setContentText("자재정보 삭제 성공");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("자재정보 삭제");
				alert.setHeaderText("자재정보 삭제 실패");
				alert.setContentText("자재정보 삭제 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("자재삭제SQL" + e);
		} catch (Exception e) {
			System.out.println("자재삭제DAO" + e);
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("자재삭제 오브젝트 해제" + e);
			}
		}
	}

	// 인력 삭제
	public void getWorkManDelete(int no) throws Exception {
		// 데이터 처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from workman where w_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스 연결
			con = DBUtil.getConnection();

			// SQL문을 수행 후 처리 결과를 가져옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			System.out.println(1);

			// SLQ문을 수행 후 처리결과를 가져옴
			int i = pstmt.executeUpdate();
			System.out.println(i);
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("인력정보 삭제");
				alert.setHeaderText("인력정보 삭제 완료");
				alert.setContentText("인력정보 삭제 성공");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("인력정보 삭제");
				alert.setHeaderText("인력정보 삭제 실패");
				alert.setContentText("인력정보 삭제 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("인력정보삭제SQL" + e);
		} catch (Exception e) {
			System.out.println("인력정보삭제DAO" + e);
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("인력정보삭제 오브젝트 해제" + e);
			}
		}
	}

	// 파일 삭제
	public void getFileDelete(int no) throws Exception {
		// 데이터 처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("update constructionmanagement set filename = null where no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스 연결
			con = DBUtil.getConnection();

			// SQL문을 수행 후 처리 결과를 가져옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			// SLQ문을 수행 후 처리결과를 가져옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("파일 삭제");
				alert.setHeaderText("파일 삭제 완료");
				alert.setContentText("파일 삭제 성공");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("파일 삭제");
				alert.setHeaderText("파일 삭제 실패");
				alert.setContentText("파일 삭제 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("파일삭제SQL" + e);
		} catch (Exception e) {
			System.out.println("파일삭제DAO" + e);
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("파일삭제 오브젝트 해제" + e);
			}
		}
	}
}
