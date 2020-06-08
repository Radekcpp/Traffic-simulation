package road;

public class StartSettings {
    private Weather chosenWeather;
    private DayTime chosenDaytime;

    public void setChosen_daytime(DayTime chosen_daytime) {
        this.chosenDaytime = chosen_daytime;
    }

    public DayTime getChosen_daytime() {
        return chosenDaytime;
    }

    public void setChosen_weather(Weather chosen_weather) {
        this.chosenWeather = chosen_weather;
    }

    public Weather getChosen_weather() {
        return chosenWeather;
    }

}
