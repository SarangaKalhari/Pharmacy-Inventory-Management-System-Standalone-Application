package edu.icet.controller;

import edu.icet.model.DTO.SaleDTO;
import edu.icet.service.SaleManagementService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesController implements Initializable {

    SaleManagementService service = new SaleManagementService();
    ObservableList<SaleDTO> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, Integer> colListID;

    @FXML
    private TableColumn<?, ?> colMedID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<String> comboMedID;

    @FXML
    private TableView<SaleDTO> tblSale;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void addItemOnAction(ActionEvent event) {

        String medID = String.valueOf(comboMedID.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
        BigDecimal total = new BigDecimal(txtTotal.getText());

        SaleDTO saleDTO = new SaleDTO(
                medID,
                qty,
                unitPrice,
                total
        );

        list.add(saleDTO);
        clear();

    }

    @FXML
    void billFinishOnAction(ActionEvent event) {

        service.addSaleItem(list);
        clear();

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

        SaleDTO selected = tblSale.getSelectionModel().getSelectedItem();
        list.remove(selected);
        clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colListID.setCellValueFactory(col ->
                new ReadOnlyObjectWrapper<>(tblSale.getItems().indexOf(col.getValue()) + 1)
        );
        colMedID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblSale.setItems(list);

        comboMedID.setItems(service.getMedicineID());

        comboMedID.getSelectionModel().selectedItemProperty().addListener(observable -> {
            txtUnitPrice.setText(String.valueOf(service.getUnitPrice(comboMedID.getValue())));
        });

        txtQty.textProperty().addListener(observable -> {
            updateTotal();
        });

        tblSale.getSelectionModel().selectedItemProperty().addListener((observableValue, saleDTO, t1) -> {
            if (t1 != null){
                comboMedID.setValue(t1.getItemID());
                txtUnitPrice.setText(String.valueOf(t1.getUnitPrice()));
                txtQty.setText(String.valueOf(t1.getQty()));
                txtTotal.setText(String.valueOf(t1.getTotal()));
            }
        });

    }

    private void updateTotal() {
        try {
            if (!txtQty.getText().isEmpty() && !txtUnitPrice.getText().isEmpty()) {

                int qty = Integer.parseInt(txtQty.getText());
                BigDecimal price = new BigDecimal(txtUnitPrice.getText());

                BigDecimal total = price.multiply(BigDecimal.valueOf(qty));

                txtTotal.setText(total.toString());
            }
        } catch (NumberFormatException e) {
            txtTotal.setText("");
        }
    }

    void clear(){
        comboMedID.setValue(null);
        txtUnitPrice.clear();
        txtQty.clear();
        txtTotal.clear();
    }

}
