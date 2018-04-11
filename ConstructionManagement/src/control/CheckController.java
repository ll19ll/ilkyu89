package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Connect;
import model.EditMnWVO;
import model.ManagementVO;

public class CheckController extends Connect implements Initializable {
	@FXML
	private Text txtNo_e;

	private String folder = null;

	// 계약자 정보
	@FXML
	private TextField txtContractor_e; // 계약자 이름
	@FXML
	private TextField txtConPhone_e; // 계약자 연락처
	@FXML
	private TextField txtConBirth_e; // 계약자 생년월일
	@FXML
	private TextField txtConResidence_e; // 계약자 주소
	@FXML
	private TextField txtConName_e; // 계약명
	@FXML
	private ComboBox<String> cbMeter1_e; // 계량기 등급 콤보박스
	@FXML
	private ComboBox<String> cbMeter2_e;
	@FXML
	private ComboBox<String> cbMeter3_e;
	@FXML
	private TextField txtMeter1_e; // 계량기 갯수
	@FXML
	private TextField txtMeter2_e;
	@FXML
	private TextField txtMeter3_e;
	@FXML
	private ComboBox<String> cbConType_e; // 건물형태
	@FXML
	private ComboBox<String> cbConUse_e; // 용도
	@FXML
	private TextField txtConDay_e; // 공사 날짜
	@FXML
	private TextField txtConPay_e; // 공사 금액

	// 인력 정보
	@FXML
	private TextField txtDerector_e; // 공사감독 이름
	@FXML
	private TextField txtDirPhone_e; // 공사감독 연락처
	@FXML
	private TextField txtDirDepartment_e; // 공사감독 소속
	@FXML
	private TextField txtManager_e; // 시공관리자 이름
	@FXML
	private TextField txtManPhone_e; // 시공관리자 연락처
	@FXML
	private TextField txtManDepartment_e; // 시공관리자 소속
	@FXML
	private TextField txtLeader_e; // 현장소장 이름
	@FXML
	private TextField txtLdPhone_e; // 현장소장 연락처
	@FXML
	private TextField txtLdDepartment_e; // 현장소장 소속

	// 비용정보
	// 메인
	@FXML
	private TextField txtMatPay_e; // 자재비
	@FXML
	private Button btnPay_e; // 자재비,인건비 버튼
	@FXML
	private TextField txtWmPay_e; // 인건비
	@FXML
	private TextField txtTotal_e; // 총비용
	@FXML
	private TextField txtMagin_e; // 마진

	// 첨부 파일
	@FXML
	private TextField txtDocument_e; // 첨부파일 목록
	@FXML
	private Button btnApp_e; // 파일 첨부
	@FXML
	private Button btnOpenDocument_e; // 폴더 열기

	@FXML
	private Button btnInfoAdd_e; // 정보등록
	@FXML
	private Button btnInfoExit_e; // 정보 창 닫기
	@FXML
	private Button btnInfoModified; // 수정

	int no; // 삭제시

	// 파일을 저장 할 폴더를 매개변수로 파일 객체 생성
	private File dirSave;
	// 파일을 저장 할 파일 객체 선언
	File selectedFile = null;

	ObservableList<ManagementVO> mainData = FXCollections.observableArrayList();
	ObservableList<ManagementVO> selectMain = null; // 테이블에서 선택한 정보 저장

	private Stage primaryStage;
	String fileSaveName = "";
	String selectFileName = ""; // 이미지 파일명
	String localUrl = ""; // 이미지 파일 경로

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		no();
		SearchDAO sDao = new SearchDAO();
		try {
			folder = sDao.getFolder(Connect.management.getNo()).getContractor();
		} catch (Exception e) {
		}
		dirSave = new File("E:\\javaFX\\ConstructionManagement\\file\\" + folder);
		if (!dirSave.exists()) {
			dirSave.mkdirs();
			System.out.println("파일생성성공");
		}
		btnInfoAdd_e.setDisable(true);
		btnPay_e.setDisable(true);
		btnApp_e.setDisable(true);
		txtContractor_e.setDisable(true);
		txtConPhone_e.setDisable(true);
		txtConBirth_e.setDisable(true);
		txtConResidence_e.setDisable(true);
		txtConName_e.setDisable(true);
		cbMeter1_e.setDisable(true);
		cbMeter2_e.setDisable(true);
		cbMeter3_e.setDisable(true);
		txtMeter1_e.setDisable(true);
		txtMeter2_e.setDisable(true);
		txtMeter3_e.setDisable(true);
		cbConType_e.setDisable(true);
		cbConUse_e.setDisable(true);
		txtConDay_e.setDisable(true);
		txtConPay_e.setDisable(true);

