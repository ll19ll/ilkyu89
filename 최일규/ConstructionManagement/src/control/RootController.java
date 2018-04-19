package control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Connect;
import model.EditVO;
import model.ManagementVO;
import model.MaterialVO;
import model.WorkManVO;

public class RootController extends Connect implements Initializable {

	// 메인창
	@FXML
	private Button btnMonthlySales; // 월매출액 버튼
	@FXML
	private Button btnTotalList; // 전체 보기
	@FXML
	private TextField txtSearch; // 검색 할 이름 입력창
	@FXML
	private DatePicker dpSearchDay; // 검색 할 날짜 선택
	@FXML
	private Button btnSearch; // 검색 버튼
	@FXML
	private TableView<ManagementVO> tvContractList; // 메인 테이블뷰
	@FXML
	private Button btnContractAdd; // 등록 버튼
	@FXML
	private Button btnContractDelete; // 삭제버튼
	@FXML
	private Button btnExit; // 종료 버튼

	int no; // 삭제시

	// 파일을 저장 할 폴더를 매개변수로 파일 객체 생성
	private File dirSave = new File("/file");
	// 파일을 저장 할 파일 객체 선언
	File selectedFile = null;

	ObservableList<ManagementVO> mainData = FXCollections.observableArrayList();
	ObservableList<ManagementVO> selectMain = null; // 테이블에서 선택한 정보 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// 메인 버튼 상태
		tvContractList.setEditable(false);
		btnContractDelete.setDisable(true);
		dpSearchDay.setValue(null);

