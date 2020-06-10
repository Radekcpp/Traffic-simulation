package car;
import cell.*;
import main.Settings;
import road.Weather;

import java.util.Random;

public class CarInstance  {
    private int speed;
    private int maxSpeed;
    private DriverPersonality personality;
    public int destination;
    private int slowProbability;

    public CarInstance(){
        Random rand = new Random();
        this.slowProbability=1;
        int driverPersonality = rand.nextInt(3);
        int destinationInt = rand.nextInt(12);
        this.setSpeed(1);
        var personality = DriverPersonality.Normal;
        switch (driverPersonality) {
            case 0:
                personality = DriverPersonality.Aggressive;
                break;
            case 1:
                personality = DriverPersonality.Normal;
                break;
            case 2:
                personality = DriverPersonality.Defensive;
                break;
        }
        this.personality = personality;
        this.setMaxSpeed(personality);

        // probability changes according to weather

        //probability changes according to time of the day

        this.destination = destinationInt+1;
    }

    public int getSpeed() { return this.speed; }

    public void setSpeed(int speedInc) { this.speed = speedInc; }

    public int getMaxSpeed() { return this.maxSpeed; }

    public void setMaxSpeed(DriverPersonality driverPersonality) {
        Random rand = new Random();
        double randomGeneric = (rand.nextInt(100) +1)/(100.00);
        this.maxSpeed = 3;
        double slowProbability = 0.0;
        switch (this.personality) {
            case Aggressive:
                slowProbability = 0.0;
                break;
            case Normal:
                slowProbability = 0.3;
                break;
            case Defensive:
                slowProbability = 0.6;
                break;
        }
        if (randomGeneric<Settings.slow + slowProbability){
            this.maxSpeed--;
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
