package main;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;
import road.DayTime;
import road.Weather;

public class MenuController {
    public MainController mainController;
    public Settings settings;
    ObservableList<String> weatherList = FXCollections.observableArrayList("Słońce", "Deszcz", "Śnieg");
    ObservableList<String> dayTimeList = FXCollections.observableArrayList("Rano", "Południe","Wieczór","Noc");

    @FXML
    public ChoiceBox weather;
    @FXML
    public ChoiceBox time;

    @FXML
    public void initialize(){
        weather.setValue("Słońce");
        weather.setItems(weatherList);
        time.setValue("Rano");
        time.setItems(dayTimeList);
    }

    @FXML
    public void Start(ActionEvent actionEvent) throws IOException {
        settings = new Settings();
        settings.updateSettings((String) weather.getValue(), (String)time.getValue());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Map.fxml"));
        Pane pane = loader.load();
        MapController mapController = loader.getController();
        mapController.mainController = this.mainController;
        mainController.layout.getChildren().clear();
        mainController.layout.getChildren().add(pane);
        mapController.Update();
    }

}
