package edu.icet.controller;

import edu.icet.model.DTO.MedicineDTO;
import edu.icet.model.Entity.Medicine;
import edu.icet.repository.MedicineRepository;
import edu.icet.service.MedicineManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MedicineManagementForm implements Initializable {

    ObservableList<Medicine> medicineList = FXCollections.observableArrayList();
    MedicineManagementService service = new MedicineManagementService();
    Stage stage = new Stage();

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
    private ComboBox<String> comboSupID;

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

        String name = txtMedName.getText();
        String brand = txtBrand.getText();
        String batchNo = txtBatchNo.getText();
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal cost = new BigDecimal(txtCost.getText());
        BigDecimal price = new BigDecimal(txtPrice.getText());
        LocalDate expDate = datePicker.getValue();
        String category = txtCategory.getText();
        String supplierId = String.valueOf(comboSupID.getValue());

        MedicineDTO medicineDTO = new MedicineDTO(name, brand, supplierId, batchNo, qty, cost, price,expDate, category);

        service.addMedicine(medicineDTO);

        loadMedicineTable();
        clear();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {

        service.deleteMedicine(txtMedID.getText());

        loadMedicineTable();
        clear();
    }

    @FXML
    void reloadOnAction(ActionEvent event) {

        clear();
        loadMedicineTable();
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        BigDecimal cost = new BigDecimal(txtCost.getText());
        BigDecimal unitPrice = new BigDecimal(txtPrice.getText());

        MedicineDTO medicineDTO = new MedicineDTO(
                txtMedName.getText(),
                txtBrand.getText(),
                comboSupID.getValue(),
                txtBatchNo.getText(),
                Integer.parseInt(txtQty.getText()),
                cost,
                unitPrice,
                datePicker.getValue(),
                txtCategory.getText()
        );
        service.updateMedicine(txtMedID.getText(),medicineDTO);

        loadMedicineTable();
        clear();

    }

    @FXML
    void viewOnAction(ActionEvent event) {

        service.viewMedicine(txtMedID.getText());

        MedicineDTO medicineDTO= service.viewMedicine(txtMedID.getText());

        txtMedName.setText(medicineDTO.getName());
        txtBrand.setText(medicineDTO.getBrand());
        txtBatchNo.setText(medicineDTO.getBatchNo());
        txtQty.setText(String.valueOf(medicineDTO.getQuantity()));
        txtCost.setText(String.valueOf(medicineDTO.getCost()));
        txtPrice.setText(String.valueOf(medicineDTO.getUnitPrice()));
        txtCategory.setText(medicineDTO.getCategory());
        datePicker.setValue(medicineDTO.getExpiryDate());
        comboSupID.setValue(medicineDTO.getSupplierID());

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

        comboSupID.setItems(service.getSupplierID());

        tblMedicine.getSelectionModel().selectedItemProperty().addListener((observableValue, medicine, t1) -> {
            if (t1 != null){
                txtMedID.setText(t1.getMedicine_id());
                txtMedName.setText(t1.getName());
                txtBrand.setText(t1.getBrand());
                comboSupID.setValue(t1.getSupplier_id());
                txtBatchNo.setText(t1.getBatch_no());
                txtQty.setText(String.valueOf(t1.getQuantity()));
                txtCost.setText(String.valueOf(t1.getCost()));
                txtPrice.setText(String.valueOf(t1.getUnit_price()));
                datePicker.setValue(t1.getExpiry_date());
                txtCategory.setText(t1.getCategory());
            }
        });

    }

    public void loadMedicineTable(){
        medicineList.clear();
        tblMedicine.setItems(service.loadMedicine());
    }

    void clear(){
        txtMedID.clear();
        txtMedName.clear();
        txtBrand.clear();
        txtBatchNo.clear();
        txtQty.clear();
        txtCost.clear();
        txtPrice.clear();
        txtCategory.clear();

        comboSupID.setValue(null);
        datePicker.setValue(null);
    }

    public void dashboardOnAction(ActionEvent actionEvent) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loginOnAction(ActionEvent actionEvent) {

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            stage.setTitle("Login Form");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
