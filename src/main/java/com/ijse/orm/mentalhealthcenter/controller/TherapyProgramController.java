package com.ijse.orm.mentalhealthcenter.controller;

import com.ijse.orm.mentalhealthcenter.bo.BOFactory;
import com.ijse.orm.mentalhealthcenter.bo.BOType;
import com.ijse.orm.mentalhealthcenter.bo.custom.TherapyProgramBO;
import com.ijse.orm.mentalhealthcenter.dto.TherapyProgramDTO;
import com.ijse.orm.mentalhealthcenter.dto.tm.TherapyProgramTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgramController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TherapyProgramTM, String> colDuration;

    @FXML
    private TableColumn<TherapyProgramTM, String> colFee;

    @FXML
    private TableColumn<TherapyProgramTM, String> colSessionId;

    @FXML
    private TableColumn<TherapyProgramTM, String> colSessionName;

    @FXML
    private TableView<TherapyProgramTM> tblSessions;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private Label lblSessionId;

    @FXML
    private TextField txtSessionName;

    @FXML
    private AnchorPane programPane;

    @FXML
    void btnClearOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String programID = lblSessionId.getText();

        if (programID.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No program selected 💀").show();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "You sure you wanna delete this therapy program? This can't be undone ❌",
                ButtonType.YES, ButtonType.NO);
        confirmation.setTitle("Delete Confirmation");
        confirmation.setHeaderText("⚠️ Confirm Program Deletion");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = tProgramBO.deleteTProgram(programID);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Program Deleted ✅").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Program Not Deleted ❌").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled 🙅").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String therapyPID = lblSessionId.getText();
        String therapyProgramName = txtSessionName.getText();
        String therapyProgramDetails = txtDuration.getText();
        String feeText = txtFee.getText();

        if (therapyPID.isEmpty() || therapyProgramName.trim().isEmpty() ||
                therapyProgramDetails.trim().isEmpty() || feeText.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All fields must be filled out 🛑").show();
            return;
        }

        double therapyProgramFee;
        try {
            therapyProgramFee = Double.parseDouble(feeText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Fee must be a number 💸").show();
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                therapyPID,
                therapyProgramName,
                therapyProgramDetails,
                therapyProgramFee
        );

        boolean isSaved = tProgramBO.saveTPrograms(therapyProgramDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapy Program Saved ✅").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapy Program Not Saved ❌").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String therapyPID = lblSessionId.getText();
        String therapyProgramName = txtSessionName.getText();
        String therapyProgramDetails = txtDuration.getText();
        String feeText = txtFee.getText();

        if (therapyPID.isEmpty() || therapyProgramName.trim().isEmpty() ||
                therapyProgramDetails.trim().isEmpty() || feeText.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All fields must be filled out 🛑").show();
            return;
        }

        double therapyProgramFee;
        try {
            therapyProgramFee = Double.parseDouble(feeText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Fee must be a number 💸").show();
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                therapyPID,
                therapyProgramName,
                therapyProgramDetails,
                therapyProgramFee
        );

        boolean isSaved = tProgramBO.updateTPrograms(therapyProgramDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapy Program Updated ✅").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapy Program Not Updated ❌").show();
        }
    }

    @FXML
    void tblOnAction(MouseEvent event) {
        TherapyProgramTM selectedPatient = tblSessions.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            lblSessionId.setText(selectedPatient.getId());
            txtSessionName.setText(selectedPatient.getName());
            txtDuration.setText(selectedPatient.getDuration());
            txtFee.setText(String.valueOf(selectedPatient.getFee()));
        }
    }

    TherapyProgramBO tProgramBO = BOFactory.getInstance().getBO(BOType.THERAPY_PROGRAMS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSessionId.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM, String>("id"));
        colSessionName.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM, String>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM, String>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM, String>("fee"));

        try{
            refreshPage();
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Load the Page", ButtonType.OK).show();
        }
    }

    public void refreshPage() throws Exception{
        loadTableData();

        lblSessionId.setText(tProgramBO.getNextProgramID());
        txtSessionName.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    public void loadTableData() throws Exception{
        List<TherapyProgramDTO> programDtos =  tProgramBO.getALL();
        ObservableList<TherapyProgramTM> programTMS = FXCollections.observableArrayList();
        for (TherapyProgramDTO programDto : programDtos) {

            TherapyProgramTM programTM = new TherapyProgramTM(
                    programDto.getId(),
                    programDto.getName(),
                    programDto.getDuration(),
                    programDto.getFee()
            );
            programTMS.add(programTM);
        }
        tblSessions.setItems(programTMS);
    }
}
