package main;

import road.Weather;

public class Settings {
    public static int carNumber;
    public static double slow;
    public static int[] incomeCars = new int[13];
    public static boolean defaultValues; //if true then default values, lese income values

    public void updateSettings(String weather, String dayTime, int[] inc, boolean defaultValues){
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
        if (defaultValues) {
            incomeCars[0] = 0;

            for (int i = 1; i < 13; i++) {
                incomeCars[i] = inc[i];
            }
            this.defaultValues = false ;
        }
        else{
            this.defaultValues = true;
        }

    }
}
