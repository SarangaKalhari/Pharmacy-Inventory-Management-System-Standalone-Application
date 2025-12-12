package edu.icet.repository;

import edu.icet.DB.DBConnection;
import edu.icet.model.DTO.SaleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleManagementRepository {
    public ObservableList<String> getMedicineID() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT medicine_id FROM medicine");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("medicine_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public BigDecimal getPrice(String medID){
        BigDecimal price = BigDecimal.ZERO;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT unit_price FROM medicine WHERE medicine_id=?");

            statement.setObject(1,medID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                price = resultSet.getBigDecimal("unit_price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return price;
    }
}
