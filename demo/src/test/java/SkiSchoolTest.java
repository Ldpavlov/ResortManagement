import org.junit.Before;
import org.junit.Test;

import com.example.common.NoSkisAvailableException;
import com.example.data.DataWriter;
import com.example.data.DataWriterInterface;
import com.example.model.SkiSchool;

import static org.junit.Assert.*;

public class SkiSchoolTest {
    private SkiSchool skiSchool;
    private DataWriterInterface dataWriter;

    @Before
    public void setUp() {
        dataWriter = new DataWriter("resort_data.txt");
        skiSchool = new SkiSchool("NumberOne", 2500, 1, 35, 5, 5, 30, 100, dataWriter);
    }

    @Test
    public void testRentSkisAvailable() throws NoSkisAvailableException {
        int expectedAvailableSkis = 5;
        skiSchool.rentEquipment("ski");
        assertEquals("Number of skis after one rental", expectedAvailableSkis - 1, skiSchool.getAvailableSkis());
    }

    @Test(expected = NoSkisAvailableException.class)
    public void testRentSkisNoneAvailable() throws NoSkisAvailableException {
        skiSchool.setAvailableSkis(0);
        skiSchool.rentEquipment("ski");
    }

    @Test
    public void testCalculateIncomeFromRentals() {
        double expected = (100 * 30) * 35;
        assertEquals(expected, skiSchool.calculateIncomeFromRent(), 0.001);
    }
}
