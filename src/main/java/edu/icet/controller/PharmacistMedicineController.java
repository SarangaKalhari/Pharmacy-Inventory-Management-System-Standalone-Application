package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PharmacistMedicineController {

    @FXML
    private TableColumn<?, ?> colBatchNo;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colExp;

    @FXML
    private TableColumn<?, ?> colMedID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<?> comboSupID;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> tblMedicine;

    @FXML
    private TextField txtBatchNo;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtMedID;

    @FXML
    private TextField txtMedName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void dashboardOnAction(ActionEvent event) {

    }

    @FXML
    void loginOnAction(ActionEvent event) {

    }

    @FXML
    void reloadOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @FXML
    void viewOnAction(ActionEvent event) {

    }

}
