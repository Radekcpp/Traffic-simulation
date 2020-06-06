package road;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Random;
import cell.Cell;
import cell.RoadType;

public class Road {
    Cell[][] road1 = new Cell[1733][2];
    Cell[][] road2 = new Cell[1733][2];
    public static Weather weather;
    public static DayTime dayTime;
    public int maxCapacity;

    public Road()
    {
        Random rand = new Random();
        int weatherInt = rand.nextInt(3);
        int dayTimeInt = rand.nextInt(3);

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

        for (var cellTable : road1 ) {
            for (var cell : cellTable){

            }
        }

    }
    public void cellTypesOuterSide(){
        var counter = 0;
        int[] streetLightPoints={0,212,289,471,600,850,982,1174,1282,1407,1457,1493,1563};
        for(int i=0;i<1733;i++){

            for(int x : streetLightPoints){
                if(counter==x){
                     road1[i][0].setType(RoadType.Lights);
                    road1[i][1].setType(RoadType.Lights);
                }
                else if(counter==x+1){
                    new Cell(RoadType.Crossroad);
                }
                else
                    new Cell(RoadType.Basic);
            }


            counter++;
        }


    }




}
