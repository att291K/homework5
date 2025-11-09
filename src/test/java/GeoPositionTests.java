import org.junit.Assert;
import org.junit.Test;
import ru.edu.lesson6.CityInfo;
import ru.edu.lesson6.GeoPosition;

public class GeoPositionTests {

    @Test
    public void GeoPositionTest_getLatitude() {

        GeoPosition GP = new GeoPosition("55(46'11'')", "59(57'00'')");
        Assert.assertEquals(0.9733652757004265,GP.getLatitude(),0.000000001);
    }

    @Test
    public void GeoPositionTest_getLongitude() {

        GeoPosition GP = new GeoPosition("55(46'11'')", "59(57'00'')");
        Assert.assertEquals(1.0463248865706005,GP.getLongitude(),0.000000001);
    }

    @Test
    public void GeoPositionTest_toString() {

        GeoPosition GP = new GeoPosition("55(45'11'')", "59(57'00'')");
        Assert.assertEquals("Координаты широта 55°(45'11\") долгота 59°(57')",GP.toString());
    }

    @Test
    public void  CityTest_toString(){
        CityInfo city = new CityInfo("City", new GeoPosition("34(12'10'')","45"));
        Assert.assertEquals("City Координаты широта 34°(12'10\") долгота 45°",city.toString());
    }
    @Test
    public void  CityTest_getName(){
        CityInfo city = new CityInfo("City", new GeoPosition("34(12'10'')","45"));
        Assert.assertEquals("City",city.getName());
    }
}
