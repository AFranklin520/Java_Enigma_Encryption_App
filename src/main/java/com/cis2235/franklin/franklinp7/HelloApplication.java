/*
Anthony Franklin afranklin18@cnm.edu
FranklinP7
03/22/2022
HelloApplication.java
*/
package com.cis2235.franklin.franklinp7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("franklinP8.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 635, 440);
        stage.setTitle("Franklin, Anthony P8 - CIS 2235 Java I");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}