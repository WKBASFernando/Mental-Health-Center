package com.ijse.orm.mentalhealthcenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableView<?> tblPatients;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPatientId;

    @FXML
    private TextField txtPatientName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void tblPatientOnAction(ActionEvent event) {

    }

}
