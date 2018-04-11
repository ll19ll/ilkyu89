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

	// ����� ����
	@FXML
	private TextField txtContractor_e; // ����� �̸�
	@FXML
	private TextField txtConPhone_e; // ����� ����ó
	@FXML
	private TextField txtConBirth_e; // ����� �������
	@FXML
	private TextField txtConResidence_e; // ����� �ּ�
	@FXML
	private TextField txtConName_e; // ����
	@FXML
	private ComboBox<String> cbMeter1_e; // �跮�� ��� �޺��ڽ�
	@FXML
	private ComboBox<String> cbMeter2_e;
	@FXML
	private ComboBox<String> cbMeter3_e;
	@FXML
	private TextField txtMeter1_e; // �跮�� ����
	@FXML
	private TextField txtMeter2_e;
	@FXML
	private TextField txtMeter3_e;
	@FXML
	private ComboBox<String> cbConType_e; // �ǹ�����
	@FXML
	private ComboBox<String> cbConUse_e; // �뵵
	@FXML
	private TextField txtConDay_e; // ���� ��¥
	@FXML
	private TextField txtConPay_e; // ���� �ݾ�

	// �η� ����
	@FXML
	private TextField txtDerector_e; // ���簨�� �̸�
	@FXML
	private TextField txtDirPhone_e; // ���簨�� ����ó
	@FXML
	private TextField txtDirDepartment_e; // ���簨�� �Ҽ�
	@FXML
	private TextField txtManager_e; // �ð������� �̸�
	@FXML
	private TextField txtManPhone_e; // �ð������� ����ó
	@FXML
	private TextField txtManDepartment_e; // �ð������� �Ҽ�
	@FXML
	private TextField txtLeader_e; // ������� �̸�
	@FXML
	private TextField txtLdPhone_e; // ������� ����ó
	@FXML
	private TextField txtLdDepartment_e; // ������� �Ҽ�

	// �������
	// ����
	@FXML
	private TextField txtMatPay_e; // �����
	@FXML
	private Button btnPay_e; // �����,�ΰǺ� ��ư
	@FXML
	private TextField txtWmPay_e; // �ΰǺ�
	@FXML
	private TextField txtTotal_e; // �Ѻ��
	@FXML
	private TextField txtMagin_e; // ����

	// ÷�� ����
	@FXML
	private TextField txtDocument_e; // ÷������ ���
	@FXML
	private Button btnApp_e; // ���� ÷��
	@FXML
	private Button btnOpenDocument_e; // ���� ����

	@FXML
	private Button btnInfoAdd_e; // �������
	@FXML
	private Button btnInfoExit_e; // ���� â �ݱ�
	@FXML
	private Button btnInfoModified; // ����

	int no; // ������

	// ������ ���� �� ������ �Ű������� ���� ��ü ����
	private File dirSave;
	// ������ ���� �� ���� ��ü ����
	File selectedFile = null;

	ObservableList<ManagementVO> mainData = FXCollections.observableArrayList();
	ObservableList<ManagementVO> selectMain = null; // ���̺��� ������ ���� ����

	private Stage primaryStage;
	String fileSaveName = "";
	String selectFileName = ""; // �̹��� ���ϸ�
	String localUrl = ""; // �̹��� ���� ���

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
			System.out.println("���ϻ�������");
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

		// �η� ����
		txtDerector_e.setDisable(true);
		txtDirPhone_e.setDisable(true);
		txtDirDepartment_e.setDisable(true);
		txtManager_e.setDisable(true);
		txtManPhone_e.setDisable(true);
		txtManDepartment_e.setDisable(true);
		txtLeader_e.setDisable(true);
		txtLdPhone_e.setDisable(true);
		txtLdDepartment_e.setDisable(true);
		// �������
		txtMatPay_e.setDisable(true);
		txtWmPay_e.setDisable(true);
		txtTotal_e.setDisable(true);
		txtMagin_e.setDisable(true);
		txtDocument_e.setEditable(false);

		// �޺��� ����
		cbMeter1_e.setItems(FXCollections.observableArrayList("G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter2_e.setItems(FXCollections.observableArrayList("G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter3_e.setItems(FXCollections.observableArrayList("G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbConType_e.setItems(FXCollections.observableArrayList("����", "����Ʈ", "��", "�����ü�"));
		cbConUse_e.setItems(FXCollections.observableArrayList("���ÿ�(���)", "�Ϲݿ�(����)", "�����"));

		infoSet();
		// ������ư
		btnInfoModified.setOnAction(event -> handleEditAction(event));
		// ��� ��ư
		btnInfoAdd_e.setOnAction(event -> handleInfoAddAction(event));
		// ÷�� ��ư
		btnApp_e.setOnAction(event -> handleAppAction(event));

		// Ȯ�� ��ư
		btnInfoExit_e.setOnAction(event -> {
			Stage oldStage = (Stage) btnInfoExit_e.getScene().getWindow();
			oldStage.close();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainList.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scene = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("���� ENG");
				mainMtage.setScene(scene);
				mainMtage.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
		});

		// ����,�ΰǺ� ��ư
		btnPay_e.setOnAction(event -> {
			try {
				ViewDAO vDao = new ViewDAO();
				EditMnWVO mnwVo = vDao.getEditMnWTotal(Connect.management.getNo());
				Connect.mnw = mnwVo;

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditPay.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scene = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("���� ����");
				mainMtage.setScene(scene);
				mainMtage.show();

				Stage oldStage = (Stage) btnInfoExit_e.getScene().getWindow();
				oldStage.close();

			} catch (Exception e) {
				System.out.println("����,�ΰǺ��ư" + e);
			}
		});
		// ���� ���� ��ư
		btnOpenDocument_e.setOnAction(event -> {
			try {
				folder = sDao.getFolder(Connect.management.getNo()).getContractor();
				Runtime open = Runtime.getRuntime();
				open.exec("explorer E:\\javaFX\\ConstructionManagement\\file\\" + folder);
			} catch (Exception e) {
				System.out.println("���Ͽ���: " + e);
			}
		});

	}

	// ��� ��ư �̺�Ʈ
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

				// �η� ����
				txtDerector_e.setDisable(true);
				txtDirPhone_e.setDisable(true);
				txtDirDepartment_e.setDisable(true);
				txtManager_e.setDisable(true);
				txtManPhone_e.setDisable(true);
				txtManDepartment_e.setDisable(true);
				txtLeader_e.setDisable(true);
				txtLdPhone_e.setDisable(true);
				txtLdDepartment_e.setDisable(true);
				// �������
				txtMatPay_e.setDisable(true);
				txtWmPay_e.setDisable(true);
				txtTotal_e.setDisable(true);
				txtMagin_e.setDisable(true);
				txtDocument_e.setEditable(false);

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("���� �Է�");
					alert.setHeaderText(txtContractor_e.getText() + "�� ���������� �����Ǿ����ϴ�.");
					alert.setContentText("���� ���� ������ �Է��ϼ���");
					alert.showAndWait();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText(txtContractor_e.getText() + "�� ���������� ���������ʾҽ��ϴ�.");
			alert.setContentText("�ٽ� �Է��ϼ���");
			alert.showAndWait();
		}
	};

	// ÷�� ��ư �̺�Ʈ
	private void handleAppAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("file", "*"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);

			if (selectedFile != null) {
				// ���� ���
				localUrl = selectedFile.toURI().toURL().toString();
				fileSave(selectedFile);
				try {

				} catch (Exception e) {
					System.out.println("��������" + e);
				}
			}
		} catch (MalformedURLException e) {
			System.out.println("÷�ι�ư" + e);

			if (selectedFile != null) {
				selectFileName = selectedFile.getName();
			}
		}
	}

	// �������� �޼ҵ�
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

	// ���� ��ư �̺�Ʈ
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
		txtContractor_e.setText(Connect.management.getContractor()); // ����� �̸�
		txtConPhone_e.setText(Connect.management.getConPhone()); // ����� ����ó
		txtConBirth_e.setText(Connect.management.getConBirth()); // ����� �������
		txtConResidence_e.setText(Connect.management.getConResidence()); // ����� �ּ�
		txtConName_e.setText(Connect.management.getConName()); // ����
		cbMeter1_e.setValue(Connect.management.getMeterType1()); // �跮�� ��� �޺��ڽ�
		cbMeter2_e.setValue(Connect.management.getMeterType2());
		cbMeter3_e.setValue(Connect.management.getMeterType3());
		txtMeter1_e.setText(Connect.management.getMeterCount1()); // �跮�� ����
		txtMeter2_e.setText(Connect.management.getMeterCount2());
		txtMeter3_e.setText(Connect.management.getMeterCount3());
		cbConType_e.setValue(Connect.management.getBuildingType()); // �ǹ�����
		cbConUse_e.setValue(Connect.management.getUse()); // �뵵
		txtConDay_e.setText(Connect.management.getConstructionDay()); // ���� ��¥
		txtConPay_e.setText(Connect.management.getConstructionPay()); // ���� �ݾ�

		// �η� ����
		txtDerector_e.setText(Connect.management.getDirector()); // ���簨�� �̸�
		txtDirPhone_e.setText(Connect.management.getDirPhone()); // ���簨�� ����ó
		txtDirDepartment_e.setText(Connect.management.getDirDepartment()); // ���簨�� �Ҽ�
		txtManager_e.setText(Connect.management.getManager()); // �ð������� �̸�
		txtManPhone_e.setText(Connect.management.getManPhone()); // �ð������� ����ó
		txtManDepartment_e.setText(Connect.management.getManDepartment()); // �ð������� �Ҽ�
		txtLeader_e.setText(Connect.management.getLeader()); // ������� �̸�
		txtLdPhone_e.setText(Connect.management.getLdPhone()); // ������� ����ó
		txtLdDepartment_e.setText(Connect.management.getLdDepartment()); // ������� �Ҽ�

		// �������
		try {
			ViewDAO vmDao = new ViewDAO();
			String matTotal = vmDao.MatPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotal();
			String workTotal = vmDao.WorkPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotal();
			txtMatPay_e.setText("0");
			txtWmPay_e.setText("0");

			txtMatPay_e.setText(vmDao.MatPayTotal(Integer.parseInt(txtNo_e.getText())).getMatTotal()); // �����
			txtWmPay_e.setText(vmDao.WorkPayTotal(Integer.parseInt(txtNo_e.getText())).getWorkTotal()); // �ΰǺ�

		} catch (Exception e) {
			System.out.println("�����ΰ� �Ѻ��: " + e);
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
			txtTotal_e.setText(totalPay + ""); // �Ѻ��

			int magin = Integer.parseInt(txtConPay_e.getText()) - totalPay;
			txtMagin_e.setText(magin + ""); // ����

			txtDocument_e.setText(txtContractor_e.getText() + "������ ��������� �ֽ��ϴ�.");
		} catch (Exception e) {
			System.out.println("�Ѻ��,�������");
			e.printStackTrace();
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
