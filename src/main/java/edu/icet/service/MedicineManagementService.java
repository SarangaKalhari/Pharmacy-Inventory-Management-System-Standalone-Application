package edu.icet.service;

import edu.icet.DB.DBConnection;
import edu.icet.model.DTO.MedicineDTO;
import edu.icet.model.Entity.Medicine;
import edu.icet.repository.MedicineRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MedicineManagementService {

    MedicineRepository repository = new MedicineRepository();

    public ObservableList<Medicine> loadMedicine() {

        ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM medicine");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                medicineObservableList.add(new Medicine(
                        rs.getString("medicine_id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("supplier_id"),
                        rs.getString("batch_no"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("cost"),
                        rs.getBigDecimal("unit_price"),
                        rs.getDate("expiry_date").toLocalDate(),
//                        expiry,
                        rs.getString("category")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicineObservableList;
    }

    public ObservableList<String> getSupplierID(){
        return repository.getSupplierID();
    }

    private String getMedID() {
        String lastID = repository.getID();
        if (lastID != null) {
            String numberPart = lastID.replaceAll("[^0-9]", "");
            int numericID = Integer.parseInt(numberPart);
            numericID++;
            return "MED" + numericID;
        } else {
            return "MED1001";
        }

    }

    public void addMedicine(MedicineDTO medicineDTO) {

        Medicine medicine = new Medicine(
                getMedID(),
                medicineDTO.getName(),
                medicineDTO.getBrand(),
                medicineDTO.getSupplierID(),
                medicineDTO.getBatchNo(),
                medicineDTO.getQuantity(),
                medicineDTO.getCost(),
                medicineDTO.getUnitPrice(),
                medicineDTO.getExpiryDate(),
                medicineDTO.getCategory()
        );

        repository.add(medicine);
    }


}
