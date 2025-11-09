package ru.edu.lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        boolean exist = cities.stream()
                .anyMatch(a -> Objects.equals(a.getName(), cityInfo.getName()));
        if (exist)  throw new IllegalArgumentException("Такой город уже есть в списке");
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {

        CityInfo removeCity = cities.stream()
                .filter(a -> Objects.equals(a.getName(), cityName))
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException(String.format("Такого города %s нет в списке",cityName)));
        cities.remove(removeCity);
    }

    private boolean checkCity(String cityName){
        cities.stream()
                .filter(a -> Objects.equals(a.getName(), cityName))
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException(String.format("Такого города %s нет в списке",cityName)));
        return  true;
    }


    /**
     * Get cities names.
     */
    public List<String> citiesNames() {

        List<String> c = cities.stream()
                .map(CityInfo::getName)
                .collect(Collectors.toList());
        return c;
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {

        checkCity(srcCityName);
        checkCity(destCityName);

        final double radiusOfEarth = 6372.795; //radius of Earth in km


        double lat1 = getPosition(srcCityName).getLatitude();
        double lat2 = getPosition(destCityName).getLatitude();
        Double long1 = getPosition(srcCityName).getLongitude();
        Double long2 = getPosition(destCityName).getLongitude();
        double delta = long2 - long1;

        // косинусы и синусы широт и разницы долгот
        Double cosLat1 = Math.cos(lat1);
        Double cosLat2 = Math.cos(lat2);
        Double sinLat1 = Math.sin(lat1);
        Double sinLat2 = Math.sin(lat2);
        Double cosDelta = Math.cos(delta);
        Double sinDelta = Math.sin(delta);
        // вычисления длины большого круга
        double y = Math.sqrt(Math.pow(cosLat2 * sinDelta, 2) + Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta,2));
        double x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;

        double distance = Math.atan2(y,x) * radiusOfEarth;


        /*double distance = Math.acos( Math.sin( getPosition(srcCityName).getLatitude() ) * Math.sin( getPosition(destCityName).getLongitude() ) +
                                     Math.cos( getPosition(srcCityName).getLatitude() ) * Math.cos( getPosition(destCityName).getLongitude() ) *
                                     Math.cos( getPosition(destCityName).getLongitude() - getPosition(srcCityName).getLongitude() )
                          ) *  radiusOfEarth;*/
        return (int)Math.round(distance);
    }

    private GeoPosition getPosition(String name){
        return cities.stream()
                .filter(a -> Objects.equals(a.getName(), name))
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException(String.format("города %s нет в списке",name)))
                .getPosition();
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        checkCity(cityName);

        return cities.stream()
                .map(CityInfo::getName)
                .filter(name -> !Objects.equals(name, cityName))
                .filter(name -> getDistance(cityName, name)<radius )
                .collect(Collectors.toList());

    }
}
