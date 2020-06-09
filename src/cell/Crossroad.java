package cell;

public class Crossroad {
    private int amountOfCars = 30;

    public Crossroad(int amount) {this.amountOfCars = amount;}
    public int getAmountOfCars() {
        return amountOfCars;
    }

    public void setAmountOfCars(int amountOfCars) {
        this.amountOfCars = amountOfCars;
    }
}