		// 인력 정보
		txtDerector_e.setDisable(true);
		txtDirPhone_e.setDisable(true);
		txtDirDepartment_e.setDisable(true);
		txtManager_e.setDisable(true);
		txtManPhone_e.setDisable(true);
		txtManDepartment_e.setDisable(true);
		txtLeader_e.setDisable(true);
		txtLdPhone_e.setDisable(true);
		txtLdDepartment_e.setDisable(true);
		// 비용정보
		txtMatPay_e.setDisable(true);
		txtWmPay_e.setDisable(true);
		txtTotal_e.setDisable(true);
		txtMagin_e.setDisable(true);
		txtDocument_e.setEditable(false);

		// 콤보값 설정
		cbMeter1_e.setItems(FXCollections.observableArrayList("G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter2_e.setItems(FXCollections.observableArrayList("G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter3_e.setItems(FXCollections.observableArrayList("G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbConType_e.setItems(FXCollections.observableArrayList("주택", "아파트", "상가", "공공시설"));
		cbConUse_e.setItems(FXCollections.observableArrayList("주택용(취사)", "일반용(영업)", "산업용"));

		infoSet();
		// 수정버튼
		btnInfoModified.setOnAction(event -> handleEditAction(event));
		// 등록 버튼
		btnInfoAdd_e.setOnAction(event -> handleInfoAddAction(event));
		// 첨부 버튼
		btnApp_e.setOnAction(event -> handleAppAction(event));

		// 확인 버튼
		btnInfoExit_e.setOnAction(event -> {
			Stage oldStage = (Stage) btnInfoExit_e.getScene().getWindow();
			oldStage.close();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainList.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scene = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("남산 ENG");
				mainMtage.setScene(scene);
				mainMtage.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
		});

		// 자재,인건비 버튼
		btnPay_e.setOnAction(event -> {
			try {
				ViewDAO vDao = new ViewDAO();
				EditMnWVO mnwVo = vDao.getEditMnWTotal(Connect.management.getNo());
				Connect.mnw = mnwVo;

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditPay.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scene = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("정보 수정");
				mainMtage.setScene(scene);
				mainMtage.show();

				Stage oldStage = (Stage) btnInfoExit_e.getScene().getWindow();
				oldStage.close();

			} catch (Exception e) {
				System.out.println("자재,인건비버튼" + e);
			}
		});
		// 폴더 열기 버튼
		btnOpenDocument_e.setOnAction(event -> {
			try {
				folder = sDao.getFolder(Connect.management.getNo()).getContractor();
				Runtime open = Runtime.getRuntime();
				open.exec("explorer E:\\javaFX\\ConstructionManagement\\file\\" + folder);
			} catch (Exception e) {
				System.out.println("파일열기: " + e);
			}
		});

	}

	// 등록 버튼 이벤트
	private void handleInfoAddAction(ActionEvent event) {
		try {
			mainData.removeAll(mainData);
			ManagementVO mVo = null;
			ManagementDAO mDao = new ManagementDAO();
			if (event.getSource().equals(btnInfoAdd_e)) {
				mVo = new ManagementVO(Connect.management.getNo(), txtConPhone_e.getText(), txtConBirth_e.getText(),
						txtConResidence_e.getText(), txtConName_e.getText(),
						cbMeter1_e.getSelectionModel().getSelectedItem(),
						cbMeter2_e.getSelectionModel().getSelectedItem(),
						cbMeter3_e.getSelectionModel().getSelectedItem(), txtMeter1_e.getText(), txtMeter2_e.getText(),
						txtMeter3_e.getText(), cbConType_e.getSelectionModel().getSelectedItem(),
						cbConUse_e.getSelectionModel().getSelectedItem(), txtConPay_e.getText(),
						txtDerector_e.getText(), txtDirPhone_e.getText(), txtDirDepartment_e.getText(),
						txtManager_e.getText(), txtManPhone_e.getText(), txtManDepartment_e.getText(),
						txtLeader_e.getText(), txtLdPhone_e.getText(), txtLdDepartment_e.getText());
				mDao = new ManagementDAO();
				mDao.getConstuctionUpdate(mVo);
				
				btnInfoExit_e.setDisable(false);
				btnInfoAdd_e.setDisable(true);
				btnPay_e.setDisable(true);
				btnApp_e.setDisable(true);
				txtContractor_e.setDisable(true);
				txtConPhone_e.setDisable(true);
				txtConBirth_e.setDisable(true);
				txtConResidence_e.setDisable(true);
				txtConName_e.setDisable(true);
				cbMeter1_e.setDisable(true);
				cbMeter2_e.setDisable(true);
				cbMeter3_e.setDisable(true);
				txtMeter1_e.setDisable(true);
				txtMeter2_e.setDisable(true);
				txtMeter3_e.setDisable(true);
				cbConType_e.setDisable(true);
				cbConUse_e.setDisable(true);
				txtConDay_e.setDisable(true);
				txtConPay_e.setDisable(true);

				// 인력 정보
				txtDerector_e.setDisable(true);
				txtDirPhone_e.setDisable(true);
				txtDirDepartment_e.setDisable(true);
				txtManager_e.setDisable(true);
				txtManPhone_e.setDisable(true);
				txtManDepartment_e.setDisable(true);
				txtLeader_e.setDisable(true);
				txtLdPhone_e.setDisable(true);
				txtLdDepartment_e.setDisable(true);
				// 비용정보
				txtMatPay_e.setDisable(true);
				txtWmPay_e.setDisable(true);
				txtTotal_e.setDisable(true);
				txtMagin_e.setDisable(true);
				txtDocument_e.setEditable(false);

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("정보 입력");
					alert.setHeaderText(txtContractor_e.getText() + "의 공사정보가 수정되었습니다.");
					alert.setContentText("다음 공사 정보를 입력하세요");
					alert.showAndWait();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 입력");
			alert.setHeaderText(txtContractor_e.getText() + "의 공사정보가 수정되지않았습니다.");
			alert.setContentText("다시 입력하세요");
			alert.showAndWait();
		}
	};

	// 첨부 버튼 이벤트
	private void handleAppAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("file", "*"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);

			if (selectedFile != null) {
				// 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();
				fileSave(selectedFile);
				try {

				} catch (Exception e) {
					System.out.println("파일저장" + e);
				}
			}
		} catch (MalformedURLException e) {
			System.out.println("첨부버튼" + e);

			if (selectedFile != null) {
				selectFileName = selectedFile.getName();
			}
		}
	}

	// 파일저장 메소드
	public String fileSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		int data = -1;
		String fileName = null;

		try {
			// 파일명 생성
			fileName = "남산ENG_" + System.currentTimeMillis() + "_" + file.getName();
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getCanonicalPath() + "\\" + fileName));

			// 선택한 이미지 파일 InputStream의 마지막에 이르렀을 경우는 -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			System.out.println("파일명생성" + e);
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				System.out.println("이미지파일명생성2" + e);
			}
		}
		return fileName;
	}

	// 수정 버튼 이벤트
	private void handleEditAction(ActionEvent event) {
		btnInfoAdd_e.setDisable(false);
		btnPay_e.setDisable(false);
		btnApp_e.setDisable(false);
		btnInfoExit_e.setDisable(true);
		txtConPhone_e.setEditable(true);
		txtConPhone_e.setDisable(false);
		txtConBirth_e.setDisable(false);
		txtConResidence_e.setDisable(false);
		txtConName_e.setDisable(false);
		cbMeter1_e.setDisable(false);
		cbMeter2_e.setDisable(false);
		cbMeter3_e.setDisable(false);
		txtMeter1_e.setDisable(false);
		txtMeter2_e.setDisable(false);
		txtMeter3_e.setDisable(false);
		cbConType_e.setDisable(false);
		cbConUse_e.setDisable(false);
		txtConPay_e.setDisable(false);
		txtDerector_e.setDisable(false);
		txtDirPhone_e.setDisable(false);
		txtDirDepartment_e.setDisable(false);
		txtManager_e.setDisable(false);
		txtManPhone_e.setDisable(false);
		txtManDepartment_e.setDisable(false);
		txtLeader_e.setDisable(false);
		txtLdPhone_e.setDisable(false);
		txtLdDepartment_e.setDisable(false);
		
	}

	public void infoSet() {
		txtNo_e.setText(Connect.management.getNo() + "");
		txtContractor_e.setText(Connect.management.getContractor()); // 계약자 이름
		txtConPhone_e.setText(Connect.management.getConPhone()); // 계약자 연락처
		txtConBirth_e.setText(Connect.management.getConBirth()); // 계약자 생년월일
		txtConResidence_e.setText(Connect.management.getConResidence()); // 계약자 주소
		txtConName_e.setText(Connect.management.getConName()); // 계약명
		cbMeter1_e.setValue(Connect.management.getMeterType1()); // 계량기 등급 콤보박스
		cbMeter2_e.setValue(Connect.management.getMeterType2());
		cbMeter3_e.setValue(Connect.management.getMeterType3());
		txtMeter1_e.setText(Connect.management.getMeterCount1()); // 계량기 갯수
		txtMeter2_e.setText(Connect.management.getMeterCount2());
		txtMeter3_e.setText(Connect.management.getMeterCount3());
		cbConType_e.setValue(Connect.management.getBuildingType()); // 건물형태
		cbConUse_e.setValue(Connect.management.getUse()); // 용도
		txtConDay_e.setText(Connect.management.getConstructionDay()); // 공사 날짜
		txtConPay_e.setText(Connect.management.getConstructionPay()); // 공사 금액

		// 인력 정보
		txtDerector_e.setText(Connect.management.getDirector()); // 공사감독 이름
		txtDirPhone_e.setText(Connect.management.getDirPhone()); // 공사감독 연락처
		txtDirDepartment_e.setText(Connect.management.getDirDepartment()); // 공사감독 소속
		txtManager_e.setText(Connect.management.getManager()); // 시공관리자 이름
		txtManPhone_e.setText(Connect.management.getManPhone()); // 시공관리자 연락처
		txtManDepartment_e.setText(Connect.management.getManDepartment()); // 시공관리자 소속
		txtLeader_e.setText(Connect.management.getLeader()); // 현장소장 이름
		txtLdPhone_e.setText(Connect.management.getLdPhone()); // 현장소장 연락처
		txtLdDepartment_e.setText(Connect.management.getLdDepartment()); // 현장소장 소속

		// 비용정보
		try {
			ViewDAO vmDao = new ViewDAO();
			String matTotal = vmDao.MatPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotal();
			String workTotal = vmDao.WorkPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotal();
			txtMatPay_e.setText("0");
			txtWmPay_e.setText("0");

			txtMatPay_e.setText(vmDao.MatPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotal()); // 자재비
			txtWmPay_e.setText(vmDao.WorkPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotal()); // 인건비

		} catch (Exception e) {
			System.out.println("자재인건 총비용: " + e);
			e.printStackTrace();
		}

		try {
			if (txtMatPay_e.getText() == null) {
				txtMatPay_e.setText("0");
			}
			if (txtWmPay_e.getText() == null) {
				txtWmPay_e.setText("0");
			}

			int totalPay = Integer.parseInt(txtMatPay_e.getText()) + Integer.parseInt(txtWmPay_e.getText());
			txtTotal_e.setText(totalPay + ""); // 총비용

			int magin = Integer.parseInt(txtConPay_e.getText()) - totalPay;
			txtMagin_e.setText(magin + ""); // 마진

			txtDocument_e.setText(txtContractor_e.getText() + "폴더에 계약정보가 있습니다.");
		} catch (Exception e) {
			System.out.println("총비용,마진계산");
			e.printStackTrace();
		}
	}

	// 넘버
	public void no() {
		try {
			int number = Connect.management.getNo();
			txtNo_e.setText(number + "");
		} catch (Exception e) {
			System.out.println("넘버" + e);
		}
	}
}
