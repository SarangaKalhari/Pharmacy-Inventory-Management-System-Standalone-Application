package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginFormController {

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioCashier;

    @FXML
    private RadioButton radioPharmacist;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) {

    }

}
