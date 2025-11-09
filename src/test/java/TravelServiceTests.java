import org.junit.Assert;
import org.junit.Test;
import ru.edu.lesson6.CityInfo;
import ru.edu.lesson6.GeoPosition;
import ru.edu.lesson6.TravelService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class TravelServiceTests {

    private TravelService converter = new TravelService();

    @Test  (expected = Exception.class)
    public void TravelServiceTest_add() {
        CityInfo city1 = new CityInfo("Moscow", new GeoPosition("34(45;13'')","67(12'40''"));
        CityInfo city2 = new CityInfo("Moscow", new GeoPosition("34(45;13'')","67(12'40''"));
        converter.add(city1);
        converter.add(city2);

    }

    private void Initialise(){
        CityInfo city1 = new CityInfo("Moscow", new GeoPosition("34(45;13'')","60(12'40''"));
        converter.add(city1);
        CityInfo city2 = new CityInfo("Tula", new GeoPosition("32(48;13'')","60(12'40''"));
        converter.add(city2);
        CityInfo city3 = new CityInfo("Kaliningrad", new GeoPosition("31(10;03'')","37(12'40''"));
        converter.add(city3);
        CityInfo city4 = new CityInfo("Vladivostok", new GeoPosition("161(55;03'')","30(32'40''"));
        converter.add(city4);
        CityInfo city = new CityInfo("Tver", new GeoPosition("43(15;03'')","69(32'40''"));
        converter.add(city);
        city = new CityInfo("Orel", new GeoPosition("25(15;03'')","65(32'40''"));
        converter.add(city);


    }

    @Test  (expected = Exception.class)
    public void TravelServiceTest_remove() {
        Initialise();

        converter.remove("Orel");

        converter.remove("Orel");


    }

    @Test  (expected = AssertionError.class)
    public void TravelServiceTest_citiesNames() {
        Initialise();

        List<String> cities = new ArrayList<>();
        cities.add("Moscow");
        cities.add("Tula");
        cities.add("Kaliningrad");
        cities.add("Vladivostok");
        cities.add("Tver");
        cities.add("Orel");

        Assert.assertThat(cities, is(converter.citiesNames()));

        converter.remove("Orel");

        Assert.assertThat(cities, is(converter.citiesNames()));

    }


    @Test  (expected = Exception.class)
    public void TravelServiceTest_getDistance() {
        Initialise();
        int distance = converter.getDistance("Moscow","Tula");
        Assert.assertEquals(217, distance);

        int distance2 = converter.getDistance("Vladivostok","Tula");
        Assert.assertEquals(13541, distance2);

        int distance3 = converter.getDistance("Kursk","Tula");
    }

    @Test  (expected = Exception.class)
    public void TravelServiceTest_getCitiesNear() {
        Initialise();
        List<String> cities = new ArrayList<>();
        cities.add("Moscow");
        cities.add("Kaliningrad");
        cities.add("Tver");
        cities.add("Orel");

        Assert.assertThat(cities, is(converter.getCitiesNear("Tula",2500)));

        Assert.assertThat(cities, is(converter.getCitiesNear("Paris",2500)));
    }
}
