package com.ijse.orm.mentalhealthcenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TherapistController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbAvailability;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private TableColumn<?, ?> colTherapistName;

    @FXML
    private TableView<?> tblTherapists;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtTherapistId;

    @FXML
    private TextField txtTherapistName;

    @FXML
    void btnCleatOnAction(ActionEvent event) {

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
    void tblTherapistOnAction(ActionEvent event) {

    }

}
