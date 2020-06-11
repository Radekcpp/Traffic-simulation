package main;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;
import road.DayTime;
import road.Road;
import road.Weather;

public class MenuController {
    public MainController mainController;
    public MapController mapController;
    public static Settings settings;

    ObservableList<String> weatherList = FXCollections.observableArrayList("Słońce", "Deszcz", "Śnieg");
    ObservableList<String> dayTimeList = FXCollections.observableArrayList("Rano", "Południe","Wieczór","Noc");


    @FXML
    public TextField Tischnera;
    @FXML
    public TextField kukl;
    @FXML
    public TextField SKA;
    @FXML
    public TextField RMa;
    @FXML
    public TextField RG;
    @FXML
    public TextField RMo;
    @FXML
    public TextField WA;
    @FXML
    public TextField NK;
    @FXML
    public TextField PI;
    @FXML
    public TextField Cz;
    @FXML
    public TextField R;
    @FXML
    public TextField SC;
    @FXML
    public ChoiceBox weather;
    @FXML
    public ChoiceBox time;
    @FXML
    public CheckBox defaultVariables;

    @FXML
    public void initialize(){
        weather.setValue("Słońce");
        weather.setItems(weatherList);
        time.setValue("Rano");
        time.setItems(dayTimeList);
        Tischnera.appendText("30");
        kukl.appendText("30");
        SKA.appendText("30");
        RMa.appendText("30");
        RG.appendText("30");
        RMo.appendText("30");
        WA.appendText("30");
        NK.appendText("30");
        PI.appendText("30");
        Cz.appendText("30");
        R.appendText("30");
        SC.appendText("30");
    }

    @FXML
    public void Start(ActionEvent actionEvent) throws IOException, InterruptedException {
        int[] incomeCars=new int[13];
        for (int i = 0; i<13; i++){
            incomeCars[i] = 0;
        }

        if (defaultVariables.isSelected()) {
            incomeCars[1] = Integer.parseInt((RMa.getText()));
            incomeCars[2] = Integer.parseInt((Tischnera.getText()));
            incomeCars[3] = Integer.parseInt((SKA.getText()));
            incomeCars[4] = Integer.parseInt((kukl.getText()));
            incomeCars[5] = Integer.parseInt((RG.getText()));
            incomeCars[6] = Integer.parseInt((RMo.getText()));
            incomeCars[7] = Integer.parseInt((WA.getText()));
            incomeCars[8] = Integer.parseInt((NK.getText()));
            incomeCars[9] = Integer.parseInt((PI.getText()));
            incomeCars[10] = Integer.parseInt((Cz.getText()));
            incomeCars[11] = Integer.parseInt((R.getText()));
            incomeCars[12] = Integer.parseInt((SC.getText()));
        }

        settings = new Settings();
        settings.updateSettings((String) weather.getValue(), (String)time.getValue(), incomeCars, defaultVariables.isSelected());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Map.fxml"));
        Pane pane = loader.load();
        MapController mapController = loader.getController();
        mapController.mainController = this.mainController;
        this.mapController = mapController;
        mainController.layout.getChildren().clear();
        mainController.layout.getChildren().add(pane);
        mapController.road = new Road();
        mapController.road.startSimulation(mapController.road);
        mapController.Update();
    }

}
