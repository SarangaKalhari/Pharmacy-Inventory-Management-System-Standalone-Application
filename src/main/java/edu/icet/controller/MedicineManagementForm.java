package edu.icet.controller;

import edu.icet.model.DTO.MedicineDTO;
import edu.icet.model.Entity.Medicine;
import edu.icet.service.MedicineManagementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MedicineManagementForm implements Initializable {

    MedicineManagementService service = new MedicineManagementService();

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
    private DatePicker datePicker;


    @FXML
    private TableView<Medicine> tblMedicine;

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colMedID.setCellValueFactory(new PropertyValueFactory<>("medicine_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colBatchNo.setCellValueFactory(new PropertyValueFactory<>("batch_no"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));

        tblMedicine.setItems(service.loadMedicine());

    }
}
