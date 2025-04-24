package com.ijse.orm.mentalhealthcenter.controller;

import com.ijse.orm.mentalhealthcenter.bo.BOFactory;
import com.ijse.orm.mentalhealthcenter.bo.BOType;
import com.ijse.orm.mentalhealthcenter.bo.custom.TherapistBO;
import com.ijse.orm.mentalhealthcenter.dto.TherapistDTO;
import com.ijse.orm.mentalhealthcenter.dto.tm.TherapistTM;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbAvailability;

    @FXML
    private TableColumn<TherapistTM, String> colAvailability;

    @FXML
    private TableColumn<TherapistTM, String> colDescription;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistId;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistName;

    @FXML
    private TableView<TherapistTM> tblTherapists;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Label lblTherId;

    @FXML
    private TextField txtTherapistName;

    @FXML
    void btnCleatOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String therapistID = lblTherId.getText();

        if (therapistID.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No therapist selected 💀", ButtonType.OK).show();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you wanna delete this therapist? This action can't be undone 🚫",
                ButtonType.YES, ButtonType.NO);
        confirmation.setTitle("Delete Confirmation");
        confirmation.setHeaderText("⚠️ Confirm Delete");

        // Show and wait for user's decision
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = therapistBO.deleteTherapist(therapistID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist deleted successfully ✅").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete therapist ❌").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion cancelled 🙅").show();
        }
    }

    private boolean isValidInput() {
        if (lblTherId.getText().isEmpty() ||
                txtTherapistName.getText().trim().isEmpty() ||
                cmbAvailability.getValue() == null ||
                txtDescription.getText().trim().isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "All fields are required 💀", ButtonType.OK).show();
            return false;
        }

        if (!txtTherapistName.getText().matches("^[A-Za-z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Name must contain only letters 🧠", ButtonType.OK).show();
            return false;
        }

        return true;
    }

    private TherapistDTO getTherapistDTO() {
        return new TherapistDTO(
                lblTherId.getText(),
                txtTherapistName.getText(),
                cmbAvailability.getValue(),
                txtDescription.getText()
        );
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (!isValidInput()) return;

        TherapistDTO therapistDTO = getTherapistDTO();
        boolean isSaved = therapistBO.saveTherapist(therapistDTO);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Therapist saved successfully 🧑‍⚕️", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save therapist 😵", ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        if (!isValidInput()) return;

        TherapistDTO therapistDTO = getTherapistDTO();
        boolean isUpdated = therapistBO.updateTherapist(therapistDTO);

        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Therapist updated successfully ✅", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update therapist ❌", ButtonType.OK).show();
        }
    }

    @FXML
    void tblTherapistOnAction(MouseEvent event) {
        TherapistTM selectedPatient = tblTherapists.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            lblTherId.setText(selectedPatient.getId());
            txtTherapistName.setText(selectedPatient.getName());
            cmbAvailability.setValue(selectedPatient.getAvailability());
            txtDescription.setText(selectedPatient.getDescription());
        }
    }

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTherapistName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        try{
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Failed to load Page", ButtonType.OK).show();
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();

        lblTherId.setText(therapistBO.getNextTherapyID());
        txtTherapistName.clear();
        cmbAvailability.setItems(FXCollections.observableArrayList("Available", "Un-Available"));
        txtDescription.clear();
    }

    private void loadTableData() throws Exception {
        List<TherapistDTO> therapistDTOS =  therapistBO.getALLTherapists();
        if (therapistDTOS == null || therapistDTOS.isEmpty()) {
            System.out.println("No data available");
        }
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();
        for (TherapistDTO therapistDTO : therapistDTOS) {
            TherapistTM therapistTM = new TherapistTM(
                    therapistDTO.getId(),
                    therapistDTO.getName(),
                    therapistDTO.getAvailability(),
                    therapistDTO.getDescription()

            );
            therapistTMS.add(therapistTM);
        }
        tblTherapists.setItems(therapistTMS);
    }
}
