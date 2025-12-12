package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalesController {

    @FXML
    private TableColumn<?, ?> colContactPerson;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colSupID;

    @FXML
    private ComboBox<?> comboMedID;

    @FXML
    private TableView<?> tblSale;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtQty;

    @FXML
    void addItemOnAction(ActionEvent event) {

    }

    @FXML
    void billFinishOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
