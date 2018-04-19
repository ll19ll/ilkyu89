package control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ManagementVO;

public class InfoController implements Initializable {
	// 등록창

	// 계약자 정보
	@FXML
	private Button btnContractReset; // 계약자 초기화
	@FXML
	private TextField txtContractor; // 계약자 이름
	@FXML
	private TextField txtConPhone; // 계약자 연락처
	@FXML
	private TextField txtConBirth; // 계약자 생년월일
	@FXML
	private TextField txtConResidence; // 계약자 주소
	@FXML
	private TextField txtConName; // 계약명
	@FXML
	private ComboBox<String> cbMeter1; // 계량기 등급 콤보박스
	@FXML
	private ComboBox<String> cbMeter2;
	@FXML
	private ComboBox<String> cbMeter3;
	@FXML
	private TextField txtMeter1; // 계량기 갯수
	@FXML
	private TextField txtMeter2;
	@FXML
	private TextField txtMeter3;
	@FXML
	private ComboBox<String> cbConType; // 건물형태
	@FXML
	private ComboBox<String> cbConUse; // 용도
	@FXML
	private DatePicker dpConDay; // 공사 날짜
	@FXML
	private TextField txtConPay; // 공사 금액

	// 인력 정보
	@FXML
	private Button btnManPowerReset; // 인력 정보 초기화
	@FXML
	private TextField txtDerector; // 공사감독 이름
	@FXML
	private TextField txtDirPhone; // 공사감독 연락처
	@FXML
	private TextField txtDirDepartment; // 공사감독 소속
	@FXML
	private TextField txtManager; // 시공관리자 이름
	@FXML
	private TextField txtManPhone; // 시공관리자 연락처
	@FXML
	private TextField txtManDepartment; // 시공관리자 소속
	@FXML
	private TextField txtLeader; // 현장소장 이름
	@FXML
	private TextField txtLdPhone; // 현장소장 연락처
	@FXML
	private TextField txtLdDepartment; // 현장소장 소속

	@FXML
	private Button btnInfoAdd; // 정보등록
	@FXML
	private Button btnInfoExit; // 정보 창 닫기

	ObservableList<ManagementVO> mainData = FXCollections.observableArrayList();
	ObservableList<ManagementVO> selectMain = null; // 테이블에서 선택한 정보 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 데이터픽커 상태설정
		dpConDay.setValue(LocalDate.now());

		// 콤보값 설정
		cbMeter1.setItems(FXCollections.observableArrayList(null,"G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter2.setItems(FXCollections.observableArrayList(null,"G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter3.setItems(FXCollections.observableArrayList(null,"G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbConType.setItems(FXCollections.observableArrayList(null,"주택", "아파트", "상가", "공공시설"));
		cbConUse.setItems(FXCollections.observableArrayList(null,"주택용(취사)", "일반용(영업)", "산업용"));

		btnInfoAdd.setOnAction(event -> handleBtnInfoAddAction(event)); // 등록
		btnInfoExit.setOnAction(event -> handleBtnInfoExitAction(event)); // 취소

		//계약자 초기화버튼
		btnContractReset.setOnAction(event ->{
			txtContractor.clear(); // 계약자 이름
			txtConPhone.clear(); // 계약자 연락처
			txtConBirth.clear(); // 계약자 생년월일
			txtConResidence.clear(); // 계약자 주소
			txtConName.clear(); // 계약명
			cbMeter1.getSelectionModel().clearSelection(); // 계량기 등급 콤보박스
			cbMeter2.getSelectionModel().clearSelection();
			cbMeter3.getSelectionModel().clearSelection();
			txtMeter1.clear(); // 계량기 갯수
			txtMeter2.clear();
			txtMeter3.clear();
			cbConType.getSelectionModel().clearSelection(); // 건물형태
			cbConUse.getSelectionModel().clearSelection();// 용도
			txtConPay.clear(); // 공사 금액
		});
		// 현장 중심 인력 초기화버튼
		btnManPowerReset.setOnAction(event ->{
			txtDerector.clear(); // 공사감독 이름
			txtDirPhone.clear(); // 공사감독 연락처
			txtDirDepartment.clear(); // 공사감독 소속
			txtManager.clear(); // 시공관리자 이름
			txtManPhone.clear(); // 시공관리자 연락처
			txtManDepartment.clear(); // 시공관리자 소속
			txtLeader.clear(); // 현장소장 이름
			txtLdPhone.clear(); // 현장소장 연락처
			txtLdDepartment.clear(); // 현장소장 소속
		});
	}

	// 취소버튼
	private void handleBtnInfoExitAction(ActionEvent event) {
		Stage oldStage = (Stage) btnInfoAdd.getScene().getWindow();
		oldStage.close();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainList.fxml"));
		Parent mainView = (Parent) loader.load();
		Scene scene = new Scene(mainView);
		Stage mainMtage = new Stage();
		mainMtage.setTitle("남산 ENG");
		mainMtage.setScene(scene);
		mainMtage.show();
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
	// 등록버튼
	private void handleBtnInfoAddAction(ActionEvent event) {
		try {
			mainData.removeAll(mainData);
			ManagementVO mVo = null;
			ManagementDAO mDao = new ManagementDAO();
			if (event.getSource().equals(btnInfoAdd)) {
				mVo = new ManagementVO(txtContractor.getText(), txtConPhone.getText(), txtConBirth.getText(),
						txtConResidence.getText(), txtConName.getText(), dpConDay.getValue() + "",
						cbMeter1.getSelectionModel().getSelectedItem(), cbMeter2.getSelectionModel().getSelectedItem(),
						cbMeter3.getSelectionModel().getSelectedItem(), txtMeter1.getText(), txtMeter2.getText(),
						txtMeter3.getText(), cbConType.getSelectionModel().getSelectedItem(),
						cbConUse.getSelectionModel().getSelectedItem(), txtConPay.getText(), txtDerector.getText(),
						txtDirPhone.getText(), txtDirDepartment.getText(), txtManager.getText(), txtManPhone.getText(),
						txtManDepartment.getText(), txtLeader.getText(), txtLdPhone.getText(),
						txtLdDepartment.getText());
				mDao = new ManagementDAO();
				mDao.getConstuctionRegiste(mVo);

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PayList.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scene = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("정보 등록");
				mainMtage.setScene(scene);
				mainMtage.show();
				
				Stage oldStage = (Stage) btnInfoAdd.getScene().getWindow();
				oldStage.close();

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("정보 입력");
					alert.setHeaderText(txtContractor.getText() + "의 공사정보가 추가되었습니다.");
					alert.setContentText("다음 공사 정보를 입력하세요");
					alert.showAndWait();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 입력");
			alert.setHeaderText(txtContractor.getText() + "의 공사정보가 추가되지않았습니다.");
			alert.setContentText("다시 입력하세요");
			alert.showAndWait();
		}
	};
}
