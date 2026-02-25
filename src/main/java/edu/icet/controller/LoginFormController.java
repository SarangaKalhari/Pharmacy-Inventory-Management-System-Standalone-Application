package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController {
    Stage stage = new Stage();

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioCashier;

    @FXML
    private RadioButton radioPharmacist;

    @FXML
    private PasswordField txtPassword;

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
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                // Create new stage with custom title bar support
                Stage newStage = new Stage();
                newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PharmacistDashboard.fxml"))));
                newStage.initStyle(StageStyle.UNDECORATED);  // Add this line

                currentStage.close();
                newStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adminLogin(ActionEvent actionEvent) {
        if (txtUsername.getText().equals("Admin") && txtPassword.getText().equals("1234")){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));

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
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CashierBillingDashboard.fxml"))));

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
