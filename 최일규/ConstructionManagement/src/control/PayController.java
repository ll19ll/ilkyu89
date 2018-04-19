package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ManagementVO;
import model.MaterialVO;
import model.WorkManVO;

public class PayController implements Initializable {
	@FXML
	private Text txtNo;

	// 자재비 정보
	@FXML
	private TextField txtMaterial; // 자재명
	@FXML
	private TextField txtMatCount; // 자재 갯수
	@FXML
	private TextField txtMatPay; // 자재 가격
	@FXML
	private TextField txtMatStore; // 자재 판매처
	@FXML
	private Button btnMatAdd; // 등록버튼
	@FXML
	private TableView<MaterialVO> tvMatList; // 자재 목록
	@FXML
	private Button btnMatDelete; // 자재 정보 삭제
	@FXML
	private Text txtMatTotal; // 자재비 총액

	int m_no; // 자재비 삭제를 위한 지정

	// 자재비 리스트
	ObservableList<MaterialVO> matData = FXCollections.observableArrayList();
	ObservableList<MaterialVO> matMain = null; // 테이블에서 선택한 정보 저장

	// 인건비 정보
	@FXML
	private TextField txtWorkMan; // 인력 이름
	@FXML
	private TextField txtPosition; // 직책
	@FXML
	private TextField txtWorkDay; // 출근 일수
	@FXML
	private TextField txtWorkPay; // 인건비
	@FXML
	private Button btnWorkAdd; // 등록버튼
	@FXML
	private TableView<WorkManVO> tvWorkView; // 인력 목록
	@FXML
	private Button btnWorkDelete; // 인력 정보 삭제

	@FXML
	private Text txtWorkTotal; // 인력비 총액

	int w_no; // 인건비 삭제를 위한 지정

	// 인건비 리스트
	ObservableList<WorkManVO> workData = FXCollections.observableArrayList();
	ObservableList<WorkManVO> workMain = null; // 테이블에서 선택한 정보 저장

	// 첨부파일-----------------------------------------------------------------------

	@FXML
	private Button btnApp; // 파일 첨부
	@FXML
	private Button btnDocumentDelete; // 파일 삭제

	// 첨부 파일
	@FXML
	private TableView<ManagementVO> tvDocumentView; // 첨부파일 목록
	private ObservableList<ManagementVO> fileSaveName = FXCollections.observableArrayList();
	private ObservableList<ManagementVO> fileSN = null;
	private String folderName;

	// 파일을 저장 할 폴더를 매개변수로 파일 객체 생성
	private String folder = null;
	private File dirSave;
	// 저장 할 파일 객체 선언
	File selectedFile = null;

	int no; // 삭제시

	private Stage primaryStage;

	String selectFileName = ""; // 이미지 파일명
	String localUrl = ""; // 이미지 파일 경로

	// --------------------------------------------------------------------------------

	@FXML
	private Button btnSave; // 정보 저장
	@FXML
	private Button btnExit; // 정보창 닫기

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		no();

		SearchDAO sDao = new SearchDAO();
		try {
			folder = sDao.getFolder(Integer.parseInt(txtNo.getText())).getContractor();
		} catch (Exception e) {
		}
		dirSave = new File("E:\\javaFX\\ConstructionManagement\\file\\" + folder);
		if (!dirSave.exists()) {
			dirSave.mkdirs();
			System.out.println("파일생성성공");
		}

		// 자재비 메인
		tvMatList.setEditable(false);
		btnMatDelete.setDisable(true);

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

		tvMatList.setItems(matData);
		tvMatList.getColumns().addAll(colName, colCount, colPay, colStore);

		// ---------------------------------------------------------------//
		matData.removeAll(matData);
		totalMatList();

		btnMatAdd.setOnAction(event -> handleBtnMatAddAction(event)); // 등록버튼 이벤트
		btnMatDelete.setOnAction(event -> handleBtnMatDelete(event)); // 삭제버튼 이벤트
		tvMatList.setOnMouseClicked(event -> handleMatDeleteAction(event)); // 삭제를위한 테이블뷰 클릭 이벤트

