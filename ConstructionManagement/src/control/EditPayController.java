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

	// ����� ����
	@FXML
	private TextField txtMaterial_e; // �����
	@FXML
	private TextField txtMatCount_e; // ���� ����
	@FXML
	private TextField txtMatPay_e; // ���� ����
	@FXML
	private TextField txtMatStore_e; // ���� �Ǹ�ó
	@FXML
	private Button btnMatAdd_e; // ��Ϲ�ư
	@FXML
	private TableView<MaterialVO> tvMatList_e; // ���� ���
	@FXML
	private Button btnMatDelete_e; // ���� ���� ����
	@FXML
	private Text txtMatTotal_e; // ����� �Ѿ�

	int m_no_e; // ����� ������ ���� ����

	// ����� ����Ʈ
	ObservableList<MaterialVO> matData_e = FXCollections.observableArrayList();
	ObservableList<MaterialVO> matMain_e = null; // ���̺��� ������ ���� ����

	// �ΰǺ� ����
	@FXML
	private TextField txtWorkMan_e; // �η� �̸�
	@FXML
	private TextField txtPosition_e; // ��å
	@FXML
	private TextField txtWorkDay_e; // ��� �ϼ�
	@FXML
	private TextField txtWorkPay_e; // �ΰǺ�
	@FXML
	private Button btnWorkAdd_e; // ��Ϲ�ư
	@FXML
	private TableView<WorkManVO> tvWorkView_e; // �η� ���
	@FXML
	private Button btnWorkDelete_e; // �η� ���� ����

	@FXML
	private Text txtWorkTotal_e; // �ηº� �Ѿ�

	int w_no_e; // �ΰǺ� ������ ���� ����

	// �ΰǺ� ����Ʈ
	ObservableList<WorkManVO> workData_e = FXCollections.observableArrayList();
	ObservableList<WorkManVO> workMain_e = null; // ���̺��� ������ ���� ����

	// --------------------------------------------------------------------------
	@FXML
	private Button btnSave_e; // ���� ����
	@FXML
	private Button btnExit_e; // ����â �ݱ�

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		no();

		// ����� ����
		tvMatList_e.setEditable(false);
		btnMatDelete_e.setDisable(true);

		TableColumn colName = new TableColumn("�����");
		colName.setMaxWidth(70);
		colName.setMinWidth(70);
		colName.setStyle("-fx-allignment: CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("material"));

		TableColumn colCount = new TableColumn("����");
		colCount.setMaxWidth(70);
		colCount.setMinWidth(70);
		colCount.setStyle("-fx-allignment: CENTER");
		colCount.setCellValueFactory(new PropertyValueFactory<>("matCount"));

		TableColumn colPay = new TableColumn("����");
		colPay.setMaxWidth(70);
		colPay.setMinWidth(70);
		colPay.setStyle("-fx-allignment: CENTER");
		colPay.setCellValueFactory(new PropertyValueFactory<>("matTotal"));

		TableColumn colStore = new TableColumn("�Ǹ�ó");
		colStore.setMaxWidth(70);
		colStore.setMinWidth(70);
		colStore.setStyle("-fx-allignment: CENTER");
		colStore.setCellValueFactory(new PropertyValueFactory<>("matStore"));

		tvMatList_e.setItems(matData_e);
		tvMatList_e.getColumns().addAll(colName, colCount, colPay, colStore);

		// ---------------------------------------------------------------//
		matData_e.removeAll(matData_e);
		totalMatList();

		// ���̺� �ȿ� �ִ� ����� �Ѿ� �ڵ� ���
		ViewDAO vmDao = new ViewDAO();
		try {
			txtMatTotal_e.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotalPay());
		} catch (Exception e) {
			System.out.println("������Ѿ�" + e);
		}

		btnMatAdd_e.setOnAction(event -> handleBtnMatAddAction(event)); // ��Ϲ�ư �̺�Ʈ
		btnMatDelete_e.setOnAction(event -> handleBtnMatDelete(event)); // ������ư �̺�Ʈ
		tvMatList_e.setOnMouseClicked(event -> handleMatDeleteAction(event)); // ���������� ���̺�� Ŭ�� �̺�Ʈ

		// ------------------------------------------------------------------------------------------------------------------//

		// �ΰǺ� ����
		tvWorkView_e.setEditable(false);
		btnWorkDelete_e.setDisable(true);

		// ���̺� �� �÷�
		TableColumn colWork = new TableColumn("�̸�");
		colWork.setMaxWidth(70);
		colWork.setMinWidth(70);
		colWork.setStyle("-fx-allignment: CENTER");
		colWork.setCellValueFactory(new PropertyValueFactory<>("workMan"));

		TableColumn colPosition = new TableColumn("��å");
		colPosition.setMaxWidth(70);
		colPosition.setMinWidth(70);
		colPosition.setStyle("-fx-allignment: CENTER");
		colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

		TableColumn colDay = new TableColumn("����ϼ�");
		colDay.setMaxWidth(70);
		colDay.setMinWidth(70);
		colDay.setStyle("-fx-allignment: CENTER");
		colDay.setCellValueFactory(new PropertyValueFactory<>("workDay"));

		TableColumn colWorkPay = new TableColumn("�ΰǺ�");
		colWorkPay.setMaxWidth(70);
		colWorkPay.setMinWidth(70);
		colWorkPay.setStyle("-fx-allignment: CENTER");
		colWorkPay.setCellValueFactory(new PropertyValueFactory<>("workPay"));

		tvWorkView_e.setItems(workData_e);
		tvWorkView_e.getColumns().addAll(colWork, colPosition, colDay, colWorkPay);

		// -------------------------------------------------------//

		workData_e.removeAll(workData_e);
		totalWorkList();

		// ���̺� �ȿ� �ִ� �ΰǺ� �Ѿ� �ڵ� ���
		ViewDAO vwDao = new ViewDAO();
		try {
			txtWorkTotal_e.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotalPay());
		} catch (Exception e) {
			System.out.println("�ΰǺ��Ѿ�" + e);
		}

		btnWorkAdd_e.setOnAction(event -> handleBtnWorkAddAction(event)); // ��Ϲ�ư �̺�Ʈ
		btnWorkDelete_e.setOnAction(event -> handleBtnWorkDelete(event)); // ������ư �̺�Ʈ
		tvWorkView_e.setOnMouseClicked(event -> handleWorkDeleteAction(event)); // ���������� ���̺�� Ŭ�� �̺�Ʈ

		btnSave_e.setOnAction(event -> handleBtnSaveAction(event)); // �����ư �̺�Ʈ
		btnExit_e.setOnAction(event -> handleBtnExitAction(event)); // �ݱ��ư �̺�Ʈ

		if (txtMatTotal_e.getText() == null) {
			txtMatTotal_e.setText("0");
		}
		if (txtWorkTotal_e.getText() == null) {
			txtWorkTotal_e.setText("0");
		}
	}
	// �����------------------------------------------------------------------------------

	// ���������� ���̺�� Ŭ�� �̺�Ʈ �޼ҵ�
	private void handleMatDeleteAction(MouseEvent event) {
		matMain_e = tvMatList_e.getSelectionModel().getSelectedItems();
		m_no_e = matMain_e.get(0).getM_no();
		System.out.println(m_no_e);
		btnMatDelete_e.setDisable(false);
	}

	// ��Ϲ�ư �̺�Ʈ �޼ҵ�(�ذ�)
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
					alert.setTitle("���� �Է�");
					alert.setHeaderText("���������� �߰��Ǿ����ϴ�.");
					alert.setContentText("���� ���� ������ �Է��ϼ���");
					alert.showAndWait();

					// ���̺� �ȿ� �ִ� ����� �Ѿ� �ڵ� ���
					ViewDAO vmDao = new ViewDAO();
					try {
						txtMatTotal_e.setText(
								vmDao.getMaterialPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotalPay());
					} catch (Exception e) {
						System.out.println("������Ѿ�" + e);
					}

					matData_e.removeAll(matData_e);
					totalMatList();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText("���������� �߰������ʾҽ��ϴ�.");
			alert.setContentText("�ٽ� �Է��ϼ���.");
			alert.showAndWait();
		}

	};

	// ������ư �̺�Ʈ �޼ҵ�
	private void handleBtnMatDelete(ActionEvent event) {
		DeleteDAO dDao = null;
		dDao = new DeleteDAO();

		try {
			System.out.println(m_no_e);
			dDao.getMaterialDelete(m_no_e);
			matData_e.removeAll(matData_e);
			btnMatDelete_e.setDisable(true);

			// ��ü ����
			totalMatList();

			// ���̺� �ȿ� �ִ� ����� �Ѿ� �ڵ� ���
			ViewDAO vmDao = new ViewDAO();
			try {
				txtMatTotal_e.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotalPay());
			} catch (Exception e) {
				System.out.println("������Ѿ�" + e);
			}

			btnMatDelete_e.setDisable(true);

		} catch (Exception e) {
			System.out.println("���������ư" + e);
		}
	}

	// ��ü ����Ʈ
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

	// �ΰǺ�------------------------------------------------------------------------------
	// ���������� ���̺�� Ŭ�� �̺�Ʈ
	private void handleWorkDeleteAction(MouseEvent event) {
		workMain_e = tvWorkView_e.getSelectionModel().getSelectedItems();
		w_no_e = workMain_e.get(0).getW_no();
		System.out.println(w_no_e);
		btnWorkDelete_e.setDisable(false);
	}

	// �η� ��Ϲ�ư �̺�Ʈ �޼ҵ�
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
					alert.setTitle("���� �Է�");
					alert.setHeaderText("�η������� �߰��Ǿ����ϴ�.");
					alert.setContentText("���� �η� ������ �Է��ϼ���");
					alert.showAndWait();

					// ���̺� �ȿ� �ִ� �ΰǺ� �Ѿ� �ڵ� ���
					ViewDAO vwDao = new ViewDAO();
					try {
						txtWorkTotal_e.setText(
								vwDao.getWorkManPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotalPay());
					} catch (Exception e) {
						System.out.println("�ΰǺ��Ѿ�" + e);
					}

					workData_e.removeAll(workData_e);
					totalWorkList();
				}

			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText("�η������� �߰������ʾҽ��ϴ�.");
			alert.setContentText("�ٽ� �Է��ϼ���.");
			alert.showAndWait();
		}

	};

	// ���� ��ư �̺�Ʈ �޼ҵ�
	private void handleBtnWorkDelete(ActionEvent event) {
		DeleteDAO dDao = null;
		dDao = new DeleteDAO();
		try {
			dDao.getWorkManDelete(w_no_e);
			workData_e.removeAll(workData_e);
			btnWorkDelete_e.setDisable(true);
			// ��ü ����
			totalWorkList();

			// ���̺� �ȿ� �ִ� �ΰǺ� �Ѿ� �ڵ� ���
			ViewDAO vwDao = new ViewDAO();
			try {
				txtWorkTotal_e.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotalPay());
			} catch (Exception e) {
				System.out.println("�ΰǺ��Ѿ�" + e);
			}

		} catch (Exception e) {
			System.out.println("�η»�����ư" + e);
		}
	}

	// ��ü ����Ʈ
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

	// ���� ��ư �̺�Ʈ �޼ҵ�
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

					// ���� ������Ʈ

					// ����� ������
					String conPay = sDao.getConPay(Integer.parseInt(txtNo_e.getText())).getConstructionPay();
					System.out.println(conPay);

					// ���� ���
					int magin = Integer.parseInt(conPay)
							- (Integer.parseInt(txtMatTotal_e.getText()) + Integer.parseInt(txtWorkTotal_e.getText()));

					// ������ ���̽��� ���� ���
					mDao.getMaginUpdate(Integer.parseInt(txtNo_e.getText()), magin + "");

					Stage oldStage = (Stage) btnSave_e.getScene().getWindow();
					oldStage.close();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Check.fxml"));
					Parent mainView = (Parent) loader.load();
					Scene scene = new Scene(mainView);
					Stage mainMtage = new Stage();
					mainMtage.setTitle("���� ����");
					mainMtage.setScene(scene);
					mainMtage.show();

				} catch (Exception e) {
					System.out.println("����: " + e);
					e.printStackTrace();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText("���������� �߰������ʾҽ��ϴ�.");
			alert.setContentText("�ٽ� �Է��ϼ���.");
			alert.showAndWait();
			
			
		}
	}

	// �ݱ��ư �̺�Ʈ �޼ҵ�
	private void handleBtnExitAction(ActionEvent event) {

		Stage oldStage = (Stage) btnSave_e.getScene().getWindow();
		oldStage.close();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Check.fxml"));
		Parent mainView = (Parent) loader.load();
		Scene scene = new Scene(mainView);
		Stage mainMtage = new Stage();
		mainMtage.setTitle("���� ����");
		mainMtage.setScene(scene);
		mainMtage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// �ѹ�
	public void no() {
		try {
			int number = Connect.management.getNo();
			txtNo_e.setText(number + "");
		} catch (Exception e) {
			System.out.println("�ѹ�" + e);
		}
	}
}
