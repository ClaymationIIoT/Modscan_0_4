package com.clay.modscan;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    Controller c;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("hello-view.fxml"));
        c = loader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("Claymation Modscan");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            c.dispose();

        });

    }



    public static void main(String[] args) {
        launch();
    }
}