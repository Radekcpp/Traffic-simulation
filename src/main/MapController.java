package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

    public int beginCounterClockwise;
    public int endCounterClockwise;
    public int beginClockwise;
    public int endClockwise;

    public void initialize(){
        crossroadPick.setValue(crossroadList.get(0));
        crossroadPick.setItems(crossroadList);
    }

    public void Update() throws IOException{
        //this.carsNumber.setText(TODO: get data from backend);
        //this.avgSpeed.setText("TODO: get data from backend");
        draw();
    }

    public void updateTrafficOrder() throws IOException {
        var tmpCrossroad = this.crossroadPick.getValue();
        switch (tmpCrossroad.toString()){
            case "Rondo Matecznego":
                trafficOrder.setText(String.format("%s - %s", crossroads[1],crossroads[2]));
                beginCounterClockwise = streetLightPointsCounterClockwise[1];
                endCounterClockwise= streetLightPointsCounterClockwise[2];
                beginClockwise = streetLightPointsClockwise[11];
                endClockwise= streetLightPointsClockwise[12];
                specialCase = false;
                draw();
                break;
            case "Kamieńskiego i Tischnera":
                trafficOrder.setText(String.format("%s - %s", crossroads[2],crossroads[3]));
                beginCounterClockwise = streetLightPointsCounterClockwise[2];
                endCounterClockwise= streetLightPointsCounterClockwise[3];
                beginClockwise = streetLightPointsClockwise[10];
                endClockwise= streetLightPointsClockwise[11];
                specialCase = false;
                draw();
                break;
            case "Podgórze SKA":
                trafficOrder.setText(String.format("%s - %s", crossroads[3],crossroads[4]));
                beginCounterClockwise = streetLightPointsCounterClockwise[3];
                endCounterClockwise= streetLightPointsCounterClockwise[4];
                beginClockwise = streetLightPointsClockwise[9];
                endClockwise= streetLightPointsClockwise[10];
                specialCase = false;
                draw();
                break;
            case "Kuklińskiego":
                trafficOrder.setText(String.format("%s - %s", crossroads[4],crossroads[5]));
                beginCounterClockwise = streetLightPointsCounterClockwise[4];
                endCounterClockwise= streetLightPointsCounterClockwise[5];
                beginClockwise = streetLightPointsClockwise[8];
                endClockwise= streetLightPointsClockwise[9];
                specialCase = false;
                draw();
                break;
            case "Rondo Grzegórzeckie":
                trafficOrder.setText(String.format("%s - %s", crossroads[5],crossroads[6]));
                beginCounterClockwise = streetLightPointsCounterClockwise[5];
                endCounterClockwise= streetLightPointsCounterClockwise[6];
                beginClockwise = streetLightPointsClockwise[7];
                endClockwise= streetLightPointsClockwise[8];
                specialCase = false;
                draw();
                break;
            case "Rondo Mogilskie":
                trafficOrder.setText(String.format("%s - %s", crossroads[6],crossroads[7]));
                beginCounterClockwise = streetLightPointsCounterClockwise[6];
                endCounterClockwise= streetLightPointsCounterClockwise[7];
                beginClockwise = streetLightPointsClockwise[6];
                endClockwise= streetLightPointsClockwise[7];
                specialCase = false;
                draw();
                break;
            case "Wita Stwosza i Aleja 29 Listopada":
                trafficOrder.setText(String.format("%s - %s", crossroads[7],crossroads[8]));
                beginCounterClockwise = streetLightPointsCounterClockwise[7];
                endCounterClockwise= streetLightPointsCounterClockwise[8];
                beginClockwise = streetLightPointsClockwise[5];
                endClockwise= streetLightPointsClockwise[6];
                specialCase = false;
                draw();
                break;
            case "Nowy Kleparz":
                trafficOrder.setText(String.format("%s - %s", crossroads[8],crossroads[9]));
                beginCounterClockwise = streetLightPointsCounterClockwise[8];
                endCounterClockwise= streetLightPointsCounterClockwise[9];
                beginClockwise = streetLightPointsClockwise[4];
                endClockwise= streetLightPointsClockwise[5];
                specialCase = false;
                draw();
                break;
            case "Plac Inwalidów":
                trafficOrder.setText(String.format("%s - %s", crossroads[9],crossroads[10]));
                beginCounterClockwise = streetLightPointsCounterClockwise[9];
                endCounterClockwise= streetLightPointsCounterClockwise[10];
                beginClockwise = streetLightPointsClockwise[3];
                endClockwise= streetLightPointsClockwise[4];
                specialCase = false;
                draw();
                break;
            case "Czarnowiejska i aleja Adama Mickiewicza":
                trafficOrder.setText(String.format("%s - %s", crossroads[10],crossroads[11]));
                beginCounterClockwise = streetLightPointsCounterClockwise[10];
                endCounterClockwise= streetLightPointsCounterClockwise[11];
                beginClockwise = streetLightPointsClockwise[2];
                endClockwise= streetLightPointsClockwise[3];
                specialCase = false;
                draw();
                break;
            case "Reymonta i aleja Adama Mickiewicza":
                trafficOrder.setText(String.format("%s - %s", crossroads[11],crossroads[12]));
                beginCounterClockwise = streetLightPointsCounterClockwise[11];
                endCounterClockwise= streetLightPointsCounterClockwise[12];
                beginClockwise = streetLightPointsClockwise[1];
                endClockwise= streetLightPointsClockwise[2];
                specialCase = false;
                draw();
                break;
            case "Stadion Cracovii":
                trafficOrder.setText(String.format("%s - %s", crossroads[12],crossroads[1]));
                beginCounterClockwise = streetLightPointsCounterClockwise[12];
                endCounterClockwise= streetLightPointsCounterClockwise[1];
                beginClockwise = streetLightPointsClockwise[12];
                endClockwise= streetLightPointsClockwise[1];
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
            for (int i = beginClockwise; i < streetLightPointsClockwise[13]; i++) {
                if (road2[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road2[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table2.add(rectangleFirst, i, 1, 1, 1);
                table2.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = streetLightPointsClockwise[0]; i < streetLightPointsClockwise[1]; i++) {
                if (road2[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road2[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table2.add(rectangleFirst, i, 1, 1, 1);
                table2.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = streetLightPointsCounterClockwise[1]; i > streetLightPointsCounterClockwise[0]; i--) {
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

            for (int i = 1732; i > beginCounterClockwise - 1; i--) {
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
            for (int i = beginClockwise; i < endClockwise; i++) {
                if (road2[i][0].getisCar())
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleFirst = new Rectangle(3 * 21, 3 * 21, Color.BLACK);
                if (road2[i][1].getisCar())
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.GREEN);
                else
                    rectangleSecond = new Rectangle(3 * 21, 3 * 21, Color.BLACK);

                table2.add(rectangleFirst, i, 1, 1, 1);
                table2.add(rectangleSecond, i, 2, 1, 1);
            }

            for (int i = endCounterClockwise; i > beginCounterClockwise- 1; i--) {
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

        clockwiseRoad.getChildren().add(table1);
        this.roadClockWise.setContent(clockwiseRoad);
        counterClockwiseRoad.getChildren().add(table2);
        this.roadCounterClockWise.setContent(counterClockwiseRoad);
    }

}
