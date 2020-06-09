package cell;

public class TrafficLights {
    private boolean Lights_color; //true - green, false - red
    private int timer;

    public TrafficLights(boolean color){
        this.Lights_color = color;
    }
    public void setLights_color(boolean lights_colour) {
        this.Lights_color = lights_colour;
    }

    public boolean getLights_color(){
        return this.Lights_color;
    }

    public void Change_color(){
        this.Lights_color = !this.Lights_color;
    }


}
