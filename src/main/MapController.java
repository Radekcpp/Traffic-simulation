package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import static road.Road.crossroads;

public class MapController {
    public MainController mainController;
    ObservableList<String> crossroadList = FXCollections.observableArrayList(
            crossroads[1],crossroads[2],crossroads[3],
            crossroads[4],crossroads[5],crossroads[6],
            crossroads[7],crossroads[8],crossroads[9],
            crossroads[10],crossroads[11],crossroads[12]);

    @FXML
    public ChoiceBox crossroadPick;
    @FXML
    public ScrollPane roadClockWise;
    @FXML
    public ScrollPane roadCounterClockWise;
    @FXML
    public Label carsNumber;
    @FXML
    public Label avgSpeed;
    @FXML
    public Label trafficOrder;

    @FXML
    public void initialize(){
        crossroadPick.setValue(crossroadList.get(0));
        crossroadPick.setItems(crossroadList);
    }

    public void Update() throws IOException{
        //this.carsNumber.setText(TODO: get data from backend);
        //this.avgSpeed.setText("TODO: get data from backend");
    }

    public void updateTrafficOrder(){
        var tmpCrossroad = this.crossroadPick.getValue();
        switch (tmpCrossroad.toString()){
            case "Rondo Matecznego":
                trafficOrder.setText(String.format("%s - %s", crossroads[1],crossroads[2]));
                break;
            case "Kamieńskiego i Tischnera":
                trafficOrder.setText(String.format("%s - %s", crossroads[2],crossroads[3]));
                break;
            case "Podgórze SKA":
                trafficOrder.setText(String.format("%s - %s", crossroads[3],crossroads[4]));
                break;
            case "Kuklińskiego":
                trafficOrder.setText(String.format("%s - %s", crossroads[4],crossroads[5]));
                break;
            case "Rondo Grzegórzeckie":
                trafficOrder.setText(String.format("%s - %s", crossroads[5],crossroads[6]));
                break;
            case "Rondo Mogilskie":
                trafficOrder.setText(String.format("%s - %s", crossroads[6],crossroads[7]));
                break;
            case "Wita Stwosza i Aleja 29 Listopada":
                trafficOrder.setText(String.format("%s - %s", crossroads[7],crossroads[8]));
                break;
            case "Nowy Kleparz":
                trafficOrder.setText(String.format("%s - %s", crossroads[8],crossroads[9]));
                break;
            case "Plac Inwalidów":
                trafficOrder.setText(String.format("%s - %s", crossroads[9],crossroads[10]));
                break;
            case "Czarnowiejska i aleja Adama Mickiewicza":
                trafficOrder.setText(String.format("%s - %s", crossroads[10],crossroads[11]));
                break;
            case "Reymonta i aleja Adama Mickiewicza":
                trafficOrder.setText(String.format("%s - %s", crossroads[11],crossroads[12]));
                break;
            case "Stadion Cracovii":
                trafficOrder.setText(String.format("%s - %s", crossroads[12],crossroads[1]));
                break;
        }
    }

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
