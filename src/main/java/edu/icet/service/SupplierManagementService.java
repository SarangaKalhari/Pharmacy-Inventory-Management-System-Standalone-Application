package edu.icet.service;

import edu.icet.DB.DBConnection;
import edu.icet.model.DTO.SupplierDTO;
import edu.icet.model.Entity.Medicine;
import edu.icet.model.Entity.Supplier;
import edu.icet.repository.SupplierRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SupplierManagementService {

    SupplierRepository repository = new SupplierRepository();

    public void addSupplier(SupplierDTO supplierDTO) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Supplier supplier = new Supplier(
                getSupID(),
                supplierDTO.getCompanyName(),
                supplierDTO.getContactPerson(),
                supplierDTO.getPhone(),
                supplierDTO.getEmail(),
                supplierDTO.getAddress(),
                timestamp

        );

        repository.add(supplier);
    }

    private String getSupID() {
        String lastID = repository.getID();
        if (lastID != null) {
            String numberPart = lastID.replaceAll("[^0-9]", "");
            int numericID = Integer.parseInt(numberPart);
            numericID++;
            return "SUP" + numericID;
        } else {
            return "SUP1001";
        }
    }

    public ObservableList<Supplier> loadSupplier(){
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM supplier");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                supplierObservableList.add(new Supplier (
                        rs.getString("supplier_id"),
                        rs.getString("company_name"),
                        rs.getString("contact_person"),
                        rs.getLong("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                         rs.getTimestamp("created_at")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierObservableList;
    }
}
