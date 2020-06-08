package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MapController {
    public MainController mainController;

    @FXML
    ScrollPane roadClockWise;
    @FXML
    ScrollPane roadCounterClockWise;
    @FXML
    Pane statistics;

    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Menu.fxml"));
        Pane pane = loader.load();
        MenuController menuController = loader.getController();
        menuController.mainController = this.mainController;
        mainController.layout.getChildren().clear();
        mainController.layout.getChildren().add(pane);
    }
}
