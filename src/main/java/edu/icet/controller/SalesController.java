package edu.icet.controller;

import edu.icet.model.DTO.SaleDTO;
import edu.icet.service.SaleManagementService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesController implements Initializable {

    Stage stage = new Stage();
    SaleManagementService service = new SaleManagementService();
    ObservableList<SaleDTO> list = FXCollections.observableArrayList();
    BillPrintController printController = new BillPrintController();

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
    private TextField txtAll;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void addItemOnAction(ActionEvent event) {

        String medID = String.valueOf(comboMedID.getValue());
        int qty = Integer.parseInt(String.valueOf(txtQty.getText()));
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
        BigDecimal total = new BigDecimal(txtTotal.getText());

        SaleDTO saleDTO = new SaleDTO(
                medID,
                qty,
                unitPrice,
                total
        );

        list.add(saleDTO);
    }

    @FXML
    void billFinishOnAction(ActionEvent event) throws IOException {

        service.addSaleItem(list);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/BillPrint.fxml")
            );

            Parent root = loader.load();

        BillPrintController controller = loader.getController();
        controller.passData(list, service.calInvoiceId());

            Stage stage = new Stage();
            stage.setTitle("Bill Preview");
            stage.setScene(new Scene(root));
            stage.show();


    }

    @FXML
    void deleteOnAction(ActionEvent event) {

        SaleDTO selected = tblSale.getSelectionModel().getSelectedItem();
        list.remove(selected);
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

        txtAll.setText(String.valueOf(service.getQTYs()));

        comboMedID.getSelectionModel().selectedItemProperty().addListener(observable -> {
            txtUnitPrice.setText(String.valueOf(service.getUnitPrice(comboMedID.getValue())));
            txtAll.setText(String.valueOf(service.setQTY(comboMedID.getValue())));
        });

        txtQty.textProperty().addListener((obs, oldVal, newVal) -> {
            if(Integer.parseInt(txtAll.getText())>=Integer.parseInt(txtQty.getText()) && (txtQty.getText()!=null)) {
                updateTotal();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Quantity");
                alert.setHeaderText(null);
                alert.setContentText("Quantity cannot be higher than available stock!");
                alert.showAndWait();

            }
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
}
