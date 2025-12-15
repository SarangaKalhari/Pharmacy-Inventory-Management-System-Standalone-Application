package edu.icet.service;

import edu.icet.model.Entity.Medicine;
import edu.icet.repository.CheckReportRepository;
import javafx.collections.ObservableList;

public class CheckReportService {

    CheckReportRepository repository = new CheckReportRepository();



    public void loadExpired(String expired) {
        repository.expired(expired);
    }
}
