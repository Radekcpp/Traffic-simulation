package main;

import road.Weather;

public class Settings {
    public static int carNumber;
    public static double slow;

    public void updateSettings(String weather, String dayTime){
        switch (weather){
            case("Słońce"):
                slow = 0.05;
            case("Deszcz"):
                slow = 0.15;
            case("Śnieg"):
                slow = 0.25;
        }
        switch (dayTime){
            case ("Rano"):
                carNumber = 350;
            case ("Południe"):
                carNumber = 700;
            case ("Wieczór"):
                carNumber = 500;
            case ("Noc"):
                carNumber = 300;
        }
    }
}
