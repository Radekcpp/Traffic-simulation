package road;
import java.util.Random;
import cell.*;

import static java.lang.Math.max;


public class Road {
    Cell[][] road1 = new Cell[1733][2];
    Cell[][] road2 = new Cell[1733][2];
    public static int[] streetLightPoints = new int[]{0,212,289,471,600,850,982,1174,1282,1407,1457,1493,1563,1733};
    public static Weather weather;
    public static DayTime dayTime;
    public int maxCapacity;

    public Road()
    {
        Random rand = new Random();
        int weatherInt = rand.nextInt(3);
        int dayTimeInt = rand.nextInt(3);
        int nextLight1 = 1;
        int nextLight2 = 0;

        switch (weatherInt) {
            case 1:
                weather = Weather.sun;
            case 2:
                weather = Weather.rain;
            case 3:
                weather = Weather.snow;
        }

        switch (dayTimeInt){
            case 1:
                dayTime = DayTime.Morning;
                maxCapacity = 350;
            case 2:
                dayTime = DayTime.Noon;
                maxCapacity = 700;
            case 3:
                dayTime = DayTime.Midnight;
                maxCapacity = 500;
            case 4:
                dayTime = DayTime.Night;
                maxCapacity = 300;
        }

        for (var counter = 0; counter < 1733; counter++) {
            road1[counter][0] = new Cell(RoadType.Basic, streetLightPoints[nextLight1] - counter, nextLight1);
            road1[counter][1] = new Cell(RoadType.Basic, streetLightPoints[nextLight1] - counter, nextLight1);
            road2[counter][0] = new Cell(RoadType.Basic, counter - streetLightPoints[nextLight2], nextLight2);
            road2[counter][1] = new Cell(RoadType.Basic, counter - streetLightPoints[nextLight2], nextLight2);

            if (counter>1281 && counter<1563) {
                road1[counter][0].setSpeedLimit(2);
                road1[counter][1].setSpeedLimit(2);
                road2[counter][0].setSpeedLimit(2);
                road2[counter][1].setSpeedLimit(2);
            }

            if (streetLightPoints[nextLight1]==counter)
                nextLight1++;

            if (streetLightPoints[nextLight2]==counter)
                nextLight2++;
        }

        for (var counter = 0; counter < (streetLightPoints.length-1); counter++) {
            road1[streetLightPoints[counter]][0].setType(RoadType.Lights);
            road1[streetLightPoints[counter]][1].setType(RoadType.Lights);
            road2[streetLightPoints[counter]+2][0].setType(RoadType.Lights);
            road2[streetLightPoints[counter]+2][0].setType(RoadType.Lights);
            road1[streetLightPoints[counter]+1][0].setType(RoadType.Crossroad);
            road1[streetLightPoints[counter]+1][1].setType(RoadType.Crossroad);
            road2[streetLightPoints[counter]+1][0].setType(RoadType.Crossroad);
            road2[streetLightPoints[counter]+1][0].setType(RoadType.Crossroad);
        }
    }
    void move() { //function which moves car on the whole bypass, temporary version moving them in only one direction
        int k = 0;
        int velocity;
        for (int i = 0; i < 1728; i++) {       //forward movement in general, without traffic lights
            if (road1[i][0].getisCar()) {
                velocity = road1[i][0].getCar().getSpeed();
                if (velocity < road1[i][0].getCar().getMaxSpeed()) velocity++;
                for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                    if (road1[i + v][0].getisCar()) {
                        road1[i][0].getCar().setSpeed(max(0, v - 2));
                        velocity = max(0, v - 2);
                        break;
                    }


                    // Random slow with given probability - needs to be done
                }
                if (velocity != 0)
                    road1[i][0].swapCar(road1[i + velocity][0]);//swap cells on positions i and i+velocity


            }
        }
        for (int i = 1728; i < 1733; i++) {       //forward movement in edge case (cars near end of bound, without traffic lights
            if (road1[i][0].getisCar()) {
                velocity = road1[i][0].getCar().getSpeed();
                if (velocity < road1[i][0].getCar().getMaxSpeed()) velocity++;
                if (i + velocity + 1 < 1732) {      //edge case without going through
                    for (int v = 1; v <= velocity + 1; v++) { //checking if all the cells we want to go through are free
                        if (road1[i + v][0].getisCar()) {
                            road1[i][0].getCar().setSpeed(max(0, v - 2));
                            velocity = max(0, v - 2);
                            break;
                        }

                    }
                    if (velocity != 0)
                        road1[i][0].swapCar(road1[i + velocity][0]);//swap cells on positions i and i+velocity

                } else if (i + velocity == 1732) {                                                 //edge case without going through nr 2
                    for (int v = 1; v <= velocity; v++) { //checking if all the cells we want to go through are free
                        if (road1[i + v][0].getisCar()) {
                            road1[i][0].getCar().setSpeed(max(0, v - 2));
                            velocity = max(0, v - 2);
                            if(velocity!=0 && !road1[0][0].getisCar())
                                 road1[i][0].swapCar(road1[1732][0]);
                            break;
                        }
                    }
                } else {                                  // edge case with going through
                    if(!road1[0][0].getisCar() && !road1[1][0].getisCar())  road1[i][0].swapCar(road1[0][0]);




                }
            }
        }

    }
}
