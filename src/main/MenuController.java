package main;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MenuController {
    public MainController mainController;

    @FXML
    public void Start(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Map.fxml"));
        Pane pane = loader.load();
        MapController mapController = loader.getController();
        mapController.mainController = this.mainController;
        mainController.layout.getChildren().clear();
        mainController.layout.getChildren().add(pane);
    }
}
