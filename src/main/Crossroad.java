package main;

public class Crossroad {
    private int crossroad_nr; //number 1-13 defining which crossroad it i
    private int crossroad_capacity; //number of cars able  to get on a crossroad during one red light

    public Crossroad (int number, int Crossroad_capacity) {
        this.crossroad_capacity = Crossroad_capacity;
        this.crossroad_nr = number;
    }

    public int getCrossroad_nr() {
        return crossroad_nr;
    }

    public int getCrossroad_capacity() {
        return crossroad_capacity;
    }

    public void setCrossroad_capacity(int crossroad_capacity) {
        this.crossroad_capacity = crossroad_capacity;
    }

    public void setCrossroad_nr(int crossroad_nr) {
        this.crossroad_nr = crossroad_nr;
    }


}
