package road;
import java.util.Random;

import car.CarInstance;
import cell.*;
import main.Settings;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Road {
    public static int deletedCars = 0;
    public static int enteredCars = 0;
    private int timer;
    public static int[] streetLightPointsCounterClockwise = new int[]{0,212,289,471,600,850,982,1174,1282,1407,1457,1493,1563,1733};
    public static int[] streetLightPointsClockwise = new int[]{0,170,240,276,326,451,559,751,883,1133,1262,1444,1521,1733};
    public static Settings settings;
    public static Cell[][] road1 = new Cell[1733][2];
    public static Cell[][] road2 = new Cell[1733][2];
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
        int nextLight1 = 1;
        int nextLight2 = 1;
        this.timer = 0;
        for (var counter = 0; counter < 1733; counter++) {
            road1[counter][0] = new Cell(RoadType.Basic, streetLightPointsCounterClockwise[nextLight1] - counter, nextLight1);
            road1[counter][1] = new Cell(RoadType.Basic, streetLightPointsCounterClockwise[nextLight1] - counter, nextLight1);
            road2[counter][0] = new Cell(RoadType.Basic, streetLightPointsClockwise[nextLight2] - counter, nextLight2);
            road2[counter][1] = new Cell(RoadType.Basic, streetLightPointsClockwise[nextLight2] - counter, nextLight2);

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
            road1[streetLightPointsCounterClockwise[counter]][0].setTrafficLights(new TrafficLights(true));
            road1[streetLightPointsCounterClockwise[counter]][1].setType(RoadType.Lights);
            road1[streetLightPointsCounterClockwise[counter]][1].setTrafficLights(new TrafficLights(true));
            road2[streetLightPointsClockwise[counter]][0].setType(RoadType.Lights);
            road2[streetLightPointsClockwise[counter]][0].setTrafficLights(new TrafficLights(true));
            road2[streetLightPointsClockwise[counter]][1].setType(RoadType.Lights);
            road2[streetLightPointsClockwise[counter]][1].setTrafficLights(new TrafficLights(true));
            road1[streetLightPointsCounterClockwise[counter]+1][0].setType(RoadType.Crossroad);
            road1[streetLightPointsCounterClockwise[counter]+1][0].setCrossroad(new Crossroad(30));
            road1[streetLightPointsCounterClockwise[counter]+1][1].setType(RoadType.Crossroad);
            road1[streetLightPointsCounterClockwise[counter]+1][1].setCrossroad(new Crossroad(30));
            road2[streetLightPointsClockwise[counter]+1][0].setType(RoadType.Crossroad);
            road2[streetLightPointsClockwise[counter]+1][0].setCrossroad(new Crossroad(30));
            road2[streetLightPointsClockwise[counter]+1][1].setType(RoadType.Crossroad);
            road2[streetLightPointsClockwise[counter]+1][1].setCrossroad(new Crossroad(30));
        }
    }
    public void getAndSetUserValues(){
        int positionClockwise;
        int positionCounterClockwise;
        int j = 12;
        if (!Settings.defaultValues){
            for (int i = 1; i<13; i++){
                positionClockwise = streetLightPointsClockwise[i]+1;
                positionCounterClockwise = streetLightPointsCounterClockwise[i]+1;
                road1[positionCounterClockwise][0].getCrossroad().setAmountOfCars(Settings.incomeCars[i]);
                road2[positionClockwise][0].getCrossroad().setAmountOfCars(Settings.incomeCars[j]);
                j--;
            }
        }
    }
    public void CarEntrance (Cell[][] road, int roadNumber){ //cars entrance on crossroads, roadNumber defines which road is given a argument
        int position;
        int amountOfCars;
        for (int i = 1; i<13; i++) {
            if(roadNumber == 1) {
                position = streetLightPointsCounterClockwise[i];
            }
            else {
                position = streetLightPointsClockwise[i];
            }
            if(!road[position][0].getTrafficLights().getLights_color()){
                amountOfCars = road[position+1][0].getCrossroad().getAmountOfCars();
                if(amountOfCars >= 1){
                    if(!road[position+1][0].getisCar()){
                        road[position+1][0].setisCar(true);
                        road[position+1][0].setCar(new CarInstance());
                        road[position+1][0].getCrossroad().setAmountOfCars(amountOfCars-1);
                        Road.enteredCars++;
                    }
                }
                amountOfCars = road[position+1][0].getCrossroad().getAmountOfCars();
                if(amountOfCars >= 1) {
                    if (!road[position][1].getTrafficLights().getLights_color()) {
                        road[position + 1][1].setisCar(true);
                        road[position + 1][1].setCar(new CarInstance());
                        amountOfCars = road[position + 1][0].getCrossroad().getAmountOfCars();
                        road[position + 1][0].getCrossroad().setAmountOfCars(amountOfCars - 1);
                        Road.enteredCars++;
                    }
                }
            }
        }
    }


    public void ResetFlags(){ //reset all flags to not moved for the next iteration of method move
        for (int i = 0; i<1733; i++){
            for (int j = 0; j<2; j++){
                road1[i][j].setMoved(false);
                road2[i][j].setMoved(false);
            }
        }
    }

    public void ChangeTimer(){
        int timer0Road1, timer1Road1, timer0Road2, timer1Road2;
        this.timer++;
        if(this.timer == 100){
            this.timer = 1;
        }
        for (int i = 1; i<13; i++){
            timer0Road1 = road1[streetLightPointsCounterClockwise[i]][0].getTrafficLights().getTimer();
            timer0Road2 = road2[streetLightPointsClockwise[i]][0].getTrafficLights().getTimer();
            road1[streetLightPointsCounterClockwise[i]][1].getTrafficLights().setTimer(timer0Road1);
            road2[streetLightPointsClockwise[i]][1].getTrafficLights().setTimer(timer0Road2);
            timer1Road1 = road1[streetLightPointsCounterClockwise[i]][1].getTrafficLights().getTimer();
            timer1Road2 = road2[streetLightPointsClockwise[i]][1].getTrafficLights().getTimer();
            if(this.timer%timer0Road1 == 0){
                road1[streetLightPointsCounterClockwise[i]][0].getTrafficLights().Change_color();
            }
            if(this.timer%timer1Road1 == 0){
                road1[streetLightPointsCounterClockwise[i]][1].getTrafficLights().Change_color();
            }
            if(this.timer%timer0Road2 == 0){
                road2[streetLightPointsClockwise[i]][0].getTrafficLights().Change_color();
            }
            if(this.timer%timer1Road2 == 0){
                road2[streetLightPointsClockwise[i]][1].getTrafficLights().Change_color();
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
            for (int i = 1; i<=velocity+1; i++){
                if (road[posX+i][adjacentLane].getisCar()){
                    return false;
                }
            }
            road[posX][posY].swapCar(road[posX][posY],road[posX+velocity][adjacentLane]);
        }
        return true;
    }


    void stopOnRed (Cell[][] road, int posX, int posY){
        int velocity = road[posX][posY].getCar().getSpeed();
        for (int i = 1; i<= velocity; i++){
            if(road[posX+i][posY].getType() == RoadType.Lights && !road[posX+i][posY].getTrafficLights().getLights_color()){
                road[posX][posY].setMoved(true);
                road[posX][posY].getCar().setSpeed(0);
                if (posX+1-1 != posX)
                    road[posX][posY].swapCar(road[posX][posY],road[posX+i-1][posY]);
            }
        }
    }

    void CreateCars (int quantity,Cell[][] road, int lane){  //create starting amount of cars
        Random position = new Random();
        int carPosition = (position.nextInt(866) * 2) + 1;
        for (int i = 0; i<quantity; i++){
            while (road[carPosition][lane].getisCar()) {
                carPosition = (position.nextInt(866) * 2) + 1;
            }
            road[carPosition][lane].setisCar(true);
            road[carPosition][lane].setCar(new CarInstance());
        }
    }

    void slowDown(Cell[][] road, int i, int j){
        for (int vel = 1; vel <= road[i][j].getCar().getSpeed(); vel++){
            if (road[i+vel][j].getisCar()){
                road[i][j].getCar().setSpeed(vel-1);
            }
        }
    }

    void slowDown(Cell[][] road, int i, int j, boolean specialCase){
        var toIterate = road[i][j].getCar().getSpeed();
        for (var vel = 1; vel < 1733-i; vel++){
            if (road[i+vel][j].getisCar()){
                road[i][j].getCar().setSpeed(vel-1);
                toIterate = 0;
                break;
            }
            toIterate--;
        }
        for (int vel = 0; vel < toIterate; vel++){
            if (road[0+vel][j].getisCar()){
                road[i][j].getCar().setSpeed(vel+1);
                break;
            }
        }
    }

    void deleteCar(Cell cell){
        cell.setCar(null);
        cell.setisCar(false);
        Road.deletedCars++;
    }

    public void move(Cell[][] road){
        for (int j=0; j<2; j++){
            for (int i=0; i<1730; i++){
                Cell cell = road[i][j];

                if (!road[i][j].getisCar())
                    continue;

                if (road[i][j].getMoved())
                    continue;

                CarInstance car = cell.getCar();
                car.setSpeed(min(car.getSpeed()+1, car.getMaxSpeed()));

                slowDown(road,i,j);

                if (cell.getDistanceFromLights() <= car.getSpeed()){
                        if (car.getDestination() == cell.getNextCrossroad()){
                            if (road[i+cell.getDistanceFromLights()][j].getTrafficLights().getLights_color())
                                deleteCar(cell);
                            else
                                stopOnRed(road, i, j);
                        }
                        else{
                            if (road[i+cell.getDistanceFromLights()][j].getTrafficLights().getLights_color())
                                cell.swapCar(cell, road[i + car.getSpeed()][j]);
                            else
                                stopOnRed(road, i, j);
                        }
                    continue;
                }
                else {
                    slowDown(road, i, j);
                    cell.swapCar(cell, road[i + car.getSpeed()][j]);
                }
            }
        }

        //EDGE CASE
        for (int j=0; j<2; j++){
            for (int i=1730; i<1733; i++){
                Cell cell = road[i][j];

                if (!road[i][j].getisCar())
                    continue;

                if (road[i][j].getMoved())
                    continue;

                CarInstance car = cell.getCar();
                car.setSpeed(min(car.getSpeed()+1, car.getMaxSpeed()));

                if (i+car.getSpeed()>1732){
                    var specialCase = true;
                        slowDown(road,i,j,specialCase);
                    if (i+car.getSpeed()<1733)
                        cell.swapCar(cell,road[i+car.getSpeed()][j]);
                    else
                        cell.swapCar(cell,road[i+car.getSpeed()-1733][j]);
                }
                else{
                    slowDown(road,i,j);
                    cell.swapCar(cell, road[i + car.getSpeed()][j]);
                }
            }
        }
    }

//    public boolean swapLanes(Cell[][] road, int i, int j){
//        int adjacentLane;
//        if (j==1)
//            adjacentLane = 0;
//        else
//            adjacentLane = 1;
//
//        if (road[i][adjacentLane].getisCar()){
//            return false;
//        }
//        road[i][j].swapCar(road[i][j],road[i][adjacentLane]);
//        if (isSlowedDown())
//        return true;
//    }

    public String carsOnRoad(){
        Integer counter = 0;

        for (int i = 0; i < 2; i++){
            for (int j=0; j<1733; j++){
                if (road1[j][i].getisCar())
                    counter++;
                if (road2[j][i].getisCar())
                    counter++;
            }
        }
        return counter.toString();
    }

    public String averageSpeed(){
        Integer counter = 0;
        Integer speed = 0;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j<1733; j++){
                    if (road1[j][i].getisCar()) {
                        counter++;
                        speed += road1[j][i].getCar().getSpeed();
                    }
                if (road2[j][i].getisCar()) {
                    counter++;
                    speed += road2[j][i].getCar().getSpeed();
                }
            }
        }
        double finalValue = (double)speed/counter * 27;

        return String.format("%.2f", finalValue);
    }

    public void startSimulation(Road road){
        road.getAndSetUserValues();
        var number = Settings.carNumber/4;
        road.CreateCars(number, road1, 0);
        road.CreateCars(number, road1, 1);
        road.CreateCars(number, road2, 0);
        road.CreateCars(number, road2, 1);
    }

}
