package com.ijse.orm.mentalhealthcenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("The Serenity Mental Health Center");
        scene.getStylesheets().add(AppInitializer.class.getResource("/css/mainLayout.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/images/Serenity.jpg"));
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}