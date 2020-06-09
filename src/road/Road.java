package road;
import java.util.Random;

import car.CarInstance;
import cell.*;

import static java.lang.Math.max;


public class Road {

    Cell[][] road1 = new Cell[1733][2];
    Cell[][] road2 = new Cell[1733][2];
    public static int[] streetLightPointsCounterClockwise = new int[]{0,212,289,471,600,850,982,1174,1282,1407,1457,1493,1563,1733};
    public static int[] streetLightPointsClockwise = new int[]{0,170,240,276,326,451,559,751,883,1133,1262,1444,1521,1733};
    public static String[] crossroads={"","Rondo Matecznego","Kamieńskiego i Tischnera","Podgórze SKA","Kuklińskiego",
                            "Rondo Grzegórzeckie","Rondo Mogilskie","Wita Stwosza i Aleja 29 Listopada","Nowy Kleparz",
                            "Plac Inwalidów","Czarnowiejska i aleja Adama Mickiewicza","Reymonta i aleja Adama Mickiewicza",
                            "Stadion Cracovii", ""};
    public static Weather weather;
    public static DayTime dayTime;
    public int maxCapacity;

    public Road()
    {
        Random rand = new Random();
        int weatherInt = rand.nextInt(3);
        int dayTimeInt = rand.nextInt(3);
        int nextLight1 = 1;
        int nextLight2 = 1;

        switch (weatherInt) {
            case 1:
                weather = Weather.sun;
            case 2:
                weather = Weather.rain;
            case 3:
                weather = Weather.snow;
        }

        switch (dayTimeInt){
            case 1:
                dayTime = DayTime.Morning;
                maxCapacity = 350;
            case 2:
                dayTime = DayTime.Noon;
                maxCapacity = 700;
            case 3:
                dayTime = DayTime.Midnight;
                maxCapacity = 500;
            case 4:
                dayTime = DayTime.Night;
                maxCapacity = 300;
        }

        for (var counter = 0; counter < 1733; counter++) {
            road1[counter][0] = new Cell(RoadType.Basic, streetLightPointsCounterClockwise[nextLight1] - counter, nextLight1);
            road1[counter][1] = new Cell(RoadType.Basic, streetLightPointsCounterClockwise[nextLight1] - counter, nextLight1);
            road2[counter][0] = new Cell(RoadType.Basic, counter - streetLightPointsClockwise[nextLight2], nextLight2);
            road2[counter][1] = new Cell(RoadType.Basic, counter - streetLightPointsClockwise[nextLight2], nextLight2);

            if (counter>1281 && counter<1563) {
                road1[counter][0].setSpeedLimit(2);
                road1[counter][1].setSpeedLimit(2);
                road2[counter][0].setSpeedLimit(2);
                road2[counter][1].setSpeedLimit(2);
            }

            if (streetLightPointsCounterClockwise[nextLight1]==counter)
                nextLight1++;

            if (streetLightPointsClockwise[nextLight2]==counter)
                nextLight2++;
        }

        for (var counter = 1; counter < (streetLightPointsCounterClockwise.length-1); counter++) {
            road1[streetLightPointsCounterClockwise[counter]][0].setType(RoadType.Lights);
            road1[streetLightPointsCounterClockwise[counter]][1].setType(RoadType.Lights);
            road2[streetLightPointsClockwise[counter]][0].setType(RoadType.Lights);
            road2[streetLightPointsClockwise[counter]][1].setType(RoadType.Lights);
            road1[streetLightPointsCounterClockwise[counter]+1][0].setType(RoadType.Crossroad);
            road1[streetLightPointsCounterClockwise[counter]+1][1].setType(RoadType.Crossroad);
            road2[streetLightPointsClockwise[counter]+1][0].setType(RoadType.Crossroad);
            road2[streetLightPointsClockwise[counter]+1][1].setType(RoadType.Crossroad);
        }
    }

    void ResetFlags(){ //reset all flags to not moved for the next iteration of method move
        for (int i = 0; i<1733; i++){
            for (int j = 0; j<1; j++){
                road1[i][j].setMoved(false);
                road2[i][j].setMoved(false);
            }
        }
    }

