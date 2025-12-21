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
}
