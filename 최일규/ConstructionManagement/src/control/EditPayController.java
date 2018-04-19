package control;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect;
import model.MaterialVO;
import model.WorkManVO;

public class EditPayController extends Connect implements Initializable {

	@FXML
	private Text txtNo_e;

	// 자재비 정보
	@FXML
	private TextField txtMaterial_e; // 자재명
	@FXML
	private TextField txtMatCount_e; // 자재 갯수
	@FXML
	private TextField txtMatPay_e; // 자재 가격
	@FXML
	private TextField txtMatStore_e; // 자재 판매처
	@FXML
	private Button btnMatAdd_e; // 등록버튼
	@FXML
	private TableView<MaterialVO> tvMatList_e; // 자재 목록
	@FXML
	private Button btnMatDelete_e; // 자재 정보 삭제
	@FXML
	private Text txtMatTotal_e; // 자재비 총액

	int m_no_e; // 자재비 삭제를 위한 지정

	// 자재비 리스트
	ObservableList<MaterialVO> matData_e = FXCollections.observableArrayList();
	ObservableList<MaterialVO> matMain_e = null; // 테이블에서 선택한 정보 저장

	// 인건비 정보
	@FXML
	private TextField txtWorkMan_e; // 인력 이름
	@FXML
	private TextField txtPosition_e; // 직책
	@FXML
	private TextField txtWorkDay_e; // 출근 일수
	@FXML
	private TextField txtWorkPay_e; // 인건비
	@FXML
	private Button btnWorkAdd_e; // 등록버튼
	@FXML
	private TableView<WorkManVO> tvWorkView_e; // 인력 목록
	@FXML
	private Button btnWorkDelete_e; // 인력 정보 삭제

	@FXML
	private Text txtWorkTotal_e; // 인력비 총액

	int w_no_e; // 인건비 삭제를 위한 지정

	// 인건비 리스트
	ObservableList<WorkManVO> workData_e = FXCollections.observableArrayList();
	ObservableList<WorkManVO> workMain_e = null; // 테이블에서 선택한 정보 저장

	// --------------------------------------------------------------------------
	@FXML
	private Button btnSave_e; // 정보 저장
	@FXML
	private Button btnExit_e; // 정보창 닫기

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		no();

		// 자재비 메인
		tvMatList_e.setEditable(false);
		btnMatDelete_e.setDisable(true);

