package road;
import java.util.Random;

import car.CarInstance;
import cell.*;
import main.Settings;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Road {

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
    void CarEntrance (Cell[][] road, int roadNumber){ //cars entrance on crossroads, roadNumber defines which road is given a argument
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
                if(amountOfCars > 1){
                    if(!road[position+1][0].getisCar()){
                        road[position+1][0].setisCar(true);
                        road[position+1][0].setCar(new CarInstance());
                        road[position+1][0].getCrossroad().setAmountOfCars(amountOfCars-1);
                    }
                }
                amountOfCars = road[position+1][0].getCrossroad().getAmountOfCars();
                if(amountOfCars > 1) {
                    if (!road[position + 1][1].getTrafficLights().getLights_color()) {
                        road[position + 1][0].setisCar(true);
                        road[position + 1][0].setCar(new CarInstance());
                        amountOfCars = road[position + 1][0].getCrossroad().getAmountOfCars();
                        road[position + 1][0].getCrossroad().setAmountOfCars(amountOfCars - 1);
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
                else
                    cell.swapCar(cell, road[i + car.getSpeed()][j]);
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

    public void moveRoad1() { //function which moves car on the whole bypass, temporary version moving them in only one direction
        int k = 0;
        int velocity;
        for(int j=0;j<2;j++) {
            for (int i = 0; i < 1728; i++) {
                if (road1[i][j].getMoved()) {
                    continue;
                } else {
                    if (road1[i][j].getisCar()) {
                        velocity = road1[i][j].getCar().getSpeed();
                        if (velocity < road1[i][j].getCar().getMaxSpeed())
                            velocity++;
                        for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                            if (road1[i + v][j].getisCar()) {
                                road1[i][j].getCar().setSpeed(max(0, v - 2));
                                velocity = max(0, v - 2);
                                continue;
                            }
                        }
                        road1[i][j].getCar().setSpeed(velocity);
                        if (j == 0 && road1[i][0].getDistanceFromLights() <= 10 && road1[i][0].getisCar() && road1[i][0].getCar().getDestination() == road1[i][0].getNextCrossroad()) {
                            // try to change Lane to get to outer so car can leave
                            if(!overtake(road1,i,0)) {
                                road1[i][0].getCar().setSpeed(1);
                                velocity = 1;
                            }
                        }
                        /*if (road1[i][1].getDistanceFromLights() <= velocity && road1[i][1].getCar().getDestination() == road1[i][1].getNextCrossroad()) {
                            //{ in future - LEAVE BYPASS}
                        }*/
                        if (velocity > road1[i][j].getDistanceFromLights()) {
                            stopOnRed(road1, i, j);
                            continue;
                        }

                        if (velocity != 0) {
                            road1[i][j].swapCar(road1[i][j],road1[i + velocity][j]);//swap cells on positions i and i+velocity
                        }
                    }
                }
            }


            for (int i = 1728; i < 1733; i++) {       //forward movement in edge case (cars near end of bound, without traffic lights
                if (road1[i][j].getisCar()) {
                    velocity = road1[i][j].getCar().getSpeed();
                    if (velocity < road1[i][j].getCar().getMaxSpeed()) velocity++;
                    if (i + velocity + 1 <= 1732) {      //edge case without going through
                        for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                            if (road1[i + v][j].getisCar()) {
                                road1[i][j].getCar().setSpeed(max(0, v - 2));
                                velocity = max(0, v - 2);
                                break;
                            }
                        }
                        road1[i][j].getCar().setSpeed(velocity);
                        if (velocity != 0) {
                            road1[i][j].swapCar(road1[i][j],road1[i + velocity][j]);//swap cells on positions i and i+velocity
                        }
                    } else if (i + velocity == 1732) {                                                 //edge case without going through nr 2
                        for (int v = 1; v <= velocity; v++) { //checking if all the cells we want to go through are free
                            if (road1[i + v][j].getisCar()) {
                                road1[i][j].getCar().setSpeed(max(0, v - 2));
                                velocity = max(0, v - 2);
                                break;
                            }

                        }
                        road1[i][j].getCar().setSpeed(velocity);
                        if (velocity != 0 && i + velocity < 1732) {
                            road1[i][j].swapCar(road1[i][j],road1[i + velocity][j]);
                            break;
                        }
                        if (velocity != 0 && !road1[0][j].getisCar()) {
                            road1[i][j].swapCar(road1[i][j],road1[1732][j]);
                            break;
                        }
                        if (road1[0][j].getisCar()) {
                            road1[i][j].swapCar(road1[i][j],road1[1731][j]);
                            break;
                        } else {

                            road1[i][j].swapCar(road1[i][j],road1[1732][j]);
                            break;
                        }
                    } else {                                  // edge case with going through
                        if (!road1[0][j].getisCar() && !road1[1][j].getisCar()) {
                            road1[i][j].swapCar(road1[i][j],road1[0][j]);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void moveRoad2() { //function which moves car on the whole bypass, temporary version moving them in only one direction
        int k = 0;
        int velocity;
        for(int j=0;j<2;j++) {
            for (int i = 0; i < 1728; i++) {       //forward movement in general, without traffic lights
                if (road2[i][j].getMoved()) {
                    continue;
                } else {
                    if (road2[i][j].getisCar()) {
                        velocity = road2[i][j].getCar().getSpeed();
                        if (velocity < road2[i][j].getCar().getMaxSpeed()) velocity++;
                        for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                            if (road2[i + v][j].getisCar()) {
                                road2[i][j].getCar().setSpeed(max(0, v - 2));
                                velocity = max(0, v - 2);
                                break;
                            }
                        }
                        road2[i][j].getCar().setSpeed(velocity);
                        if (road2[i][0].getDistanceFromLights() <= 10 &&
                                road2[i][0].getisCar() &&
                                road2[i][0].getCar().getDestination() == road2[i][0].getNextCrossroad()) {
                            // try to change Lane to get to outer so car can leave
                            if(!overtake(road2,i,0)){
                                road2[i][0].getCar().setSpeed(1);
                            }
                            else
                                break;
                        }
//                        if (road2[i][1].getDistanceFromLights() <= velocity && road2[i][1].getCar().getDestination() == road2[i][1].getNextCrossroad()) {
//                            //{ in future - LEAVE BYPASS}
//                        }

//                        if (velocity > road2[i][j].getDistanceFromLights()) {
//                            stopOnRed(road2, i, j);
//                            continue;
//                        }

                        if (velocity != 0) {
                            road2[i][j].swapCar(road2[i][j],road2[i + velocity][j]);//swap cells on positions i and i+velocity
                        }
                    }
                }
            }


            for (int i = 1728; i < 1733; i++) {       //forward movement in edge case (cars near end of bound, without traffic lights
                if (road2[i][j].getisCar()) {
                    velocity = road2[i][j].getCar().getSpeed();
                    if (velocity < road2[i][j].getCar().getMaxSpeed()) velocity++;
                    if (i + velocity + 1 <= 1732) {      //edge case without going through
                        for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                            if (road2[i + v][j].getisCar()) {
                                road2[i][j].getCar().setSpeed(max(0, v - 2));
                                velocity = max(0, v - 2);
                                break;
                            }
                        }
                        road2[i][j].getCar().setSpeed(velocity);
                        if (velocity != 0) {

                            road2[i][j].swapCar(road2[i][j],road2[i + velocity][j]);//swap cells on positions i and i+velocity
                        }
                    } else if (i + velocity == 1732) {                                                 //edge case without going through nr 2
                        for (int v = 1; v <= velocity; v++) { //checking if all the cells we want to go through are free
                            if (road2[i + v][j].getisCar()) {
                                road2[i][j].getCar().setSpeed(max(0, v - 2));
                                velocity = max(0, v - 2);
                                break;
                            }

                        }
                        road2[i][j].getCar().setSpeed(velocity);
                        if (velocity != 0 && i + velocity < 1732) {

                            road1[i][j].swapCar(road1[i][j],road2[i + velocity][j]);
                            break;
                        }
                        if (velocity != 0 && !road2[0][j].getisCar()) {

                            road2[i][j].swapCar(road2[i][j],road2[1732][j]);
                            break;
                        }
                        if (road2[0][j].getisCar()) {

                            road2[i][j].swapCar(road2[i][j],road2[1731][j]);
                        } else {

                            road2[i][j].swapCar(road2[i][j],road2[1732][j]);
                        }
                    } else {                                  // edge case with going through
                        if (!road2[0][j].getisCar() && !road2[1][j].getisCar()) {

                            road2[i][j].swapCar(road2[i][j],road2[0][j]);
                        }
                    }
                }
            }
        }
    }

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
        var number = Settings.carNumber/4;
        road.CreateCars(number, road1, 0);
        road.CreateCars(number, road1, 1);
        road.CreateCars(number, road2, 0);
        road.CreateCars(number, road2, 1);
    }

}
