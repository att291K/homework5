package ru.edu.lesson6;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        // parse and set latitude and longitude in radian

        latitude =convertToRadian(latitudeGradus);

        longitude = convertToRadian(longitudeGradus);

    }
    private double convertToRadian (String value){
        List<String> latitudeGraduslist =  List.of (value.replace('\u00B0','-').split("\\D", 4));

        final double pi = Math.PI;

        MyBiFunction myFunc = (o, o2) -> (Double)o/Math.pow(60.0,(int)o2);
        List<Double> doubleList=  latitudeGraduslist.stream()
                .map(s -> s.isEmpty() ? "0" : s)
                .limit(3)
                .map(Integer::parseInt)
                .map(i -> i*pi/180)
//              /  .peek(System.out::println)
                .collect(toList());
        //System.out.println(doubleList);

        return IntStream.range(0,doubleList.size())
                .boxed()
                .map(i -> (double)myFunc.apply(doubleList.get(i),i))
//                .peek(System.out::println)
                .reduce(0.0,Double::sum);
    }


    @FunctionalInterface
    interface MyBiFunction <T,U>{
        T apply(T t,U u);
    }

    // gettes and toString


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString(){
        return "Координаты широта "+ convertToString(latitude) + " долгота " + convertToString(longitude);
    }

    private String convertToString (Double value){
        double pi = Math.PI;

        double valueGradusDouble = value/pi*180;
        int valueGradusInt = (int) valueGradusDouble;

        double valueMinutes = (valueGradusDouble - valueGradusInt) * 60;
        int valueMinutesInt = (int)valueMinutes;

        double valueSeconds = (valueMinutes - valueMinutesInt)*60;
        int valueSecondsInt = (int)Math.round(valueSeconds);

        if (valueSecondsInt >= 60) {
            valueSecondsInt -= 60;
            valueMinutesInt += 1;
        }
        if (valueMinutesInt >= 60){
            valueMinutesInt -= 60;
            valueGradusInt = valueGradusInt + 1;
        }

        String result = valueGradusInt +"°"; //\u00B0
        if (valueMinutesInt != 0 || valueSecondsInt !=0) {
            result += "(";
            if (valueMinutesInt != 0) result +=valueMinutesInt+"'";
            if (valueSecondsInt != 0 ) result +=valueSecondsInt+"\"";
            result +=")";
        }
        return result;
    }
}
