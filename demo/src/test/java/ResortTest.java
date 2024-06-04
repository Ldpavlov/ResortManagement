import com.example.data.DataWriter;
import com.example.data.DataWriterInterface;
import com.example.model.Country;
import com.example.model.Resort;
import com.example.model.SkiSchool;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ResortTest {
    private Resort resort;
    private DataWriterInterface dataWriter = new DataWriter("resort_data.txt"); 

    @Before
    public void setUp() {
        resort = new Resort("Aspen", 111, 645, Country.USA, 31000, 5000000, 1.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Resort", resort.getName());
        assertEquals(10, resort.getNumberOfLifts());
        assertEquals(50, resort.getKmPistes());
    }

    @Test
    public void testAddSkiSchool() {
        SkiSchool skiSchool = new SkiSchool("Test Ski School", 2000, 0.8, 35, 5, 5, 5, 111, dataWriter);
        resort.addSkiSchool(skiSchool);
        List<SkiSchool> skiSchools = resort.getAllSkiSchools();
        assertTrue(skiSchools.contains(skiSchool));
    }

    @Test
    public void testAddSkiPassType() {
        resort.addSkiPassType("WEEKEND", 100.0);
        assertEquals(100.0, resort.getFinancialManager().getSkiPassPrice("WEEKEND"), 0.001);
    }

    @Test
    public void testGetPriceForSkiPassType() {
        resort.addSkiPassType("WEEKEND", 100.0);
        assertEquals(100.0, resort.getPriceForSkiPassType("WEEKEND"), 0.001);
    }

    @Test
    public void testToString() {
        String expected = "Resort: Aspen, Number of lifts: 111, Km/Pistes: 645, Country: USA";
        assertEquals(expected, resort.toString());
    }

}
