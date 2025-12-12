package edu.icet.service;

import edu.icet.model.DTO.SaleDTO;
import edu.icet.repository.SaleManagementRepository;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class SaleManagementService {

    SaleManagementRepository repository = new SaleManagementRepository();

    public ObservableList<String> getMedicineID() {
        return repository.getMedicineID();
    }

    public BigDecimal getUnitPrice(String value){
        return repository.getPrice(value);
    }

    public void addSaleItem(SaleDTO saleDTO) {

    }
}
