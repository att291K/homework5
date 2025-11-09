package ru.edu.lesson6;

/**
 * City info
 */
public class CityInfo {

    private String name;
    private GeoPosition position;

    /**
     * Ctor.
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    public String getName (){
        return name;
    }
    public GeoPosition getPosition(){
        return position;
    }


    public String toString(){
        return name + " " +position.toString();
    }
}
