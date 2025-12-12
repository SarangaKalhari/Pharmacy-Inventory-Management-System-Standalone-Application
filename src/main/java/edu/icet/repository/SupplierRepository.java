package edu.icet.repository;

import edu.icet.DB.DBConnection;
import edu.icet.model.Entity.Supplier;

import java.sql.*;

public class SupplierRepository {
    public void add(Supplier supplier) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO supplier VALUES(?,?,?,?,?,?,?)");

            preparedStatement.setString(1,supplier.getSupplier_id());
            preparedStatement.setString(2, supplier.getCompany_name());
            preparedStatement.setString(3, supplier.getContact_person());
            preparedStatement.setLong(4, supplier.getPhone());
            preparedStatement.setString(5, supplier.getEmail());
            preparedStatement.setString(6, supplier.getAddress());
            preparedStatement.setTimestamp(7, supplier.getCreated_at());

            preparedStatement.execute();        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getID() {
        String lastID = null;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT supplier_id FROM supplier ORDER BY CAST(SUBSTRING(supplier_id, 4) AS UNSIGNED) DESC LIMIT 1");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                lastID = rs.getString("supplier_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastID;
    }

    public void delete(String supplierID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM supplier WHERE supplier_id=?");

            statement.setString(1,supplierID);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