		// ------------------------------------------------------------------------------------------------------------------//

		// 인건비 메인
		tvWorkView.setEditable(false);
		btnWorkDelete.setDisable(true);

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

		tvWorkView.setItems(workData);
		tvWorkView.getColumns().addAll(colWork, colPosition, colDay, colWorkPay);

		// -------------------------------------------------------//

		workData.removeAll(workData);
		totalWorkList();

		btnWorkAdd.setOnAction(event -> handleBtnWorkAddAction(event)); // 등록버튼 이벤트
		btnWorkDelete.setOnAction(event -> handleBtnWorkDelete(event)); // 삭제버튼 이벤트
		tvWorkView.setOnMouseClicked(event -> handleWorkDeleteAction(event)); // 삭제를위한 테이블뷰 클릭 이벤트

		btnSave.setOnAction(event -> handleBtnSaveAction(event)); // 저장버튼 이벤트
		btnExit.setOnAction(event -> handleBtnExitAction(event)); // 닫기버튼 이벤트

		// 첨부파일 메인
		// 텍스트필드 상태설정
		tvDocumentView.setEditable(false);

		// 버튼 상태 성정
		btnDocumentDelete.setDisable(true);

		TableColumn colFileName = new TableColumn("첨부파일");
		colFileName.setMaxWidth(240);
		colFileName.setMinWidth(240);
		colFileName.setStyle("-fx-allignment: CENTER");
		colFileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
		tvDocumentView.setItems(fileSaveName);
		tvDocumentView.getColumns().addAll(colFileName);

		btnApp.setOnAction(event -> handleBtnAppAction(event)); // 첨부
		btnDocumentDelete.setOnAction(event -> handleBtnDocumentDeleteAction(event)); // 첨부파일 삭제
		tvDocumentView.setOnMouseClicked(event -> handleFileNameAction(event)); // 테이블 마우스 액션

