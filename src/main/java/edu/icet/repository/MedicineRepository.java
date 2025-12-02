package edu.icet.repository;

import edu.icet.DB.DBConnection;
import edu.icet.model.Entity.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MedicineRepository {

    public ObservableList<String> getSupplierID(){

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT supplier_id FROM medicine");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("supplier_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String getID(){
        String lastID = null;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT medicine_id FROM medicine ORDER BY CAST(SUBSTRING(medicine_id, 4) AS UNSIGNED) DESC LIMIT 1");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                lastID = rs.getString("medicine_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastID;
    }

    public void add(Medicine medicine) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO medicine VALUES(?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,medicine.getMedicine_id());
            preparedStatement.setString(2, medicine.getName());
            preparedStatement.setString(3,medicine.getBrand());
            preparedStatement.setString(4,medicine.getSupplier_id());
            preparedStatement.setString(5,medicine.getBatch_no());
            preparedStatement.setInt(6,medicine.getQuantity());
            preparedStatement.setBigDecimal(7,medicine.getCost());
            preparedStatement.setBigDecimal(8,medicine.getUnit_price());
            preparedStatement.setDate(9, Date.valueOf(medicine.getExpiry_date()));
            preparedStatement.setString(10,medicine.getCategory());

            preparedStatement.execute();        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String text) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM medicine WHERE medicine_id=?");

            statement.setString(1,text);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Medicine medicine) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medicine SET name=?, brand=?, supplier_id=?, batch_no=?, quantity=?, cost=?, unit_price=?, expiry_date=?, category=? WHERE medicine_id=?");

            preparedStatement.setObject(1,medicine.getName());
            preparedStatement.setObject(2,medicine.getBrand());
            preparedStatement.setObject(3,medicine.getSupplier_id());
            preparedStatement.setObject(4,medicine.getBatch_no());
            preparedStatement.setObject(5,medicine.getQuantity());
            preparedStatement.setObject(6,medicine.getCost());
            preparedStatement.setObject(7,medicine.getUnit_price());
            preparedStatement.setObject(8,medicine.getExpiry_date());
            preparedStatement.setObject(9,medicine.getCategory());
            preparedStatement.setObject(10,medicine.getMedicine_id());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
