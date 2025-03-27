package com.ijse.orm.mentalhealthcenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button BtnPayments;

    @FXML
    private Button btnPatients;

    @FXML
    private Button btnTherapists;

    @FXML
    private Button btnTherapyProgram;

    @FXML
    private Button btnTherapySessions;

    @FXML
    private Button btnUsers;

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView iconHospital;

    @FXML
    private Label lblUserName;

    @FXML
    private Pane topPane;

    @FXML
    void appointmentsOnAction(ActionEvent event) {

    }

    @FXML
    void patientOnAction(ActionEvent event) {
        navigateTo("/view/Patient.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void therapistOnAction(ActionEvent event) {
        navigateTo("/view/Therapist.fxml");
    }

    @FXML
    void therapyProgramOnAction(ActionEvent event) {
        navigateTo("/view/TherapyProgram.fxml");
    }

    @FXML
    void usersOnAction(ActionEvent event) {

    }

    private void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            content.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load UI").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/TherapyProgram.fxml");
    }
}
