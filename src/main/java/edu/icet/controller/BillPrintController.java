package edu.icet.controller;

import edu.icet.model.DTO.SaleDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class BillPrintController implements Initializable {

    @FXML
    private VBox vboxItems;

    @FXML
    private Label lblInvoice;

    @FXML
    private Label lblDateTime;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblDiscount;

    @FXML
    private Label lblNetAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // runs after FXML loads
    }

    // 🔹 DATA PASS METHOD
    public void passData(ObservableList<SaleDTO> list, String invoiceId) {

        BigDecimal total = BigDecimal.ZERO;

        vboxItems.getChildren().clear();

        for (SaleDTO sale : list) {

            total.add(sale.getTotal());

            Label label = new Label(
                    sale.getItemID() + "   \t" +
                            sale.getQty() + "\t"+" x "+"\t" +
                            sale.getUnitPrice() + "\t"+" = "+"\t" +
                            total
            );

            vboxItems.getChildren().add(label);
        }

        lblInvoice.setText(invoiceId);
        lblTotalAmount.setText(String.format("%.2f", total));
        lblDiscount.setText("0.00");
        lblNetAmount.setText(String.format("%.2f", total));
    }

    // 🔹 PRINT BUTTON METHOD
    @FXML
    private void handlePrint() {

        Node billPane = vboxItems.getParent(); // AnchorPane

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(billPane.getScene().getWindow())) {
            if (job.printPage(billPane)) {
                job.endJob();
            }
        }
    }
}
