package edu.icet.service;

import edu.icet.controller.BillPrintController;
import edu.icet.model.DTO.SaleDTO;
import edu.icet.model.Entity.Sale;
import edu.icet.repository.SaleManagementRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class SaleManagementService {

    SaleManagementRepository repository = new SaleManagementRepository();
    ObservableList<Sale> sales = FXCollections.observableArrayList();

    public ObservableList<String> getMedicineID() {
        return repository.getMedicineID();
    }

    public BigDecimal getUnitPrice(String value){
        return repository.getPrice(value);
    }

    public String calInvoiceId() {
        String lastID = repository.getInvoice();
        if (lastID != null) {
            String numberPart = lastID.replaceAll("[^0-9]", "");
            int numericID = Integer.parseInt(numberPart);
            numericID++;
            return "INV" + numericID;
        } else {
            return "INV1001";
        }

    }

    public int generateID(){
        int lastID = repository.getID();
        if (lastID != 0){
            int newID = lastID+1;
            lastID = newID;
        }
        return lastID;
    }
    public void addSaleItem(ObservableList<SaleDTO> list) {

        String invoiceID = calInvoiceId();
        int newID = generateID()+1;
        BigDecimal total = BigDecimal.ZERO;

        // Controller or Service
        BigDecimal discount = BigDecimal.ZERO;

        for (SaleDTO item :  list){
            Sale sale = new Sale(
                    newID,
                    invoiceID,
                    item.getItemID(),
                    item.getQty(),
                    item.getUnitPrice(),
                    item.getTotal()
            );
            sales.add(sale);
            total = total.add(item.getTotal());
            newID++;
            repository.updateQTY(item.getQty(), item.getItemID());
        }

        if (total.compareTo(BigDecimal.valueOf(500)) > 0) {
            discount = total.multiply(BigDecimal.valueOf(0.10));
        }

        BigDecimal netAmount = total.subtract(discount);

        repository.addSaleItems(sales, invoiceID,total,discount,netAmount);

    }

    public int getQTYs() {
        return repository.getQty();
    }

    public Integer setQTY(String value) {
        return repository.setQTY(value);
    }
}
