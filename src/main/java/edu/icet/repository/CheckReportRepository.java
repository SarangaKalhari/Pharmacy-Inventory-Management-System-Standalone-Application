package edu.icet.repository;

import edu.icet.DB.DBConnection;
import edu.icet.model.Entity.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CheckReportRepository {

    ObservableList<Medicine> list = FXCollections.observableArrayList();

    public ObservableList<Medicine> loadLowStack(String lowStock) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * "+"FROM medicine WHERE quantity < 50");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(map(resultSet, lowStock));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Medicine map(ResultSet resultSet, String lowStock) throws SQLException {
        return new Medicine(
                resultSet.getString("medicine_id"),
                resultSet.getString("name"),
                resultSet.getString("brand"),
                resultSet.getString("supplier_id"),
                resultSet.getString("batch_no"),
                resultSet.getInt("quantity"),
                resultSet.getBigDecimal("cost"),
                resultSet.getBigDecimal("unit_price"),
                resultSet.getDate("expiry_date").toLocalDate(),
                resultSet.getString("category"),
                lowStock
        );
    }

    public ObservableList<Medicine> expired(String expired) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * "+"FROM medicine WHERE expiry_date < CURDATE()");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                list.add(
                        new Medicine(
                                resultSet.getString("medicine_id"),
                                resultSet.getString("name"),
                                resultSet.getString("brand"),
                                resultSet.getString("supplier_id"),
                                resultSet.getString("batch_no"),
                                resultSet.getInt("quantity"),
                                resultSet.getBigDecimal("cost"),
                                resultSet.getBigDecimal("unit_price"),
                                resultSet.getDate("expiry_date").toLocalDate(),
                                resultSet.getString("category"),
                                expired
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }
}
