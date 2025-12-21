package edu.icet.service;

import edu.icet.model.Entity.SalesReport;
import edu.icet.repository.SalesReportRepository;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesReportService {


        private SalesReportRepository repo = new SalesReportRepository();

        public ObservableList<SalesReport> getDailyReport(LocalDate date) {
            return repo.findDaily(date);
        }


        public BigDecimal calculateTotal(ObservableList<SalesReport> list) {
            return list.stream()
                    .map(SalesReport::getNet_amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

    public ObservableList<SalesReport> getWeeklyReport() {
        return repo.findWeekly();
    }

}
