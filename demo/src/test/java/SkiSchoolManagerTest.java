import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.data.DataWriter;
import com.example.data.DataWriterInterface;
import com.example.manager.SkiSchoolManager;
import com.example.model.Instructor;
import com.example.model.SkiSchool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkiSchoolManagerTest {
    private SkiSchoolManager manager;
    private SkiSchool skiSchool1;
    private SkiSchool skiSchool2;
    private Instructor g;
    private Instructor t;
    private Instructor i;
    private Instructor p;
    private List<SkiSchool> schools;
    private DataWriterInterface dataWriter = new DataWriter("resort_data.txt");

    @Before
    public void setUp() {
        // Set up Ski Schools with stub data
        skiSchool1 = new SkiSchool("Ski With Me", 2000, 0.8, 1, 1, 1, 1, 1, dataWriter);

        g = new Instructor("Gosho", true, 50);
        t = new Instructor("Tosho", false, 22);

        skiSchool2 = new SkiSchool("Ski With Me", 2000, 0.8, 1, 1, 1, 1, 1, dataWriter);
        i = new Instructor("Gosho", true, 50);
        p = new Instructor("Tosho", false, 22);

        skiSchool1.addInstructor(g);
        skiSchool1.addInstructor(t);
        skiSchool2.addInstructor(i);
        skiSchool2.addInstructor(p);

        // Initialize SkiSchoolManager with the list of ski schools
        schools = new ArrayList<>(Arrays.asList(skiSchool1, skiSchool2));
        manager = new SkiSchoolManager(schools);
    }

    @Test
    public void testCalculateTotalRevenue() {
        double expectedRevenue = manager.calculateTotalRevenue();
        assertEquals("Total revenue should be correctly calculated", expectedRevenue, manager.calculateTotalRevenue(),
                0.01);
    }

    @Test
    public void testCalculatePaymentToResort() {
        double revenue = manager.calculateTotalRevenue();
        double expectedPayment = revenue * 20 / 100;
        assertEquals("Payment to resort should be 20% of total revenue", expectedPayment,
                manager.calculatePaymentToResort(20), 0.01);
    }

}
