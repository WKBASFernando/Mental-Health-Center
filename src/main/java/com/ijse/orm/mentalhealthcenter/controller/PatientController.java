package com.ijse.orm.mentalhealthcenter.controller;

import com.ijse.orm.mentalhealthcenter.bo.BOFactory;
import com.ijse.orm.mentalhealthcenter.bo.BOType;
import com.ijse.orm.mentalhealthcenter.bo.custom.PatientBO;
import com.ijse.orm.mentalhealthcenter.dto.PatientDTO;
import com.ijse.orm.mentalhealthcenter.dto.tm.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private TableColumn<PatientTM, String> colAddress;

    @FXML
    private TableColumn<PatientTM, String> colAge;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colGender;

    @FXML
    private TableColumn<PatientTM, String> colPatientId;

    @FXML
    private TableColumn<PatientTM, String> colPatientName;

    @FXML
    private TableColumn<PatientTM, String> colPhoneNumber;

    @FXML
    private TableView<PatientTM> tblPatients;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblPatientId;

    @FXML
    private TextField txtPatientName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnClearOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String patientID = lblPatientId.getText();

        if (patientID.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, " Please select data from table",ButtonType.CLOSE).show();
            return;
        }
        boolean isDeleted = patientBO.deletePatient(patientID);
        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Failed to Delete",ButtonType.OK).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String patientId = lblPatientId.getText();
        String patientName = txtPatientName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String patientAge = txtAge.getText();
        String patientGender = cmbGender.getSelectionModel().getSelectedItem();
        String patientPhoneNumber = txtPhoneNumber.getText();
        String patientAddress  = txtAddress.getText();
        String patientEmail = txtEmail.getText();

        if (patientId.isEmpty() || patientName.trim().isEmpty() || patientAge.isEmpty() || patientGender.isEmpty() || patientPhoneNumber.trim().isEmpty() || patientAddress.trim().isEmpty() || patientEmail.trim().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please select data from table",ButtonType.CLOSE).show();
            return;
        }

        PatientDTO patientDTO = new PatientDTO(
                patientId,
                patientName,
                age,
                patientGender,
                patientPhoneNumber,
                patientAddress,
                patientEmail
        );

        boolean isUpdated = patientBO.savePatient(patientDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Patient saved successfully", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Patient",ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String patientId = lblPatientId.getText();
        String patientName = txtPatientName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String patientAge = txtAge.getText();
        String patientGender = cmbGender.getSelectionModel().getSelectedItem();
        String patientPhoneNumber = txtPhoneNumber.getText();
        String patientAddress  = txtAddress.getText();
        String patientEmail = txtEmail.getText();

        if (patientId.isEmpty() || patientName.trim().isEmpty() || patientAge.isEmpty() || patientGender.isEmpty() || patientPhoneNumber.trim().isEmpty() || patientAddress.trim().isEmpty() || patientEmail.trim().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please select data from table",ButtonType.CLOSE).show();
            return;
        }

        PatientDTO patientDTO = new PatientDTO(
                patientId,
                patientName,
                age,
                patientGender,
                patientPhoneNumber,
                patientAddress,
                patientEmail
        );

        boolean isUpdated = patientBO.updatePatient(patientDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Failed to update Patient",ButtonType.OK).show();
        }
    }

    @FXML
    void tblPatientOnAction(MouseEvent event)  {
        PatientTM selectedPatient = tblPatients.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            lblPatientId.setText(selectedPatient.getId());
            txtPatientName.setText(selectedPatient.getName());
            txtAge.setText(String.valueOf(selectedPatient.getAge()));
            cmbGender.setValue(selectedPatient.getGender());
            txtPhoneNumber.setText(selectedPatient.getPhoneNumber());
            txtAddress.setText(selectedPatient.getAddress());
            txtEmail.setText(selectedPatient.getEmail());
        }
    }

    PatientBO patientBO = BOFactory.getInstance().getBO(BOType.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, " Failed ").show();
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void refreshPage() throws Exception {
        loadTableData();

        lblPatientId.setText(patientBO.getNextPatientID());
        txtPatientName.clear();
        txtAge.clear();
        cmbGender.setItems(FXCollections.observableArrayList("Male", "Female"));
        txtPhoneNumber.clear();
        txtAddress.clear();
        txtEmail.clear();
    }

    public void loadTableData() throws Exception {
        List<PatientDTO> patientDTOS = patientBO.getALL();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : patientDTOS) {
            PatientTM patientTM = new PatientTM(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getAge(),
                    patientDTO.getGender(),
                    patientDTO.getPhoneNumber(),
                    patientDTO.getAddress(),
                    patientDTO.getEmail()

            );
            patientTMS.add(patientTM);
        }
        tblPatients.setItems(patientTMS);
    }
}
