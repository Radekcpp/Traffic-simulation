package road;
import java.util.Random;
import cell.Cell;
import cell.RoadType;

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
}
