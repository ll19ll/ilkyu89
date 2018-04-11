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

	// ����� ����
	@FXML
	private TextField txtMaterial; // �����
	@FXML
	private TextField txtMatCount; // ���� ����
	@FXML
	private TextField txtMatPay; // ���� ����
	@FXML
	private TextField txtMatStore; // ���� �Ǹ�ó
	@FXML
	private Button btnMatAdd; // ��Ϲ�ư
	@FXML
	private TableView<MaterialVO> tvMatList; // ���� ���
	@FXML
	private Button btnMatDelete; // ���� ���� ����
	@FXML
	private Text txtMatTotal; // ����� �Ѿ�

	int m_no; // ����� ������ ���� ����

	// ����� ����Ʈ
	ObservableList<MaterialVO> matData = FXCollections.observableArrayList();
	ObservableList<MaterialVO> matMain = null; // ���̺��� ������ ���� ����

	// �ΰǺ� ����
	@FXML
	private TextField txtWorkMan; // �η� �̸�
	@FXML
	private TextField txtPosition; // ��å
	@FXML
	private TextField txtWorkDay; // ��� �ϼ�
	@FXML
	private TextField txtWorkPay; // �ΰǺ�
	@FXML
	private Button btnWorkAdd; // ��Ϲ�ư
	@FXML
	private TableView<WorkManVO> tvWorkView; // �η� ���
	@FXML
	private Button btnWorkDelete; // �η� ���� ����

	@FXML
	private Text txtWorkTotal; // �ηº� �Ѿ�

	int w_no; // �ΰǺ� ������ ���� ����

	// �ΰǺ� ����Ʈ
	ObservableList<WorkManVO> workData = FXCollections.observableArrayList();
	ObservableList<WorkManVO> workMain = null; // ���̺��� ������ ���� ����

	// ÷������-----------------------------------------------------------------------

	@FXML
	private Button btnApp; // ���� ÷��
	@FXML
	private Button btnDocumentDelete; // ���� ����

	// ÷�� ����
	@FXML
	private TableView<ManagementVO> tvDocumentView; // ÷������ ���
	private ObservableList<ManagementVO> fileSaveName = FXCollections.observableArrayList();
	private ObservableList<ManagementVO> fileSN = null;
	private String folderName;

	// ������ ���� �� ������ �Ű������� ���� ��ü ����
	private String folder = null;
	private File dirSave;
	// ���� �� ���� ��ü ����
	File selectedFile = null;

	int no; // ������

	private Stage primaryStage;

	String selectFileName = ""; // �̹��� ���ϸ�
	String localUrl = ""; // �̹��� ���� ���

	// --------------------------------------------------------------------------------

	@FXML
	private Button btnSave; // ���� ����
	@FXML
	private Button btnExit; // ����â �ݱ�

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
			System.out.println("���ϻ�������");
		}

		// ����� ����
		tvMatList.setEditable(false);
		btnMatDelete.setDisable(true);

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

		tvMatList.setItems(matData);
		tvMatList.getColumns().addAll(colName, colCount, colPay, colStore);

		// ---------------------------------------------------------------//
		matData.removeAll(matData);
		totalMatList();

		btnMatAdd.setOnAction(event -> handleBtnMatAddAction(event)); // ��Ϲ�ư �̺�Ʈ
		btnMatDelete.setOnAction(event -> handleBtnMatDelete(event)); // ������ư �̺�Ʈ
		tvMatList.setOnMouseClicked(event -> handleMatDeleteAction(event)); // ���������� ���̺�� Ŭ�� �̺�Ʈ

		// ------------------------------------------------------------------------------------------------------------------//

		// �ΰǺ� ����
		tvWorkView.setEditable(false);
		btnWorkDelete.setDisable(true);

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

		tvWorkView.setItems(workData);
		tvWorkView.getColumns().addAll(colWork, colPosition, colDay, colWorkPay);

		// -------------------------------------------------------//

		workData.removeAll(workData);
		totalWorkList();

		btnWorkAdd.setOnAction(event -> handleBtnWorkAddAction(event)); // ��Ϲ�ư �̺�Ʈ
		btnWorkDelete.setOnAction(event -> handleBtnWorkDelete(event)); // ������ư �̺�Ʈ
		tvWorkView.setOnMouseClicked(event -> handleWorkDeleteAction(event)); // ���������� ���̺�� Ŭ�� �̺�Ʈ

		btnSave.setOnAction(event -> handleBtnSaveAction(event)); // �����ư �̺�Ʈ
		btnExit.setOnAction(event -> handleBtnExitAction(event)); // �ݱ��ư �̺�Ʈ

		// ÷������ ����
		// �ؽ�Ʈ�ʵ� ���¼���
		tvDocumentView.setEditable(false);

		// ��ư ���� ����
		btnDocumentDelete.setDisable(true);

		TableColumn colFileName = new TableColumn("÷������");
		colFileName.setMaxWidth(240);
		colFileName.setMinWidth(240);
		colFileName.setStyle("-fx-allignment: CENTER");
		colFileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
		tvDocumentView.setItems(fileSaveName);
		tvDocumentView.getColumns().addAll(colFileName);

		btnApp.setOnAction(event -> handleBtnAppAction(event)); // ÷��
		btnDocumentDelete.setOnAction(event -> handleBtnDocumentDeleteAction(event)); // ÷������ ����
		tvDocumentView.setOnMouseClicked(event -> handleFileNameAction(event)); // ���̺� ���콺 �׼�

		if (txtMatTotal.getText() == null) {
			txtMatTotal.setText("0");
		}
		if (txtWorkTotal.getText() == null) {
			txtWorkTotal.setText("0");
		}

	}

	// �����------------------------------------------------------------------------------

	// ���������� ���̺�� Ŭ�� �̺�Ʈ �޼ҵ�
	private void handleMatDeleteAction(MouseEvent event) {
		matMain = tvMatList.getSelectionModel().getSelectedItems();
		m_no = matMain.get(0).getM_no();
		System.out.println(m_no);
		btnMatDelete.setDisable(false);
	}

	// ��Ϲ�ư �̺�Ʈ �޼ҵ�(�ذ�)
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
					alert.setTitle("���� �Է�");
					alert.setHeaderText("���������� �߰��Ǿ����ϴ�.");
					alert.setContentText("���� ���� ������ �Է��ϼ���");
					alert.showAndWait();

					// ���̺� �ȿ� �ִ� ����� �Ѿ� �ڵ� ���
					ViewDAO vmDao = new ViewDAO();
					try {
						txtMatTotal
								.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo.getText())).getMatTotalPay());
					} catch (Exception e) {
						System.out.println("������Ѿ�" + e);
					}

					matData.removeAll(matData);
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
			System.out.println(m_no);
			dDao.getMaterialDelete(m_no);
			matData.removeAll(matData);

			// ��ü ����
			totalMatList();
			btnMatDelete.setDisable(true);

			// ���̺� �ȿ� �ִ� ����� �Ѿ� �ڵ� ���
			ViewDAO vmDao = new ViewDAO();
			try {
				txtMatTotal.setText(vmDao.getMaterialPayTotal(Integer.parseInt(txtNo.getText())).getMatTotalPay());
			} catch (Exception e) {
				System.out.println("������Ѿ�" + e);
			}

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

		list = vDao.getMaterialTotal(Integer.parseInt(txtNo.getText()));
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			matVo = list.get(index);
			matData.add(matVo);
		}
	}

	// �ΰǺ�------------------------------------------------------------------------------
	// ���������� ���̺�� Ŭ�� �̺�Ʈ
	private void handleWorkDeleteAction(MouseEvent event) {
		System.out.println(12);
		workMain = tvWorkView.getSelectionModel().getSelectedItems();
		w_no = workMain.get(0).getW_no();
		System.out.println(w_no+"�ΰǺ�");
		btnWorkDelete.setDisable(false);
	}

	// �η� ��Ϲ�ư �̺�Ʈ �޼ҵ�
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
					alert.setTitle("���� �Է�");
					alert.setHeaderText("�η������� �߰��Ǿ����ϴ�.");
					alert.setContentText("���� �η� ������ �Է��ϼ���");
					alert.showAndWait();

					// ���̺� �ȿ� �ִ� �ΰǺ� �Ѿ� �ڵ� ���
					ViewDAO vwDao = new ViewDAO();
					try {
						txtWorkTotal
								.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo.getText())).getWorkTotalPay());
					} catch (Exception e) {
						System.out.println("�ΰǺ��Ѿ�" + e);
					}

					workData.removeAll(workData);
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
			dDao.getWorkManDelete(w_no);
			System.out.println(w_no);
			workData.removeAll(workData);
			totalWorkList();
			btnWorkDelete.setDisable(true);

			// ���̺� �ȿ� �ִ� �ΰǺ� �Ѿ� �ڵ� ���
			ViewDAO vwDao = new ViewDAO();
			try {
				txtWorkTotal.setText(vwDao.getWorkManPayTotal(Integer.parseInt(txtNo.getText())).getWorkTotalPay());
				btnWorkDelete.setDisable(true);
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

		list = vDao.getWorkManTotal(Integer.parseInt(txtNo.getText()));
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			workVo = list.get(index);
			workData.add(workVo);
		}
	}

	// ÷������-------------------------------------------------------------------------
	// ���̺�Ʒ��� Ŭ���̺�Ʈ
	private void handleFileNameAction(MouseEvent event) {
		if (event.getClickCount() != 2) {
			try {
				fileSN = tvDocumentView.getSelectionModel().getSelectedItems();
				selectFileName = fileSN.get(0).getFileName();
				btnDocumentDelete.setDisable(false);
				System.out.println("÷��Ŭ��" + selectFileName);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ����");
				alert.setHeaderText("������ ������ �����ϴ�.");
				alert.setContentText("������ �߰��� �Ŀ� �����ϼ���.");
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
			System.out.println("���Ͽ���: " + e);
		}
	}

	// ÷�� ���� ����
	private void handleBtnDocumentDeleteAction(ActionEvent event) {

		try {
			DeleteDAO dDao = new DeleteDAO();
			dDao.getFileDelete(Integer.parseInt(txtNo.getText()));
			fileSaveName.removeAll(fileSN);
			fileDelete(selectedFile);

			btnDocumentDelete.setDisable(true);

		} catch (Exception e) {
			System.out.println("÷�����ϻ���" + e);
			System.out.println(selectedFile);
		}
	}

	// ���� ���� �޼ҵ�
	public boolean fileDelete(File fileName) {
		boolean result = false;
		try {
			File fileDelete = new File(dirSave.getCanonicalPath() + "\\" + fileName); // ���� �̹��� ����
			if (fileDelete.exists() && fileDelete.isFile()) {
				result = fileDelete.delete();
			}
		} catch (Exception ie) {
			System.out.println("���ϻ��� " + ie.getMessage());
			result = false;
		}
		return result;
	}

	// ÷�ι�ư
	private void handleBtnAppAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("file", "*"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// ���� ���
				localUrl = selectedFile.toURI().toURL().toString();
				String fileName = fileSave(selectedFile);
				ManagementVO mVo = new ManagementVO(fileName);
				fileSaveName.addAll(mVo);

				try {

				} catch (Exception e) {
					System.out.println("��������" + e);
				}
			}
			if (tvDocumentView.getInsets() != null) {
				btnDocumentDelete.setDisable(false);
			}
		} catch (MalformedURLException e) {
			System.out.println("÷�ι�ư" + e);

			if (selectedFile != null) {
				selectFileName = selectedFile.getName();
			}
		}
	}

	/*
	 * fileSave() ���� ���� �޼ҵ�
	 * 
	 * @param(�о� �� ���� ��ü)
	 */

	public String fileSave(File file) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// ���ϸ� ����
			fileName = "����ENG_" + System.currentTimeMillis() + "_" + file.getName();
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getCanonicalPath() + "\\" + fileName));

			// ������ �̹��� ���� InputStream�� �������� �̸����� ���� -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			System.out.println("���ϸ����" + e);
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				System.out.println("�̹������ϸ����2" + e);
			}
		}
		return fileName;
	}

	// ------------------------------------------------------------------------------

	// ���� ��ư �̺�Ʈ �޼ҵ�
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
					// ����,���ϳ��� ������Ʈ

					// ����� ������
					String conPay = sDao.getConPay(Integer.parseInt(txtNo.getText())).getConstructionPay();

					System.out.println(conPay);
					// ���� ���
					int magin = Integer.parseInt(conPay)
							- (Integer.parseInt(txtMatTotal.getText()) + Integer.parseInt(txtWorkTotal.getText()));
					folder = sDao.getFolder(Integer.parseInt(txtNo.getText())).getContractor();
					System.out.println(magin + " , " + folder);

					// ������ ���̽��� ���� �̸��� ���� ���
					mDao.getMaginFileUpdate(Integer.parseInt(txtNo.getText()), magin + "", folder);

					Stage oldStage = (Stage) btnSave.getScene().getWindow();
					oldStage.close();

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainList.fxml"));
					Parent mainView = (Parent) loader.load();
					Scene scene = new Scene(mainView);
					Stage mainMtage = new Stage();
					mainMtage.setTitle("���� ENG");
					mainMtage.setScene(scene);
					mainMtage.show();

				} catch (Exception e) {
					System.out.println("����,����" + e);
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
		Stage oldStage = (Stage) btnSave.getScene().getWindow();
		oldStage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainList.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("���� ���");
			mainMtage.setScene(scene);
			mainMtage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// �ѹ�
	public void no() {
		try {
			ArrayList<ManagementVO> list = new ArrayList<>();
			SearchDAO sDao = new SearchDAO();
			list = sDao.getNumber();
			int number = list.get(0).getNo();
			txtNo.setText(number + "");
		} catch (Exception e) {
			System.out.println("�ѹ�" + e);
		}
	}

	// ���� �̸�
	public void folderName() {
		try {
			SearchDAO sDao = new SearchDAO();
			folder = sDao.getFolder(Integer.parseInt(txtNo.getText())).getContractor();

			System.out.println(folder);
		} catch (Exception e) {
			System.out.println("�����̸�" + e);
		}
	}
}
