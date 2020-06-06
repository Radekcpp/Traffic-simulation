package car;
import java.util.Random;

public class CarInstance  {
    private int speed;
    private int maxSpeed;
    private DriverPersonality personality;
    private int destination;

    CarInstance(int maxSpeedInc){
        Random rand = new Random();
        int driverPersonality = rand.nextInt(3);
        int destinationInt = rand.nextInt(12);
        this.setSpeed(1);
        switch (driverPersonality) {
            case 1:
                this.personality = DriverPersonality.Aggressive;
            case 2:
                this.personality = DriverPersonality.Defensive;
            case 3:
                this.personality = DriverPersonality.Passive;
        }
        this.setMaxSpeed(maxSpeedInc);
        this.destination = destinationInt;
    }

    public float getSpeed() { return this.speed; }

    public void setSpeed(int speedInc) { this.speed = speedInc; }

    public float getMaxSpeed() { return this.maxSpeed; }

    public void setMaxSpeed(int speedInc) {
        this.maxSpeed = speedInc;
        switch (this.personality) {
            case Aggressive:
                this.maxSpeed++;
            case Defensive:
                if (this.maxSpeed>1)
                    this.maxSpeed--;
        }
    }

    public DriverPersonality getDriverPersonality() { return this.personality; }

    public int getDestination() { return this.destination; }

    public void increaseSpeed(){ this.speed++; }

    public void decreaseSpeed() { this.speed--; }



}
