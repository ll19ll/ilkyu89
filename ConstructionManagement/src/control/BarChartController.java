package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.BarChartVO;

public class BarChartController implements Initializable {
	@FXML
	private BarChart barChart;
	@FXML
	private Button btnBarClose;

	ObservableList barList = FXCollections.observableArrayList();
	ObservableList<BarChartVO> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		monthlyPay();
		XYChart.Series series = new XYChart.Series();
		series.setName("월별매출액");
		for (int i = 0; i < data.size(); i++) {
			barList.add(new XYChart.Data<>(data.get(i).getMon(), Integer.parseInt(data.get(i).getPay())));
		}
		series.setData(barList);
		barChart.getData().add(series);

		btnBarClose.setOnAction(event -> {
			Stage oldStage = (Stage) btnBarClose.getScene().getWindow();
			oldStage.close();
		});
	}

	public void monthlyPay() {
		try {
			ViewDAO bDao = new ViewDAO();
			ArrayList<BarChartVO> list = new ArrayList<>();
			BarChartVO cb;
			list = bDao.getMonthlyPay();
			int count = list.size();
			for (int i = 0; i < count; i++) {
				cb = list.get(i);
				data.add(cb);
			}
		} catch (Exception e) {
			System.out.println("월매출액컨트롤러" + e);
		}
	}
}
