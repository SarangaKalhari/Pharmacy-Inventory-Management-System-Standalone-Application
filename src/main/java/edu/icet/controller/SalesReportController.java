package edu.icet.controller;

import edu.icet.model.Entity.SalesReport;
import edu.icet.service.SalesReportService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SalesReportController implements Initializable {

    @FXML
    private TableView<SalesReport> tblSales;
    @FXML
    private TableColumn<?, String> colInvoiceId;
    @FXML
    private TableColumn<?, LocalDate> colDate;
    @FXML
    private TableColumn<?, BigDecimal> colTotal;

    @FXML
    private Label lblTotalSales;
    @FXML
    private Label lblInvoices;
    @FXML
    private Label lblItems;

    @FXML private DatePicker datePicker;
    @FXML private BarChart<String, Number> salesChart;

    private SalesReportService service = new SalesReportService();


    @FXML
    void loadDaily() {
        LocalDate date = datePicker.getValue();
        loadUI(service.getDailyReport(date));
    }

    @FXML
    void loadWeekly() {
    }

    @FXML
    void loadMonthly() {
    }

    private void loadUI(ObservableList<SalesReport> list) {
        tblSales.setItems(list);

        lblInvoices.setText("Invoices: " + list.size());
        lblTotalSales.setText("Total Sales: " +
                service.calculateTotal(list));

        loadChart(list);
    }

    private void loadChart(ObservableList<SalesReport> list) {
        salesChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (SalesReport dto : list) {
            series.getData().add(
                    new XYChart.Data<>(dto.getDate().toString(), dto.getNet_amount())
            );
        }
        salesChart.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInvoiceId.setCellValueFactory(new PropertyValueFactory<>("invoice_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("net_amount"));

        datePicker.setValue(LocalDate.now());
        loadDaily();
    }
}
