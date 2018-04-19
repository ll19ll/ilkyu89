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
	// ���â

	// ����� ����
	@FXML
	private Button btnContractReset; // ����� �ʱ�ȭ
	@FXML
	private TextField txtContractor; // ����� �̸�
	@FXML
	private TextField txtConPhone; // ����� ����ó
	@FXML
	private TextField txtConBirth; // ����� �������
	@FXML
	private TextField txtConResidence; // ����� �ּ�
	@FXML
	private TextField txtConName; // ����
	@FXML
	private ComboBox<String> cbMeter1; // �跮�� ��� �޺��ڽ�
	@FXML
	private ComboBox<String> cbMeter2;
	@FXML
	private ComboBox<String> cbMeter3;
	@FXML
	private TextField txtMeter1; // �跮�� ����
	@FXML
	private TextField txtMeter2;
	@FXML
	private TextField txtMeter3;
	@FXML
	private ComboBox<String> cbConType; // �ǹ�����
	@FXML
	private ComboBox<String> cbConUse; // �뵵
	@FXML
	private DatePicker dpConDay; // ���� ��¥
	@FXML
	private TextField txtConPay; // ���� �ݾ�

	// �η� ����
	@FXML
	private Button btnManPowerReset; // �η� ���� �ʱ�ȭ
	@FXML
	private TextField txtDerector; // ���簨�� �̸�
	@FXML
	private TextField txtDirPhone; // ���簨�� ����ó
	@FXML
	private TextField txtDirDepartment; // ���簨�� �Ҽ�
	@FXML
	private TextField txtManager; // �ð������� �̸�
	@FXML
	private TextField txtManPhone; // �ð������� ����ó
	@FXML
	private TextField txtManDepartment; // �ð������� �Ҽ�
	@FXML
	private TextField txtLeader; // ������� �̸�
	@FXML
	private TextField txtLdPhone; // ������� ����ó
	@FXML
	private TextField txtLdDepartment; // ������� �Ҽ�

	@FXML
	private Button btnInfoAdd; // �������
	@FXML
	private Button btnInfoExit; // ���� â �ݱ�

	ObservableList<ManagementVO> mainData = FXCollections.observableArrayList();
	ObservableList<ManagementVO> selectMain = null; // ���̺��� ������ ���� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// ��������Ŀ ���¼���
		dpConDay.setValue(LocalDate.now());

		// �޺��� ����
		cbMeter1.setItems(FXCollections.observableArrayList(null,"G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter2.setItems(FXCollections.observableArrayList(null,"G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbMeter3.setItems(FXCollections.observableArrayList(null,"G1.6", "G2.5", "G4", "G6", "G10", "G16", "G25"));
		cbConType.setItems(FXCollections.observableArrayList(null,"����", "����Ʈ", "��", "�����ü�"));
		cbConUse.setItems(FXCollections.observableArrayList(null,"���ÿ�(���)", "�Ϲݿ�(����)", "�����"));

		btnInfoAdd.setOnAction(event -> handleBtnInfoAddAction(event)); // ���
		btnInfoExit.setOnAction(event -> handleBtnInfoExitAction(event)); // ���

		//����� �ʱ�ȭ��ư
		btnContractReset.setOnAction(event ->{
			txtContractor.clear(); // ����� �̸�
			txtConPhone.clear(); // ����� ����ó
			txtConBirth.clear(); // ����� �������
			txtConResidence.clear(); // ����� �ּ�
			txtConName.clear(); // ����
			cbMeter1.getSelectionModel().clearSelection(); // �跮�� ��� �޺��ڽ�
			cbMeter2.getSelectionModel().clearSelection();
			cbMeter3.getSelectionModel().clearSelection();
			txtMeter1.clear(); // �跮�� ����
			txtMeter2.clear();
			txtMeter3.clear();
			cbConType.getSelectionModel().clearSelection(); // �ǹ�����
			cbConUse.getSelectionModel().clearSelection();// �뵵
			txtConPay.clear(); // ���� �ݾ�
		});
		// ���� �߽� �η� �ʱ�ȭ��ư
		btnManPowerReset.setOnAction(event ->{
			txtDerector.clear(); // ���簨�� �̸�
			txtDirPhone.clear(); // ���簨�� ����ó
			txtDirDepartment.clear(); // ���簨�� �Ҽ�
			txtManager.clear(); // �ð������� �̸�
			txtManPhone.clear(); // �ð������� ����ó
			txtManDepartment.clear(); // �ð������� �Ҽ�
			txtLeader.clear(); // ������� �̸�
			txtLdPhone.clear(); // ������� ����ó
			txtLdDepartment.clear(); // ������� �Ҽ�
		});
	}

	// ��ҹ�ư
	private void handleBtnInfoExitAction(ActionEvent event) {
		Stage oldStage = (Stage) btnInfoAdd.getScene().getWindow();
		oldStage.close();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainList.fxml"));
		Parent mainView = (Parent) loader.load();
		Scene scene = new Scene(mainView);
		Stage mainMtage = new Stage();
		mainMtage.setTitle("���� ENG");
		mainMtage.setScene(scene);
		mainMtage.show();
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
	// ��Ϲ�ư
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
				mainMtage.setTitle("���� ���");
				mainMtage.setScene(scene);
				mainMtage.show();
				
				Stage oldStage = (Stage) btnInfoAdd.getScene().getWindow();
				oldStage.close();

				if (mDao != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("���� �Է�");
					alert.setHeaderText(txtContractor.getText() + "�� ���������� �߰��Ǿ����ϴ�.");
					alert.setContentText("���� ���� ������ �Է��ϼ���");
					alert.showAndWait();
				}
			}

		} catch (Exception ie) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� �Է�");
			alert.setHeaderText(txtContractor.getText() + "�� ���������� �߰������ʾҽ��ϴ�.");
			alert.setContentText("�ٽ� �Է��ϼ���");
			alert.showAndWait();
		}
	};
}
