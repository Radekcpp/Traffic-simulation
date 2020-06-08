package car;
import cell.*;
import road.Weather;

import java.util.Random;

public class CarInstance  {
    private int speed;
    private int maxSpeed;
    private DriverPersonality personality;
    private int destination;
    private int slowProbability;

    CarInstance(){
        Random rand = new Random();
        this.slowProbability=1;
        int driverPersonality = rand.nextInt(3);
        int destinationInt = rand.nextInt(12);
        this.setSpeed(1);
        switch (driverPersonality) {
            case 1:
                this.personality = DriverPersonality.Aggressive;
            case 2:
                this.personality = DriverPersonality.Normal;
            case 3:
                this.personality = DriverPersonality.Defensive;
        }



        // probability changes according to weather

        //probability changes according to time of the day

        this.destination = destinationInt+1;
    }

    public int getSpeed() { return this.speed; }

    public void setSpeed(int speedInc) { this.speed = speedInc; }

    public int getMaxSpeed() { return this.maxSpeed; }

    public void setMaxSpeed(int speedInc) {

        switch (this.personality) {
            case Aggressive:
                this.maxSpeed=3;
            case Normal:
                this.maxSpeed=2;
            case Defensive:
                this.maxSpeed=2;

        }
    }

    public DriverPersonality getDriverPersonality() { return this.personality; }

    public int getDestination() { return this.destination; }

    public void increaseSpeed(){ this.speed++; }

    public void decreaseSpeed() { this.speed--; }

    public void move(Cell cell) {
        if (speed < maxSpeed)
            this.increaseSpeed();

    }

}
