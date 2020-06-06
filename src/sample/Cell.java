package sample;

public class Cell {
    private boolean isCar; // 0 - empty cell, 1 - cell with a car (jest na niej auto)
    private int type; // 1 - basic road, 2 - crossroad, 3 - traffic lights
    //private Car auto = new Car(); object of class Car, to uncomment after implementing class car
    private int speed_limit; //speed limit on this road
    private int distance_from_lights; //distance from the nearest traffic lights
    private int next_crossroad; //number 1-13 defining which crossroad is next
    public void setType(int type) {
        this.type = type;
    }
    public void setCar(boolean ifcar) {
        this.isCar = ifcar;
    }
    public void setDistance_from_lights(int distance_from_lights) {
        this.distance_from_lights = distance_from_lights;
    }
    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

    public void setNext_crossroad(int next_crossroad) {
        this.next_crossroad = next_crossroad;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }
    public int getDistance_from_lights() {
        return distance_from_lights;
    }
    public int getType() {
        return type;
    }



}

