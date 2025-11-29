package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    Stage stage = new Stage();

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

        if (radioPharmacist.isSelected()){
            pharmacistLogin(event);
        } else if (radioAdmin.isSelected()) {
            adminLogin(event);
        } else if (radioCashier.isSelected()) {
            cashierLogin(event);
        }
    }


    public void pharmacistLogin(ActionEvent actionEvent) {
        if (txtUsername.getText().equals("Pharmacist") && txtPassword.getText().equals("1234")){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adminLogin(ActionEvent actionEvent) {
        if (txtUsername.getText().equals("Admin") && txtPassword.getText().equals("1234")){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cashierLogin(ActionEvent actionEvent) {
        if (txtUsername.getText().equals("Cashier") && txtPassword.getText().equals("1234")){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
