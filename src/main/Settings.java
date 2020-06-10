package main;

import road.Weather;

public class Settings {
    public static int carNumber;
    public static double slow;

    public void updateSettings(String weather, String dayTime){
        switch (weather){
            case("Słońce"):
                slow = 0.05;
                break;
            case("Deszcz"):
                slow = 0.15;
                break;
            case("Śnieg"):
                slow = 0.25;
                break;
        }
        switch (dayTime){
            case ("Rano"):
                carNumber = 350;
                break;
            case ("Południe"):
                carNumber = 700;
                break;
            case ("Wieczór"):
                carNumber = 500;
                break;
            case ("Noc"):
                carNumber = 300;
                break;
        }
    }
}
