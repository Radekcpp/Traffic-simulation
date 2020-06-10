package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import road.Road;

import java.io.IOException;

public class Main extends Application {

    @FXML
    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Road road=new Road();

        Parent root = FXMLLoader.load(Main.class.getResource("sample.fxml"));

        primaryStage.setTitle("Druga Obwodnica Krakowa");
        Scene scene = new Scene(root, 1200, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
