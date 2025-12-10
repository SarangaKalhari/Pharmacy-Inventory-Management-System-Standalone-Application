package edu.icet.controller;

import edu.icet.model.DTO.MedicineDTO;
import edu.icet.model.DTO.SupplierDTO;
import edu.icet.model.Entity.Supplier;
import edu.icet.service.SupplierManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierManagementController implements Initializable {

    SupplierManagementService service = new SupplierManagementService();
    ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colAdded;

    @FXML
    private TableColumn<?, ?> colContactPerson;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSupID;

    @FXML
    void addOnAction(ActionEvent event) {

        String name = txtCompanyName.getText();
        String contact = txtContact.getText();
        long phone = Long.parseLong(txtPhone.getText());
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        SupplierDTO supplierDTO = new SupplierDTO(name, contact, phone, address, email);

        service.addSupplier(supplierDTO);

        loadSupplierTable();
        clear();

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

        colSupID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contact_person"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAdded.setCellValueFactory(new PropertyValueFactory<>("created_at"));

        tblSupplier.setItems(service.loadSupplier());

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, medicine, t1) -> {
            if (t1 != null){
                txtSupID.setText(t1.getSupplier_id());
                txtCompanyName.setText(t1.getCompany_name());
                txtContact.setText(t1.getContact_person());
                txtPhone.setText(String.valueOf(t1.getPhone()));
                txtEmail.setText(t1.getEmail());
                txtAddress.setText(t1.getAddress());
            }
        });

    }

    public void loadSupplierTable(){
        suppliers.clear();
        tblSupplier.setItems(service.loadSupplier());
    }

    void clear(){
        txtSupID.clear();
        txtCompanyName.clear();
        txtContact.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtAddress.clear();

    }
}
