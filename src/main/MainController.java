package main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    public StackPane layout;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Menu.fxml"));
        Pane pane = loader.load();
        MenuController menuController = loader.getController();
        menuController.mainController = this;
        layout.getChildren().clear();
        layout.getChildren().add(pane);
    }
}