    boolean overtake (Cell[][] road, int posX, int posY){ //method to overtake, return true if it was successful, false if it wasn't and car didn't move as an arguments it takes indexes of current positions
        int adjacentLane;
        int velocity = road[posX][posY].getCar().getSpeed();
        if (posY  == 0){   //if car is on right lane
            adjacentLane = 1;
        }
        else{   //if car is on left lane - pos_y can be only 0 or 1
            adjacentLane = 0;
        }
        if (road[posX][adjacentLane].getisCar()){ //if the adjacent cell is taken then return false
            return false;
        }
        else{
            for (int i = 1; i<=velocity; i++){
                if (road[posX+i][adjacentLane].getisCar()){
                    return false;
                }
            }
            road[posX][posY].setMoved(true);
            road[posX][posY].swapCar(road[posX+velocity][adjacentLane]);
        }
        return true;
    }


    void stopOnRed (Cell[][] road, int posX, int posY){
        int velocity = road[posX][posY].getCar().getSpeed();
        for (int i = 0; i<= velocity; i++){
            if(road[posX+i][posY].getType() == RoadType.Lights && !road[posX+i][posY].getTrafficLights().getLights_color()){
                road[posX][posY].setMoved(true);
                road[posX][posY].swapCar(road[posX+i][posY]);
                road[posX][posY].getCar().setSpeed(0);
            }
        }
    }

    void CreateCars (int quantity){  //create start amount of cars
        Random position = new Random();
        int carPosition = position.nextInt(1733);
        for (int i = 0; i<quantity; i++){
            while (road1[carPosition][0].getisCar()) {
                carPosition = position.nextInt(1733);
            }
            road1[carPosition][0].setisCar(true);
            road1[carPosition][0].setCar(new CarInstance());
        }
    }

    void move() { //function which moves car on the whole bypass, temporary version moving them in only one direction
        int k = 0;
        int velocity;
        for (int i = 0; i < 1728; i++) {       //forward movement in general, without traffic lights
            if (road1[i][0].getMoved()){
                continue;
            }
            else {
                if (road1[i][0].getisCar()) {
                    velocity = road1[i][0].getCar().getSpeed();
                    if (velocity < road1[i][0].getCar().getMaxSpeed()) velocity++;
                    for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                        if (road1[i + v][0].getisCar()) {
                            road1[i][0].getCar().setSpeed(max(0, v - 2));
                            velocity = max(0, v - 2);
                            break;
                        }

                }
                if(road1[i][0].getDistanceFromLights()<=10 && road1[i][0].getCar().getDestination()==road1[i][0].getNextCrossroad()) {
                    //FUNCTION TRY TO CHANGE LANE
                }//change Lane to get to outer so car can leave
                if(road1[i][1].getDistanceFromLights()<=velocity && road1[i][0].getCar().getDestination()==road1[i][0].getNextCrossroad()) {//{ in future - LEAVE BYPASS}

                        // Random slow with given probability - needs to be done
                    }
                    if (velocity != 0) {
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[i + velocity][0]);//swap cells on positions i and i+velocity
                    }
                }
            }
        }


        for (int i = 1728; i < 1733; i++) {       //forward movement in edge case (cars near end of bound, without traffic lights
            if (road1[i][0].getisCar()) {
                velocity = road1[i][0].getCar().getSpeed();
                if (velocity < road1[i][0].getCar().getMaxSpeed()) velocity++;
                if (i + velocity + 1 <= 1732) {      //edge case without going through
                    for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                        if (road1[i + v][0].getisCar()) {
                            road1[i][0].getCar().setSpeed(max(0, v - 2));
                            velocity = max(0, v - 2);
                            break;
                        }
                    }
                    if (velocity != 0) {
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[i + velocity][0]);//swap cells on positions i and i+velocity
                    }
                }
                else if (i + velocity == 1732) {                                                 //edge case without going through nr 2
                    for (int v = 1; v <= velocity; v++) { //checking if all the cells we want to go through are free
                        if (road1[i + v][0].getisCar()) {
                            road1[i][0].getCar().setSpeed(max(0, v - 2));
                            velocity = max(0, v - 2);
                            break;
                        }

                    }
                    if(velocity!=0 && i+velocity<1732) {
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[i+velocity][0]);
                        break;
                    }
                    if(velocity!=0 && !road1[0][0].getisCar()) {
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[1732][0]);
                        break;
                    }
                    if (road1[0][0].getisCar()){
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[1731][0]);
                    }
                    else {
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[1732][0]);
                    }
                }
                else {                                  // edge case with going through
                    if(!road1[0][0].getisCar() && !road1[1][0].getisCar()){
                        road1[i][0].setMoved(true);
                        road1[i][0].swapCar(road1[0][0]);
                    }
                }
            }
        }
    }
}