		if (txtMatTotal.getText() == null) {
			txtMatTotal.setText("0");
		}
		if (txtWorkTotal.getText() == null) {
			txtWorkTotal.setText("0");
		}

	}

	// 자재비------------------------------------------------------------------------------

	// 삭제를위한 테이블뷰 클릭 이벤트 메소드
	private void handleMatDeleteAction(MouseEvent event) {
		matMain = tvMatList.getSelectionModel().getSelectedItems();
		m_no = matMain.get(0).getM_no();
		System.out.println(m_no);
		btnMatDelete.setDisable(false);
	}

	// 등록버튼 이벤트 메소드(해결)
	private void handleBtnMatAddAction(ActionEvent event) {
		String total = Integer.parseInt(txtMatCount.getText()) * Integer.parseInt(txtMatPay.getText()) + "";
		try {
			MaterialVO matVo = null;
			ManagementDAO mDao = new ManagementDAO();

			if (event.getSource().equals(btnMatAdd)) {
				matVo = new MaterialVO(txtMaterial.getText(), txtMatCount.getText(), txtMatPay.getText(),
						txtMatStore.getText(), total, Integer.parseInt(txtNo.getText()));

				mDao.getMaterialRegiste(matVo, Integer.parseInt(txtNo.getText()));
				txtMaterial.clear();
				txtMatCount.clear();
				txtMatPay.clear();
				txtMatStore.clear();

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("정보 입력");
					alert.setHeaderText("자재정보가 추가되었습니다.");
					alert.setContentText("다음 자재 정보를 입력하세요");
					alert.showAndWait();

					// 테이블 안에 있는 자재비 총액 자동 계산
					ViewDAO vmDao = new ViewDAO();
					try {
						txtMatTotal
								.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo.getText())).getMatTotalPay());
					} catch (Exception e) {
						System.out.println("자재비총액" + e);
					}

					matData.removeAll(matData);
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
			System.out.println(m_no);
			dDao.getMaterialDelete(m_no);
			matData.removeAll(matData);

			// 전체 정보
			totalMatList();
			btnMatDelete.setDisable(true);

			// 테이블 안에 있는 자재비 총액 자동 계산
			ViewDAO vmDao = new ViewDAO();
			try {
				txtMatTotal.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo.getText())).getMatTotalPay());
			} catch (Exception e) {
				System.out.println("자재비총액" + e);
			}

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

		list = vDao.getMaterialTotal(Integer.parseInt(txtNo.getText()));
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			matVo = list.get(index);
			matData.add(matVo);
		}
	}

	// 인건비------------------------------------------------------------------------------
	// 삭제를위한 테이블뷰 클릭 이벤트
	private void handleWorkDeleteAction(MouseEvent event) {
		System.out.println(12);
		workMain = tvWorkView.getSelectionModel().getSelectedItems();
		w_no = workMain.get(0).getW_no();
		System.out.println(w_no+"인건비");
		btnWorkDelete.setDisable(false);
	}

	// 인력 등록버튼 이벤트 메소드
	private void handleBtnWorkAddAction(ActionEvent event) {

		String total = Integer.parseInt(txtWorkDay.getText()) * Integer.parseInt(txtWorkPay.getText()) + "";
		try {
			WorkManVO workVo = null;
			ManagementDAO mDao = new ManagementDAO();

			if (event.getSource().equals(btnWorkAdd)) {
				workVo = new WorkManVO(txtWorkMan.getText(), txtPosition.getText(), txtWorkPay.getText(),
						txtWorkDay.getText(), total, Integer.parseInt(txtNo.getText()));
				mDao = new ManagementDAO();
				mDao.getWorkManRegiste(workVo, Integer.parseInt(txtNo.getText()));

				txtWorkMan.clear();
				txtPosition.clear();
				txtWorkDay.clear();
				txtWorkPay.clear();

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("정보 입력");
					alert.setHeaderText("인력정보가 추가되었습니다.");
					alert.setContentText("다음 인력 정보를 입력하세요");
					alert.showAndWait();

					// 테이블 안에 있는 인건비 총액 자동 계산
					ViewDAO vwDao = new ViewDAO();
					try {
						txtWorkTotal
								.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo.getText())).getWorkTotalPay());
					} catch (Exception e) {
						System.out.println("인건비총액" + e);
					}

					workData.removeAll(workData);
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
			dDao.getWorkManDelete(w_no);
			System.out.println(w_no);
			workData.removeAll(workData);
			totalWorkList();
			btnWorkDelete.setDisable(true);

			// 테이블 안에 있는 인건비 총액 자동 계산
			ViewDAO vwDao = new ViewDAO();
			try {
				txtWorkTotal.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo.getText())).getWorkTotalPay());
				btnWorkDelete.setDisable(true);
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

		list = vDao.getWorkManTotal(Integer.parseInt(txtNo.getText()));
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			workVo = list.get(index);
			workData.add(workVo);
		}
	}

	// 첨부파일-------------------------------------------------------------------------
	// 테이블아레아 클릭이벤트
	private void handleFileNameAction(MouseEvent event) {
		if (event.getClickCount() != 2) {
			try {
				fileSN = tvDocumentView.getSelectionModel().getSelectedItems();
				selectFileName = fileSN.get(0).getFileName();
				btnDocumentDelete.setDisable(false);
				System.out.println("첨부클릭" + selectFileName);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("파일 선택");
				alert.setHeaderText("선택한 파일이 없습니다.");
				alert.setContentText("파일을 추가한 후에 선택하세요.");
				alert.showAndWait();
				btnDocumentDelete.setDisable(true);
			}
			return;
		}
		try {
			SearchDAO sDao = new SearchDAO();
			folder = sDao.getFolder(Integer.parseInt(txtNo.getText())).getContractor();
			Runtime open = Runtime.getRuntime();
			open.exec("explorer E:\\javaFX\\ConstructionManagement\\file\\" + folder);
		} catch (Exception e) {
			System.out.println("파일열기: " + e);
		}
	}

	// 첨부 파일 삭제
	private void handleBtnDocumentDeleteAction(ActionEvent event) {

		try {
			DeleteDAO dDao = new DeleteDAO();
			dDao.getFileDelete(Integer.parseInt(txtNo.getText()));
			fileSaveName.removeAll(fileSN);
			fileDelete(selectedFile);

			btnDocumentDelete.setDisable(true);

		} catch (Exception e) {
			System.out.println("첨부파일삭제" + e);
			System.out.println(selectedFile);
		}
	}

	// 파일 삭제 메소드
	public boolean fileDelete(File fileName) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getCanonicalPath() + "\\" + fileName); // 삭제 이미지 파일
			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();
			}
		} catch (Exception ie) {
			System.out.println("파일삭제 " + ie.getMessage());
			result = false;
		}
		return result;
	}

	// 첨부버튼
	private void handleBtnAppAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("file", "*"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();
				String fileName = fileSave(selectedFile);
				ManagementVO mVo = new ManagementVO(fileName);
				fileSaveName.addAll(mVo);

				try {

				} catch (Exception e) {
					System.out.println("파일저장" + e);
				}
			}
			if (tvDocumentView.getInsets() != null) {
				btnDocumentDelete.setDisable(false);
			}
		} catch (MalformedURLException e) {
			System.out.println("첨부버튼" + e);

			if (selectedFile != null) {
				selectFileName = selectedFile.getName();
			}
		}
	}

	/*
	 * fileSave() 파일 저장 메소드
	 * 
	 * @param(읽어 올 파일 객체)
	 */

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

	// ------------------------------------------------------------------------------

	// 저장 버튼 이벤트 메소드
	private void handleBtnSaveAction(ActionEvent event) {

		try {
			ManagementVO mVo = new ManagementVO();
			SearchDAO sDao = new SearchDAO();
			ManagementDAO mDao = new ManagementDAO();

			if (event.getSource().equals(btnSave)) {
				System.out.println(1);

				try {

					if (txtMatTotal.getText() == null) {
						txtMatTotal.setText("0");
					}
					if (txtWorkTotal.getText() == null) {
						txtWorkTotal.setText("0");
					}
					// 마진,파일네임 업데이트

					// 공사비를 가져옴
					String conPay = sDao.getConPay(Integer.parseInt(txtNo.getText())).getConstructionPay();

					System.out.println(conPay);
					// 마진 계산
					int magin = Integer.parseInt(conPay)
							- (Integer.parseInt(txtMatTotal.getText()) + Integer.parseInt(txtWorkTotal.getText()));
					folder = sDao.getFolder(Integer.parseInt(txtNo.getText())).getContractor();
					System.out.println(magin + " , " + folder);

					// 데이터 베이스에 폴더 이름과 마진 등록
					mDao.getMaginFileUpdate(Integer.parseInt(txtNo.getText()), magin + "", folder);

					Stage oldStage = (Stage) btnSave.getScene().getWindow();
					oldStage.close();

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainList.fxml"));
					Parent mainView = (Parent) loader.load();
					Scene scene = new Scene(mainView);
					Stage mainMtage = new Stage();
					mainMtage.setTitle("남산 ENG");
					mainMtage.setScene(scene);
					mainMtage.show();

				} catch (Exception e) {
					System.out.println("마진,파일" + e);
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
		Stage oldStage = (Stage) btnSave.getScene().getWindow();
		oldStage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainList.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("정보 등록");
			mainMtage.setScene(scene);
			mainMtage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 넘버
	public void no() {
		try {
			ArrayList<ManagementVO> list = new ArrayList<>();
			SearchDAO sDao = new SearchDAO();
			list = sDao.getNumber();
			int number = list.get(0).getNo();
			txtNo.setText(number + "");
		} catch (Exception e) {
			System.out.println("넘버" + e);
		}
	}

	// 폴더 이름
	public void folderName() {
		try {
			SearchDAO sDao = new SearchDAO();
			folder = sDao.getFolder(Integer.parseInt(txtNo.getText())).getContractor();

			System.out.println(folder);
		} catch (Exception e) {
			System.out.println("폴더이름" + e);
		}
	}
}
