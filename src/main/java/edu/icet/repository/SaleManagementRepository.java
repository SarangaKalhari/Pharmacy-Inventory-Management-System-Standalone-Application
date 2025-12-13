package edu.icet.repository;

import edu.icet.DB.DBConnection;
import edu.icet.model.Entity.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

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

    public String getInvoice() {
        String lastID = null;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT invoice_id FROM sales_invoice ORDER BY CAST(SUBSTRING(invoice_id, 4) AS UNSIGNED) DESC LIMIT 1");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                lastID = rs.getString("invoice_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastID;
    }

    public int getID(){
        int lastID = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM sales_items ORDER BY id DESC LIMIT 1");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                 lastID = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastID;
    }

    public void addSaleItems(ObservableList<Sale> sales, String invoice, BigDecimal total, BigDecimal discount, BigDecimal netAmount) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sales_invoice (invoice_id, date, total_amount, discount, net_amount) VALUES (?,?,?,?,?)");

            preparedStatement.setObject(1, invoice);
            preparedStatement.setObject(2, timestamp);
            preparedStatement.setObject(3, total);
            preparedStatement.setObject(4, discount);
            preparedStatement.setObject(5, netAmount);

            preparedStatement.execute();
//            preparedStatement.setObject(4,);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Connection connection =  DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sales_items (id, invoice_id, medicine_id, quantity, price, total) VALUES (?, ?, ?, ?, ?, ?)");

            int newID = getID() + 1;
            for (Sale item : sales){
                statement.setObject(1,newID);
                statement.setObject(2,item.getInvoice_id());
                statement.setObject(3,item.getMedicine_id());
                statement.setObject(4,item.getQuantity());
                statement.setObject(5,item.getPrice());
                statement.setObject(6,item.getTotal());

                statement.addBatch();
                newID++;
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Integer> getQty() {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT quantity FROM medicine");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Integer setQTY(String value) {

        int qty = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT quantity FROM medicine WHERE medicine_id=?");

            ps.setObject(1,value);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                qty = rs.getInt("quantity");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return qty;
    }
}