		TableColumn colName = new TableColumn("자재명");
		colName.setMaxWidth(70);
		colName.setMinWidth(70);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("material"));

		TableColumn colCount = new TableColumn("갯수");
		colCount.setMaxWidth(70);
		colCount.setMinWidth(70);
		colCount.setStyle("-fx-allignment: CENTER");
		colCount.setCellValueFactory(new PropertyValueFactory<>("matCount"));

		TableColumn colPay = new TableColumn("가격");
		colPay.setMaxWidth(70);
		colPay.setMinWidth(70);
		colPay.setStyle("-fx-allignment: CENTER");
		colPay.setCellValueFactory(new PropertyValueFactory<>("matTotal"));

		TableColumn colStore = new TableColumn("판매처");
		colStore.setMaxWidth(70);
		colStore.setMinWidth(70);
		colStore.setStyle("-fx-allignment: CENTER");
		colStore.setCellValueFactory(new PropertyValueFactory<>("matStore"));

		tvMatList_e.setItems(matData_e);
		tvMatList_e.getColumns().addAll(colName, colCount, colPay, colStore);

		// ---------------------------------------------------------------//
		matData_e.removeAll(matData_e);
		totalMatList();

		// 테이블 안에 있는 자재비 총액 자동 계산
		ViewDAO vmDao = new ViewDAO();
		try {
			txtMatTotal_e.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotalPay());
		} catch (Exception e) {
			System.out.println("자재비총액" + e);
		}

		btnMatAdd_e.setOnAction(event -> handleBtnMatAddAction(event)); // 등록버튼 이벤트
		btnMatDelete_e.setOnAction(event -> handleBtnMatDelete(event)); // 삭제버튼 이벤트
		tvMatList_e.setOnMouseClicked(event -> handleMatDeleteAction(event)); // 삭제를위한 테이블뷰 클릭 이벤트

		// ------------------------------------------------------------------------------------------------------------------//

		// 인건비 메인
		tvWorkView_e.setEditable(false);
		btnWorkDelete_e.setDisable(true);

		// 테이블 뷰 컬럼
		TableColumn colWork = new TableColumn("이름");
		colWork.setMaxWidth(70);
		colWork.setMinWidth(70);
		colWork.setStyle("-fx-allignment: CENTER");
		colWork.setCellValueFactory(new PropertyValueFactory<>("workMan"));

		TableColumn colPosition = new TableColumn("직책");
		colPosition.setMaxWidth(70);
		colPosition.setMinWidth(70);
		colPosition.setStyle("-fx-allignment: CENTER");
		colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

		TableColumn colDay = new TableColumn("출근일수");
		colDay.setMaxWidth(70);
		colDay.setMinWidth(70);
		colDay.setStyle("-fx-allignment: CENTER");
		colDay.setCellValueFactory(new PropertyValueFactory<>("workDay"));

		TableColumn colWorkPay = new TableColumn("인건비");
		colWorkPay.setMaxWidth(70);
		colWorkPay.setMinWidth(70);
		colWorkPay.setStyle("-fx-allignment: CENTER");
		colWorkPay.setCellValueFactory(new PropertyValueFactory<>("workPay"));

		tvWorkView_e.setItems(workData_e);
		tvWorkView_e.getColumns().addAll(colWork, colPosition, colDay, colWorkPay);

		// -------------------------------------------------------//

		workData_e.removeAll(workData_e);
		totalWorkList();

		// 테이블 안에 있는 인건비 총액 자동 계산
		ViewDAO vwDao = new ViewDAO();
		try {
			txtWorkTotal_e.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotalPay());
		} catch (Exception e) {
			System.out.println("인건비총액" + e);
		}

		btnWorkAdd_e.setOnAction(event -> handleBtnWorkAddAction(event)); // 등록버튼 이벤트
		btnWorkDelete_e.setOnAction(event -> handleBtnWorkDelete(event)); // 삭제버튼 이벤트
		tvWorkView_e.setOnMouseClicked(event -> handleWorkDeleteAction(event)); // 삭제를위한 테이블뷰 클릭 이벤트

		btnSave_e.setOnAction(event -> handleBtnSaveAction(event)); // 저장버튼 이벤트
		btnExit_e.setOnAction(event -> handleBtnExitAction(event)); // 닫기버튼 이벤트

		if (txtMatTotal_e.getText() == null) {
			txtMatTotal_e.setText("0");
		}
		if (txtWorkTotal_e.getText() == null) {
			txtWorkTotal_e.setText("0");
		}
	}
	// 자재비------------------------------------------------------------------------------

	// 삭제를위한 테이블뷰 클릭 이벤트 메소드
	private void handleMatDeleteAction(MouseEvent event) {
		matMain_e = tvMatList_e.getSelectionModel().getSelectedItems();
		m_no_e = matMain_e.get(0).getM_no();
		System.out.println(m_no_e);
		btnMatDelete_e.setDisable(false);
	}

	// 등록버튼 이벤트 메소드(해결)
	private void handleBtnMatAddAction(ActionEvent event) {
		String total = Integer.parseInt(txtMatCount_e.getText()) * Integer.parseInt(txtMatPay_e.getText()) + "";
		try {
			MaterialVO matVo = null;
			ManagementDAO mDao = new ManagementDAO();

			if (event.getSource().equals(btnMatAdd_e)) {
				matVo = new MaterialVO(txtMaterial_e.getText(), txtMatCount_e.getText(), txtMatPay_e.getText(),
						txtMatStore_e.getText(), total, Integer.parseInt(txtNo_e.getText()));

				mDao.getMaterialRegiste(matVo, Integer.parseInt(txtNo_e.getText()));
				txtMaterial_e.clear();
				txtMatCount_e.clear();
				txtMatPay_e.clear();
				txtMatStore_e.clear();

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("정보 입력");
					alert.setHeaderText("자재정보가 추가되었습니다.");
					alert.setContentText("다음 자재 정보를 입력하세요");
					alert.showAndWait();

					// 테이블 안에 있는 자재비 총액 자동 계산
					ViewDAO vmDao = new ViewDAO();
					try {
						txtMatTotal_e.setText(
								vmDao.getMaterialPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotalPay());
					} catch (Exception e) {
						System.out.println("자재비총액" + e);
					}

					matData_e.removeAll(matData_e);
					totalMatList();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 입력");
			alert.setHeaderText("자재정보가 추가되지않았습니다.");
			alert.setContentText("다시 입력하세요.");
			alert.showAndWait();
		}

	};

	// 삭제버튼 이벤트 메소드
	private void handleBtnMatDelete(ActionEvent event) {
		DeleteDAO dDao = null;
		dDao = new DeleteDAO();

		try {
			System.out.println(m_no_e);
			dDao.getMaterialDelete(m_no_e);
			matData_e.removeAll(matData_e);
			btnMatDelete_e.setDisable(true);

			// 전체 정보
			totalMatList();

			// 테이블 안에 있는 자재비 총액 자동 계산
			ViewDAO vmDao = new ViewDAO();
			try {
				txtMatTotal_e.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotalPay());
			} catch (Exception e) {
				System.out.println("자재비총액" + e);
			}

			btnMatDelete_e.setDisable(true);

		} catch (Exception e) {
			System.out.println("자재삭제버튼" + e);
		}
	}

	// 전체 리스트
	private void totalMatList() {
		Object[][] totalData;

		ViewDAO vDao = new ViewDAO();
		MaterialVO matVo = null;
		ArrayList<String> title;
		ArrayList<MaterialVO> list;

		title = vDao.getColumnName();
		int columnCount = title.size();

		list = vDao.getMaterialTotal(Integer.parseInt(txtNo_e.getText()));
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			matVo = list.get(index);
			matData_e.add(matVo);
		}
	}

	// 인건비------------------------------------------------------------------------------
	// 삭제를위한 테이블뷰 클릭 이벤트
	private void handleWorkDeleteAction(MouseEvent event) {
		workMain_e = tvWorkView_e.getSelectionModel().getSelectedItems();
		w_no_e = workMain_e.get(0).getW_no();
		System.out.println(w_no_e);
		btnWorkDelete_e.setDisable(false);
	}

	// 인력 등록버튼 이벤트 메소드
	private void handleBtnWorkAddAction(ActionEvent event) {

		String total = Integer.parseInt(txtWorkDay_e.getText()) * Integer.parseInt(txtWorkPay_e.getText()) + "";
		try {
			WorkManVO workVo = null;
			ManagementDAO mDao = new ManagementDAO();

			if (event.getSource().equals(btnWorkAdd_e)) {
				workVo = new WorkManVO(txtWorkMan_e.getText(), txtPosition_e.getText(), txtWorkPay_e.getText(),
						txtWorkDay_e.getText(), total, Integer.parseInt(txtNo_e.getText()));
				mDao = new ManagementDAO();
				mDao.getWorkManRegiste(workVo, Integer.parseInt(txtNo_e.getText()));

				txtWorkMan_e.clear();
				txtPosition_e.clear();
				txtWorkDay_e.clear();
				txtWorkPay_e.clear();

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("정보 입력");
					alert.setHeaderText("인력정보가 추가되었습니다.");
					alert.setContentText("다음 인력 정보를 입력하세요");
					alert.showAndWait();

					// 테이블 안에 있는 인건비 총액 자동 계산
					ViewDAO vwDao = new ViewDAO();
					try {
						txtWorkTotal_e.setText(
								vwDao.getWorkManPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotalPay());
					} catch (Exception e) {
						System.out.println("인건비총액" + e);
					}

					workData_e.removeAll(workData_e);
					totalWorkList();
				}

			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 입력");
			alert.setHeaderText("인력정보가 추가되지않았습니다.");
			alert.setContentText("다시 입력하세요.");
			alert.showAndWait();
		}

	};

	// 삭제 버튼 이벤트 메소드
	private void handleBtnWorkDelete(ActionEvent event) {
		DeleteDAO dDao = null;
		dDao = new DeleteDAO();
		try {
			dDao.getWorkManDelete(w_no_e);
			workData_e.removeAll(workData_e);
			btnWorkDelete_e.setDisable(true);
			// 전체 정보
			totalWorkList();

			// 테이블 안에 있는 인건비 총액 자동 계산
			ViewDAO vwDao = new ViewDAO();
			try {
				txtWorkTotal_e.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotalPay());
			} catch (Exception e) {
				System.out.println("인건비총액" + e);
			}

		} catch (Exception e) {
			System.out.println("인력삭제버튼" + e);
		}
	}

	// 전체 리스트
	private void totalWorkList() {
		Object[][] totalData;

		ViewDAO vDao = new ViewDAO();
		WorkManVO workVo = null;
		ArrayList<String> title;
		ArrayList<WorkManVO> list;

		title = vDao.getColumnName();
		int columnCount = title.size();

		list = vDao.getWorkManTotal(Integer.parseInt(txtNo_e.getText()));
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			workVo = list.get(index);
			workData_e.add(workVo);
		}
	}
	// ------------------------------------------------------------------------------

	// 저장 버튼 이벤트 메소드
	private void handleBtnSaveAction(ActionEvent event) {

		try {
			SearchDAO sDao = new SearchDAO();
			ManagementDAO mDao = new ManagementDAO();

			if (event.getSource().equals(btnSave_e)) {
				System.out.println(1);

				try {

					if (txtMatTotal_e.getText() == null) {
						txtMatTotal_e.setText("0");
					}
					if (txtWorkTotal_e.getText() == null) {
						txtWorkTotal_e.setText("0");
					}

					// 마진 업데이트

					// 공사비를 가져옴
					String conPay = sDao.getConPay(Integer.parseInt(txtNo_e.getText())).getConstructionPay();
					System.out.println(conPay);

					// 마진 계산
					int magin = Integer.parseInt(conPay)
							- (Integer.parseInt(txtMatTotal_e.getText()) + Integer.parseInt(txtWorkTotal_e.getText()));

					// 데이터 베이스에 마진 등록
					mDao.getMaginUpdate(Integer.parseInt(txtNo_e.getText()), magin + "");

					Stage oldStage = (Stage) btnSave_e.getScene().getWindow();
					oldStage.close();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Check.fxml"));
					Parent mainView = (Parent) loader.load();
					Scene scene = new Scene(mainView);
					Stage mainMtage = new Stage();
					mainMtage.setTitle("정보 수정");
					mainMtage.setScene(scene);
					mainMtage.show();

				} catch (Exception e) {
					System.out.println("마진: " + e);
					e.printStackTrace();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 입력");
			alert.setHeaderText("공사정보가 추가되지않았습니다.");
			alert.setContentText("다시 입력하세요.");
			alert.showAndWait();
			
			
		}
	}

	// 닫기버튼 이벤트 메소드
	private void handleBtnExitAction(ActionEvent event) {

		Stage oldStage = (Stage) btnSave_e.getScene().getWindow();
		oldStage.close();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Check.fxml"));
		Parent mainView = (Parent) loader.load();
		Scene scene = new Scene(mainView);
		Stage mainMtage = new Stage();
		mainMtage.setTitle("공사 정보");
		mainMtage.setScene(scene);
		mainMtage.show();
		} catch (Exception e) {
			// TODO: handle exception
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
