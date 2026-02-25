package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginFormController {

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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void loginOnAction(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String selectedRole = getSelectedRole();

        // Validate inputs FIRST
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter username");
            return;
        }

        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter password");
            return;
        }

        if (selectedRole == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a user type");
            return;
        }

        // Then attempt login based on role
        if (selectedRole.equals("Pharmacist")) {
            pharmacistLogin(event, username, password);
        } else if (selectedRole.equals("Admin")) {
            adminLogin(event, username, password);
        } else if (selectedRole.equals("Cashier")) {
            cashierLogin(event, username, password);
        }
    }

    private String getSelectedRole() {
        if (radioPharmacist.isSelected()) {
            return "Pharmacist";
        } else if (radioAdmin.isSelected()) {
            return "Admin";
        } else if (radioCashier.isSelected()) {
            return "Cashier";
        }
        return null;
    }

    public void pharmacistLogin(ActionEvent actionEvent, String username, String password) {
        if (username.equals("Pharmacist") && password.equals("1234")) {
            navigateToDashboard(actionEvent, "/view/PharmacistDashboard.fxml", "Pharmacist");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Pharmacist credentials");
        }
    }

    public void adminLogin(ActionEvent actionEvent, String username, String password) {
        if (username.equals("Admin") && password.equals("1234")) {
            navigateToDashboard(actionEvent, "/view/AdminDashboard.fxml", "Admin");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Admin credentials");
        }
    }

    public void cashierLogin(ActionEvent actionEvent, String username, String password) {
        if (username.equals("Cashier") && password.equals("1234")) {
            navigateToDashboard(actionEvent, "/view/CashierBillingDashboard.fxml", "Cashier");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Cashier credentials");
        }
    }

    private void navigateToDashboard(ActionEvent actionEvent, String fxmlPath, String role) {
        try {
            // Get current stage
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Load dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            // Create new stage for dashboard
            Stage dashboardStage = new Stage();
            dashboardStage.setScene(scene);
            dashboardStage.setTitle(role + " Dashboard");

            // Close login and show dashboard
            currentStage.close();
            dashboardStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Could not load dashboard");
        }
    }

    // Keep these for FXML compatibility (if radio buttons call them directly)
    public void pharmacistLogin(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }

    public void adminLogin(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }

    public void cashierLogin(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }
}