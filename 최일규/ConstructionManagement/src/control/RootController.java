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

	// ����â
	@FXML
	private Button btnMonthlySales; // ������� ��ư
	@FXML
	private Button btnTotalList; // ��ü ����
	@FXML
	private TextField txtSearch; // �˻� �� �̸� �Է�â
	@FXML
	private DatePicker dpSearchDay; // �˻� �� ��¥ ����
	@FXML
	private Button btnSearch; // �˻� ��ư
	@FXML
	private TableView<ManagementVO> tvContractList; // ���� ���̺��
	@FXML
	private Button btnContractAdd; // ��� ��ư
	@FXML
	private Button btnContractDelete; // ������ư
	@FXML
	private Button btnExit; // ���� ��ư

	int no; // ������

	// ������ ���� �� ������ �Ű������� ���� ��ü ����
	private File dirSave = new File("/file");
	// ������ ���� �� ���� ��ü ����
	File selectedFile = null;

	ObservableList<ManagementVO> mainData = FXCollections.observableArrayList();
	ObservableList<ManagementVO> selectMain = null; // ���̺��� ������ ���� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// ���� ��ư ����
		tvContractList.setEditable(false);
		btnContractDelete.setDisable(true);
		dpSearchDay.setValue(null);

		// ���̺� �� �÷�
		TableColumn colName = new TableColumn("�̸�");
		colName.setMaxWidth(70);
		colName.setMinWidth(70);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("contractor"));

		TableColumn colPhone = new TableColumn("��ȭ��ȣ");
		colPhone.setMaxWidth(100);
		colPhone.setMinWidth(100);
		colPhone.setStyle("-fx-allignment: CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("conPhone"));

		TableColumn colResidence = new TableColumn("�ּ�");
		colResidence.setMaxWidth(200);
		colResidence.setMinWidth(200);
		colResidence.setStyle("-fx-allignment: CENTER");
		colResidence.setCellValueFactory(new PropertyValueFactory<>("conResidence"));

		TableColumn colConName = new TableColumn("����");
		colConName.setMaxWidth(170);
		colConName.setMinWidth(170);
		colConName.setStyle("-fx-allignment: CENTER");
		colConName.setCellValueFactory(new PropertyValueFactory<>("ConName"));

		TableColumn colConDay = new TableColumn("��¥");
		colConDay.setMaxWidth(110);
		colConDay.setMinWidth(110);
		colConDay.setStyle("-fx-allignment: CENTER");
		colConDay.setCellValueFactory(new PropertyValueFactory<>("constructionDay"));

		tvContractList.setItems(mainData);
		tvContractList.getColumns().addAll(colName, colPhone, colResidence, colConName, colConDay);

		btnMonthlySales.setOnAction(event -> handlerBtnMonthlySalesAction(event)); // ���� ����� ��ư �̺�Ʈ
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // �˻� ��ư �̺�Ʈ
		btnContractAdd.setOnAction(event -> handlerBtnContractAddAction(event)); // ��� ��ư �̺�Ʈ
		btnContractDelete.setOnAction(event -> handlerBtnContractDelete(event)); // ���� ��ư �̺�Ʈ
		btnExit.setOnAction(event -> handlerBtnExitAction(event)); // ���� ��ư �̺�Ʈ
		tvContractList.setOnMouseClicked(event -> handleCheckAction(event)); // ���̺� �� ���� Ŭ�� �̺�Ʈ(Ȯ��,����â ���)

		// �̸��˻�â Ŭ�� �̺�Ʈ
		txtSearch.setOnMouseClicked(event -> {
			dpSearchDay.setValue(null);
		});

		// ��ü�����ư �̺�Ʈ
		btnTotalList.setOnAction(event -> {
			mainData.removeAll(mainData);
			totalList();
			dpSearchDay.setValue(null);
		});

		// ��ü ���� �ҷ�����
		totalList();
		tvContractList.setItems(mainData);
	}

	// ���̺�� ����Ŭ�� �̺�Ʈ �޼ҵ� (Ȯ��,����â)
	private void handleCheckAction(MouseEvent event) {
		if (event.getClickCount() != 2) {
			try {
				selectMain = tvContractList.getSelectionModel().getSelectedItems();
				no = selectMain.get(0).getNo();
				System.out.println(no);
				btnContractDelete.setDisable(false);

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����� ����");
				alert.setHeaderText("������ ����ڰ� �����ϴ�.");
				alert.setContentText("����ڸ� �߰��� �Ŀ� �����ϼ���.");
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
			mainMtage.setTitle("���� ����");
			mainMtage.setScene(scene);
			mainMtage.show();

			Stage oldStage = (Stage) btnContractAdd.getScene().getWindow();
			oldStage.close();

		} catch (Exception e) {
			System.out.println("��������" + e);
			e.printStackTrace();
		}
	}

	// ��������� ��ư ����Ʈ �̺�Ʈ �޼ҵ�
	private void handlerBtnMonthlySalesAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BarChart.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("���� �����");
			mainMtage.setScene(scene);
			mainMtage.show();

		} catch (Exception e) {
			System.out.println("���������" + e);
		}
	}

	// �˻� ��ư �̺�Ʈ �޼ҵ�
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

			// �ƹ� ���� ������
			if (searchDay.equals("") && searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("������� �˻�");
				alert.setHeaderText("������� �̸��� �Է��ϼ���.");
				alert.setContentText("�������ʹ� �ȹٷ� �Է��ϼ���.");
				alert.showAndWait();
			}
			// �̸����� �˻�
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

			// ��¥�� �˻�
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
							System.out.println("��������ͺ�����");
						}
					}
				}
			}

			if (!searchResult) {
				txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������� �˻�");
				alert.setHeaderText(searchName + "��������� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻� �ϼ���.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			System.out.println("�˻� ����");
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("������� �˻� ����");
			alert.setHeaderText("��� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �˻��ϼ���.");
			alert.showAndWait();
		}
	}

	// ��� ��ư �̺�Ʈ �޼ҵ�
	private void handlerBtnContractAddAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Write.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("���� ���");
			mainMtage.setScene(scene);
			mainMtage.show();

			Stage oldStage = (Stage) btnContractAdd.getScene().getWindow();
			oldStage.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// ���� ��ư �̺�Ʈ �޼ҵ�
	private void handlerBtnContractDelete(ActionEvent event) {
		DeleteDAO dDao = null;
		dDao = new DeleteDAO();
		try {
			dDao.getMainMatDelete(no);
			dDao.getMainWorkDelete(no);
			dDao.getMainDelete(no);

			// ���� ���ϻ��� ������Ʈ�ϱ�.
		} catch (Exception e) {
			System.out.println("���λ�����ư" + e);
		}
		mainData.removeAll(mainData);
		totalList();
		btnContractDelete.setDisable(true);
	}

	// ���� ��ư �̺�Ʈ �޼ҵ�
	private void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// ��ü ����Ʈ
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