package cell;

import car.CarInstance;

public class Cell {
    private boolean isCar; // false - empty cell, true - cell with a car (jest na niej auto)
    private RoadType type; // 1 - basic road, 2 - crossroad, 3 - traffic lights
    private CarInstance car;
    private int speedLimit; //speed limit on this road
    private int distanceFromLights; //distance from the nearest traffic lights
    private int nextCrossroad; //number 0-12 defining which crossroad is next

    public Cell(){
        this.isCar = false;
    }

    public Cell(RoadType typeInc, int distance, int nextCrossroadInc) {
        this.isCar = false;
        this.speedLimit = 3;
        setType(typeInc);
        setDistanceFromLights(distance);
        setNextCrossroad(nextCrossroadInc);
    }

    public void setType(RoadType type) { this.type = type; }

    public void setCar(CarInstance car) {
        this.car = car;
    }

    public void setisCar(boolean ifcar) {
       this.isCar = ifcar;
    }

    public void setDistanceFromLights(int distance_from_lights) {
        this.distanceFromLights = distance_from_lights;
    }

    public void setSpeedLimit(int speed_limit) {
        this.speedLimit = speed_limit;
    }

    public void setNextCrossroad(int nextCrossroad) {
        if (nextCrossroad == 13)
            nextCrossroad = 0;
        this.nextCrossroad = nextCrossroad;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public int getDistanceFromLights() {
        return distanceFromLights;
    }

    public CarInstance getCar() {
        return car;
    }

    public boolean getisCar() {
        return isCar;
    }

    public RoadType getType() {
        return type;
    }

    public void swapCar (Cell cell){ //takes a cell with car and switches it to empty and the other way around
        CarInstance temp = this.car;
        this.car = cell.getCar();
        cell.setCar(temp);
        this.isCar = false;
        cell.setisCar(true);
    }

}

