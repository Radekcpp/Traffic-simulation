package cell;

import java.util.concurrent.ThreadLocalRandom;


public class TrafficLights {
    private boolean Lights_color; //true - green, false - red
    private int timer;

    public TrafficLights(boolean color){
        this.Lights_color = color;
        this.timer = ThreadLocalRandom.current().nextInt(30,51);
    }
    public void setLights_color(boolean lights_colour){this.Lights_color = lights_colour;}

    public boolean getLights_color(){
        return this.Lights_color;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void Change_color(){
        this.Lights_color = !this.Lights_color;
    }


}
