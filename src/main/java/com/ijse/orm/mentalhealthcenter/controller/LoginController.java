package com.ijse.orm.mentalhealthcenter.controller;

import com.ijse.orm.mentalhealthcenter.AppInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox checkShowPassword;

    @FXML
    private ComboBox<?> cmbRole;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("The Serenity Mental Health Center");
        scene.getStylesheets().add(AppInitializer.class.getResource("/css/mainLayout.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/images/Serenity.jpg"));
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }



    @FXML
    void checkShowPasswordOnAction(ActionEvent event) {

    }

}
