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

	// 신규 계약자 정보 등록
	public ManagementVO getConstuctionRegiste(ManagementVO mvo) throws Exception {
		// 데이터 처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ConstructionManagement ");
		sql.append(" (no, contractor, con_Phone, con_birth, con_residence, con_name, construction_day, meter_type1, meter_type2, meter_type3, meter_count1, meter_count2, meter_count3, building_type, use, construction_pay, director, dir_phone, dir_department, manager, man_phone, man_department, leader, ld_phone, ld_department) ");
		sql.append(" values (ConstructionManagement_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ManagementVO mVo = mvo;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스와 연결
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// 입력받은 정보를 처리하기 위하여 SQL문장을 생성
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
			
			

			// SQL문을 수행 수 처리결과를 가져옴
			int i = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("신규계약자정보등록SQL" + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("신규계약자정보등록DAO" + e);
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("신규계약자정보등록오브젝트해제" + e);
			}
		}
		return mVo;
	}

	
	
	// 인력정보등록
	public WorkManVO getWorkManRegiste(WorkManVO workvo, int no) throws Exception {
		// 데이터 처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("insert into WorkMan ");
		sql.append("values (workman_seq.nextval,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		WorkManVO workVo = workvo;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스와 연결
			con = DBUtil.getConnection();

			// 입력받은 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, workVo.getWorkMan());
			pstmt.setString(2, workVo.getPosition());
			pstmt.setString(3, workVo.getWorkPay());
			pstmt.setString(4, workVo.getWorkDay());
			pstmt.setString(5, workVo.getWorkTotal());
			pstmt.setInt(6, no);

			// SQL문을 수행 수 처리결과를 가져옴
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("인력정보등록SQL" + e);
		} catch (Exception e) {
			System.out.println("인력정보등록DAO" + e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("인력정보등록오브젝트해제" + e);
			}
		}
		return workVo;
	}
	
	// 자재정보등록
	public MaterialVO getMaterialRegiste(MaterialVO matvo, int no) throws Exception {
		// 데이터 처리를 위한 SQL문
		StringBuffer sql = new StringBuffer();
		sql.append("insert into material ");
		sql.append("values (?,material_seq.nextval,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MaterialVO matVo = matvo;

		try {
			// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스와 연결
			con = DBUtil.getConnection();

			// 입력받은 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.setString(2, matVo.getMaterial());
			pstmt.setString(3, matVo.getMatCount());
			pstmt.setString(4, matVo.getMatPay());
			pstmt.setString(5, matVo.getMatStore());
			pstmt.setString(6, matVo.getMatTotal());
			
			

			// SQL문을 수행 수 처리결과를 가져옴
			int i = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("자재정보등록SQL: " + e);
		} catch (Exception e) {
			System.out.println("자재정보등록DAO: " + e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("자재정보등록오브젝트해제: " + e);
			}
		}
		return matVo;
	}
	
	// 마진,파일이름 업데이트
		public ManagementVO getMaginFileUpdate(int no, String magin, String fileName ) throws Exception {
			// 데이터 처리를 위한 SQL문
			StringBuffer sql = new StringBuffer();
			sql.append("update constructionmanagement ");
			sql.append("set magin = ?,filename = ? ");
			sql.append("where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ManagementVO mVo = null;

			try {
				// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스와 연결
				con = DBUtil.getConnection();

				// 입력받은 정보를 처리하기 위하여 SQL문장을 생성
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, magin);
				pstmt.setString(2, fileName);
				pstmt.setInt(3, no);
				
				// SQL문을 수행 수 처리결과를 가져옴
				int i = pstmt.executeUpdate();
				if(i == 1) {
					System.out.println("수정성공");
				}
			} catch (SQLException e) {
				System.out.println("자재정보등록SQL: " + e);
			} catch (Exception e) {
				System.out.println("자재정보등록DAO: " + e);
			} finally {
				try {
					// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println("자재정보등록오브젝트해제: " + e);
				}
			}
			return mVo;
		}
		
		
		//마진 업데이트
		public ManagementVO getMaginUpdate(int no, String magin) throws Exception {
			// 데이터 처리를 위한 SQL문
			StringBuffer sql = new StringBuffer();
			sql.append("update constructionmanagement ");
			sql.append("set magin = ? ");
			sql.append("where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ManagementVO mVo = null;

			try {
				// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스와 연결
				con = DBUtil.getConnection();

				// 입력받은 정보를 처리하기 위하여 SQL문장을 생성
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, magin);
				pstmt.setInt(2, no);
				
				// SQL문을 수행 수 처리결과를 가져옴
				int i = pstmt.executeUpdate();
				if(i == 1) {
					System.out.println("수정성공");
				}
			} catch (SQLException e) {
				System.out.println("마진정보업뎃SQL: " + e);
			} catch (Exception e) {
				System.out.println("마진정보업뎃DAO: " + e);
			} finally {
				try {
					// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println("마진정보업뎃오브젝트해제: " + e);
				}
			}
			return mVo;
		}
		
		//계약정보 수정 업데이트
		public ManagementVO getConstuctionUpdate(ManagementVO mvo) throws Exception {
			// 데이터 처리를 위한 SQL문
			StringBuffer sql = new StringBuffer();
			sql.append("update ConstructionManagement ");
			sql.append("set con_Phone = ?, con_birth = ?, con_residence = ?, con_name = ?, meter_type1 = ?, meter_type2 = ?, meter_type3 = ?, meter_count1 = ?, meter_count2 = ?, meter_count3 = ?, building_type = ?, use = ?, construction_pay = ?, director = ?, dir_phone = ?, dir_department = ?, manager = ?, man_phone = ?, man_department = ?, leader = ?, ld_phone = ?, ld_department = ? ");
			sql.append("where no = ?");

			Connection con = null;
			PreparedStatement pstmt = null;
			ManagementVO mVo = mvo;

			try {
				// DBUtil이라는 클래스의 getConnection()메소드로 데이터 베이스와 연결
				con = DBUtil.getConnection();

				// sVo = new StudentVO();

				// 입력받은 정보를 처리하기 위하여 SQL문장을 생성
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
				
				

				// SQL문을 수행 수 처리결과를 가져옴
				int i = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("수정계약자정보업뎃SQL" + e);
			} catch (Exception e) {
				System.out.println("수정계약자정보업뎃DAO" + e);
			} finally {
				try {
					// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println("수정계약자정보업뎃오브젝트해제" + e);
				}
			}
			return mVo;
		}
}

