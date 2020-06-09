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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static road.Road.*;

public class MapController {
    public MainController mainController;
    ObservableList<String> crossroadList = FXCollections.observableArrayList(
            crossroads[1],crossroads[2],crossroads[3],
            crossroads[4],crossroads[5],crossroads[6],
            crossroads[7],crossroads[8],crossroads[9],
            crossroads[10],crossroads[11],crossroads[12]);

    public static boolean specialCase;
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

    public int begin;
    public int end;

    public void initialize(){
        crossroadPick.setValue(crossroadList.get(0));
        crossroadPick.setItems(crossroadList);
    }

    public void Update() throws IOException{
        //this.carsNumber.setText(TODO: get data from backend);
        //this.avgSpeed.setText("TODO: get data from backend");
    }

    public void updateTrafficOrder() throws IOException {
        var tmpCrossroad = this.crossroadPick.getValue();
        switch (tmpCrossroad.toString()){
            case "Rondo Matecznego":
                trafficOrder.setText(String.format("%s - %s", crossroads[1],crossroads[2]));
                begin = streetLightPoints[1];
                end = streetLightPoints[2];
                specialCase = false;
                draw();
                break;
            case "Kamieńskiego i Tischnera":
                trafficOrder.setText(String.format("%s - %s", crossroads[2],crossroads[3]));
                begin = streetLightPoints[2];
                end = streetLightPoints[3];
                specialCase = false;
                draw();
                break;
            case "Podgórze SKA":
                trafficOrder.setText(String.format("%s - %s", crossroads[3],crossroads[4]));
                begin = streetLightPoints[3];
                end = streetLightPoints[4];
                specialCase = false;
                draw();
                break;
            case "Kuklińskiego":
                trafficOrder.setText(String.format("%s - %s", crossroads[4],crossroads[5]));
                begin = streetLightPoints[4];
                end = streetLightPoints[5];
                specialCase = false;
                draw();
                break;
            case "Rondo Grzegórzeckie":
                trafficOrder.setText(String.format("%s - %s", crossroads[5],crossroads[6]));
                begin = streetLightPoints[5];
                end = streetLightPoints[6];
                specialCase = false;
                draw();
                break;
            case "Rondo Mogilskie":
                trafficOrder.setText(String.format("%s - %s", crossroads[6],crossroads[7]));
                begin = streetLightPoints[6];
                end = streetLightPoints[7];
                specialCase = false;
                draw();
                break;
            case "Wita Stwosza i Aleja 29 Listopada":
                trafficOrder.setText(String.format("%s - %s", crossroads[7],crossroads[8]));
                begin = streetLightPoints[7];
                end = streetLightPoints[8];
                specialCase = false;
                draw();
                break;
            case "Nowy Kleparz":
                trafficOrder.setText(String.format("%s - %s", crossroads[8],crossroads[9]));
                begin = streetLightPoints[8];
                end = streetLightPoints[9];
                specialCase = false;
                draw();
                break;
            case "Plac Inwalidów":
                trafficOrder.setText(String.format("%s - %s", crossroads[9],crossroads[10]));
                begin = streetLightPoints[9];
                end = streetLightPoints[10];
                specialCase = false;
                draw();
                break;
            case "Czarnowiejska i aleja Adama Mickiewicza":
                trafficOrder.setText(String.format("%s - %s", crossroads[10],crossroads[11]));
                begin = streetLightPoints[10];
                end = streetLightPoints[11];
                specialCase = false;
                draw();
                break;
            case "Reymonta i aleja Adama Mickiewicza":
                trafficOrder.setText(String.format("%s - %s", crossroads[11],crossroads[12]));
                begin = streetLightPoints[11];
                end = streetLightPoints[12];
                specialCase = false;
                draw();
                break;
            case "Stadion Cracovii":
                trafficOrder.setText(String.format("%s - %s", crossroads[12],crossroads[1]));
                begin = streetLightPoints[12];
                end = streetLightPoints[1];
                specialCase = true;
                draw();
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

    public void draw() throws IOException {
        Pane clockwiseRoad = new Pane();
        Pane counterClockwiseRoad = new Pane();

        GridPane table1 = new GridPane();
        GridPane table2 = new GridPane();
        Rectangle rectangleFirst;
        Rectangle rectangleSecond;

        if (specialCase){
            for (int i = begin; i < streetLightPoints[13]; i++) {
                if (road1[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road1[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table1.add(rectangleFirst, i, 1, 1, 1);
                table1.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = streetLightPoints[0]; i < streetLightPoints[1]; i++) {
                if (road1[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road1[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table1.add(rectangleFirst, i, 1, 1, 1);
                table1.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = 1733; i > begin - 1; i--) {
                if (road1[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road1[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table2.add(rectangleFirst, i, 1, 1, 1);
                table2.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = streetLightPoints[1]; i < streetLightPoints[0]; i++) {
                if (road1[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road1[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table1.add(rectangleFirst, i, 1, 1, 1);
                table1.add(rectangleSecond, i, 2, 1, 1);
            }
        }
        else {
            for (int i = begin; i < end; i++) {
                if (road1[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road1[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table1.add(rectangleFirst, i, 1, 1, 1);
                table1.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = end; i > begin - 1; i--) {
                if (road1[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road1[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table2.add(rectangleFirst, i, 1, 1, 1);
                table2.add(rectangleSecond, i, 2, 1, 1);
            }
        }

        clockwiseRoad.getChildren().add(table1);
        this.roadClockWise.setContent(clockwiseRoad);
        counterClockwiseRoad.getChildren().add(table2);
        this.roadCounterClockWise.setContent(counterClockwiseRoad);
    }

}