		// 테이블 뷰 컬럼
		TableColumn colName = new TableColumn("이름");
		colName.setMaxWidth(70);
		colName.setMinWidth(70);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("contractor"));

		TableColumn colPhone = new TableColumn("전화번호");
		colPhone.setMaxWidth(100);
		colPhone.setMinWidth(100);
		colPhone.setStyle("-fx-allignment: CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("conPhone"));

		TableColumn colResidence = new TableColumn("주소");
		colResidence.setMaxWidth(200);
		colResidence.setMinWidth(200);
		colResidence.setStyle("-fx-allignment: CENTER");
		colResidence.setCellValueFactory(new PropertyValueFactory<>("conResidence"));

		TableColumn colConName = new TableColumn("계약명");
		colConName.setMaxWidth(170);
		colConName.setMinWidth(170);
		colConName.setStyle("-fx-allignment: CENTER");
		colConName.setCellValueFactory(new PropertyValueFactory<>("ConName"));

		TableColumn colConDay = new TableColumn("날짜");
		colConDay.setMaxWidth(110);
		colConDay.setMinWidth(110);
		colConDay.setStyle("-fx-allignment: CENTER");
		colConDay.setCellValueFactory(new PropertyValueFactory<>("constructionDay"));

		tvContractList.setItems(mainData);
		tvContractList.getColumns().addAll(colName, colPhone, colResidence, colConName, colConDay);

		btnMonthlySales.setOnAction(event -> handlerBtnMonthlySalesAction(event)); // 월별 매출액 버튼 이벤트
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색 버튼 이벤트
		btnContractAdd.setOnAction(event -> handlerBtnContractAddAction(event)); // 등록 버튼 이벤트
		btnContractDelete.setOnAction(event -> handlerBtnContractDelete(event)); // 삭제 버튼 이벤트
		btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료 버튼 이벤트
		tvContractList.setOnMouseClicked(event -> handleCheckAction(event)); // 테이블 뷰 더블 클릭 이벤트(확인,수정창 띄움)

		// 이름검색창 클릭 이벤트
		txtSearch.setOnMouseClicked(event -> {
			dpSearchDay.setValue(null);
		});

		// 전체보기버튼 이벤트
		btnTotalList.setOnAction(event -> {
			mainData.removeAll(mainData);
			totalList();
			dpSearchDay.setValue(null);
		});

		// 전체 정보 불러오기
		totalList();
		tvContractList.setItems(mainData);
	}

	// 테이블뷰 더블클릭 이벤트 메소드 (확인,수정창)
	private void handleCheckAction(MouseEvent event) {
		if (event.getClickCount() != 2) {
			try {
				selectMain = tvContractList.getSelectionModel().getSelectedItems();
				no = selectMain.get(0).getNo();
				System.out.println(no);
				btnContractDelete.setDisable(false);

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("계약자 선택");
				alert.setHeaderText("선택한 계약자가 없습니다.");
				alert.setContentText("계약자를 추가한 후에 선택하세요.");
				alert.showAndWait();
				btnContractDelete.setDisable(true);
			}
			return;
		}

		try {

			ViewDAO vDao = new ViewDAO();
			EditVO eVo1 = vDao.MatPayTotal(no);
			EditVO eVo2 = vDao.WorkPayTotal(no);
			ManagementVO mVo = vDao.getEditTotal(no);

			Connect.management = mVo;
			Connect.edit = eVo1;
			Connect.edit = eVo2;

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Check.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("공사 정보");
			mainMtage.setScene(scene);
			mainMtage.show();

			Stage oldStage = (Stage) btnContractAdd.getScene().getWindow();
			oldStage.close();

		} catch (Exception e) {
			System.out.println("공사정보" + e);
			e.printStackTrace();
		}
	}

	// 월별매출액 버튼 바차트 이벤트 메소드
	private void handlerBtnMonthlySalesAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BarChart.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("월별 매출액");
			mainMtage.setScene(scene);
			mainMtage.show();

		} catch (Exception e) {
			System.out.println("월별매출액" + e);
		}
	}

	// 검색 버튼 이벤트 메소드
	private void handlerBtnSearchAction(ActionEvent event) {
		ManagementVO mnVo = new ManagementVO();
		ManagementVO mdVo = new ManagementVO();

		SearchDAO sDao = null;
		ViewDAO vDao = null;

		Object[][] totalData = null;

		String searchName = "";
		String searchDay = "";
		boolean searchResult = false;

		try {
			searchName = txtSearch.getText().trim();
			searchDay = dpSearchDay.getValue() + "";
			sDao = new SearchDAO();
			mnVo = sDao.getManagementCheck_name(searchName);
			mdVo = sDao.getManagementCheck_day(searchDay);
			System.out.println(searchDay);

			vDao = new ViewDAO();

			// 아무 값도 없을때
			if (searchDay.equals("") && searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("계약정보 검색");
				alert.setHeaderText("계약자의 이름을 입력하세요.");
				alert.setContentText("다음부터는 똑바로 입력하세요.");
				alert.showAndWait();
			}
			// 이름으로 검색
			if (!searchName.equals("") && (mnVo != null) && (searchDay.equals(searchDay))) {
				ArrayList<String> title;
				ArrayList<ManagementVO> list;

				title = vDao.getColumnName();
				int columnCount = title.size();

				list = vDao.getManagementTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (mnVo.getContractor().equals(searchName)) {
					txtSearch.clear();
					mainData.removeAll(mainData);
					for (int index = 0; index < rowCount; index++) {
						System.out.println(index);
						mnVo = list.get(index);
						if (mnVo.getContractor().equals(searchName)) {
							mainData.add(mnVo);
							searchResult = true;
						}
					}
				}
			}

			// 날짜로 검색
			if (!searchDay.equals("") && (mdVo != null) && searchName.equals("")) {
				ArrayList<String> title;
				ArrayList<ManagementVO> list;

				title = vDao.getColumnName();
				int columnCount = title.size();

				list = vDao.getManagementTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];
				System.out.println(mdVo.getConstructionDay());
				System.out.println(searchDay);

				if (mdVo.getConstructionDay().substring(0,10).equals(searchDay)) {
					txtSearch.clear();
					mainData.removeAll(mainData);
					System.out.println(mdVo.getConstructionDay());
					for (int index = 0; index < rowCount; index++) {
						System.out.println(index);
						mdVo = list.get(index);
						System.out.println(mdVo.getConstructionDay());
						System.out.println(searchDay);
						if (mdVo.getConstructionDay().equals(searchDay)) {
							mainData.add(mdVo);
							searchResult = true;
							System.out.println("여기까지와보시지");
						}
					}
				}
			}

			if (!searchResult) {
				txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("계약정보 검색");
				alert.setHeaderText(searchName + "계약정보가 리스트에 없습니다.");
				alert.setContentText("다시 검색 하세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			System.out.println("검색 오류");
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("계약정보 검색 오류");
			alert.setHeaderText("계약 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 검색하세요.");
			alert.showAndWait();
		}
	}

	// 등록 버튼 이벤트 메소드
	private void handlerBtnContractAddAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Write.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("정보 등록");
			mainMtage.setScene(scene);
			mainMtage.show();

			Stage oldStage = (Stage) btnContractAdd.getScene().getWindow();
			oldStage.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 삭제 버튼 이벤트 메소드
	private void handlerBtnContractDelete(ActionEvent event) {
		DeleteDAO dDao = null;
		dDao = new DeleteDAO();
		try {
			dDao.getMainMatDelete(no);
			dDao.getMainWorkDelete(no);
			dDao.getMainDelete(no);

			// 차후 파일삭제 업데이트하기.
		} catch (Exception e) {
			System.out.println("메인삭제버튼" + e);
		}
		mainData.removeAll(mainData);
		totalList();
		btnContractDelete.setDisable(true);
	}

	// 종료 버튼 이벤트 메소드
	private void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// 전체 리스트
	private void totalList() {
		Object[][] totalData;

		ViewDAO vDao = new ViewDAO();
		ManagementVO mVo = null;
		ArrayList<String> title;
		ArrayList<ManagementVO> list;

		title = vDao.getColumnName();
		int columnCount = title.size();

		list = vDao.getManagementTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			mVo = list.get(index);
			mainData.add(mVo);
		}

	}
}