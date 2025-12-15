package edu.icet.service;

import edu.icet.model.Entity.Medicine;
import edu.icet.repository.CheckReportRepository;
import javafx.collections.ObservableList;

public class CheckReportService {

    CheckReportRepository repository = new CheckReportRepository();



    public ObservableList<Medicine> loadExpired(String expired) {
        return repository.expired(expired);
    }

    public ObservableList<Medicine> loadLowStock(String lowStock) {
        return repository.loadLowStack(lowStock);
    }

}
