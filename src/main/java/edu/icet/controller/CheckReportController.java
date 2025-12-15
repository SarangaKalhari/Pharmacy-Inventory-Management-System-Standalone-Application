package edu.icet.controller;

import edu.icet.model.Entity.Medicine;
import edu.icet.service.CheckReportService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckReportController implements Initializable {

    @FXML
    private TableView<Medicine> tblReport;

    @FXML
    private TableColumn<Medicine, String> colMedId;

    @FXML
    private TableColumn<Medicine, String> colName;

    @FXML
    private TableColumn<?, Integer> colQty;

    @FXML
    private TableColumn<?, LocalDate> colExpire;

    @FXML
    private TableColumn<?, String> colStatus;

    @FXML
    private Button btnExpireSoon;

    @FXML
    private Button btnExpired;

    @FXML
    private Button btnLowStock;

    private ObservableList<Medicine> list = FXCollections.observableArrayList();
    CheckReportService service = new CheckReportService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colMedId.setCellValueFactory(new PropertyValueFactory<>("medicine_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colExpire.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblReport.setItems(service.loadLowStock("Low Stock"));
    }

    @FXML
    void loadLowStock() {
        list.clear();

        tblReport.setItems(service.loadLowStock("LOW STOCK"));

        btnLowStock.setDisable(true);

    }


    @FXML
    void loadExpired() {
        tblReport.setItems(null);
        list.clear();
        tblReport.setItems(service.loadExpired("EXPIRED"));
    }

    // 🟠 Expiring soon
    @FXML
    void loadExpiringSoon() {

    }
}

