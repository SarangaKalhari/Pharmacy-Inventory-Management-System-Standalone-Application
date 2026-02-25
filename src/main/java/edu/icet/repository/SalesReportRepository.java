package edu.icet.repository;

import edu.icet.DB.DBConnection;
import edu.icet.model.Entity.SalesReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class SalesReportRepository {

    public ObservableList<SalesReport> findDaily(LocalDate date) {

        ObservableList<SalesReport> list = FXCollections.observableArrayList();

        String sql = """
                SELECT invoice_id, date, net_amount
                FROM sales_invoice
                WHERE DATE(date) = ?
                """;

        try {
                Connection con = DBConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(date));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                        new SalesReport(
                                rs.getString("invoice_id"),
                                rs.getTimestamp("date").toLocalDateTime().toLocalDate(),
                                rs.getBigDecimal("net_amount")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<SalesReport> findWeekly() {

        ObservableList<SalesReport> list = FXCollections.observableArrayList();

        String sql = """
                SELECT invoice_id, date, net_amount
                FROM sales_invoice
                WHERE date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                """;

        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(
                        new SalesReport(
                                rs.getString("invoice_id"),
                                rs.getTimestamp("date").toLocalDateTime().toLocalDate(),
                                rs.getBigDecimal("net_amount")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<SalesReport> findMonthly() {

        ObservableList<SalesReport> list = FXCollections.observableArrayList();

        try {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                SELECT invoice_id, date, net_amount
                FROM sales_invoice
                WHERE MONTH(date) = MONTH(CURDATE())
                  AND YEAR(date) = YEAR(CURDATE())
                """);

            ResultSet set = statement.executeQuery();

            while (set.next()){
                list.add(
                        new SalesReport(
                                set.getString("invoice_id"),
                                set.getTimestamp("date").toLocalDateTime().toLocalDate(),
                                set.getBigDecimal("net_amount")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalItemsSold(LocalDate date) {

        String sql = """
                SELECT SUM(quantity) AS total_items
                FROM sales_items si
                JOIN sales_invoice inv ON si.invoice_id = inv.invoice_id
                WHERE DATE(inv.date) = ?
                """;

        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_items");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
