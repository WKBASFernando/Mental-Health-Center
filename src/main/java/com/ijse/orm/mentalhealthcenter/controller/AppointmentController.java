package com.ijse.orm.mentalhealthcenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AppointmentController {

    @FXML
    private Button PatientsBTN;

    @FXML
    private Button addAppointmentBTN;

    @FXML
    private Button addDoctors;

    @FXML
    private Button addPrograms;

    @FXML
    private AnchorPane appointmentPage;

    @FXML
    private Label date;

    @FXML
    private Label docLoadLabel;

    @FXML
    private Label patientAddress;

    @FXML
    private Label patientDOB;

    @FXML
    private Label patientEMAIL;

    @FXML
    private Label patientGender;

    @FXML
    private Label patientID;

    @FXML
    private Label patientNIC;

    @FXML
    private Label patientName;

    @FXML
    private Label patientTelNO;

    @FXML
    private TextField payAMOUNT;

    @FXML
    private Label paymentID;

    @FXML
    private ComboBox<?> paymentMethod;

    @FXML
    private Button printBillBTN;

    @FXML
    private ListView<?> programmsListView;

    @FXML
    private Button registerPatient;

    @FXML
    private Button reset;

    @FXML
    private TextField search;

    @FXML
    private Button searchPatient;

    @FXML
    private DatePicker sessionDate;

    @FXML
    private Label sessionID;

    @FXML
    private TextField sessionNotes;

    @FXML
    private TextField sessionTime;

    @FXML
    private Label time;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    @FXML
    private Button viewAppointmentsBTN;

    @FXML
    void PatientsBTNAction(ActionEvent event) {

    }

    @FXML
    void addAppointmentBTNAction(ActionEvent event) {

    }

    @FXML
    void addDoctorsAction(MouseEvent event) {

    }

    @FXML
    void addProgramsAction(MouseEvent event) {

    }

    @FXML
    void printBillBTNAction(ActionEvent event) {

    }

    @FXML
    void registerPatientAction(MouseEvent event) {

    }

    @FXML
    void resetAction(ActionEvent event) {

    }

    @FXML
    void searchPatientAction(MouseEvent event) {

    }

    @FXML
    void viewAppointmentsBTNAction(ActionEvent event) {

    }

}
