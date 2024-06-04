import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.example.manager.FinancialManager;
import com.example.model.Country;

public class FinancialManagerTest {

    private FinancialManager financialManager;

    @Before
    public void setUp() {
        Country country = Country.USA;
        financialManager = new FinancialManager(50000, 3000, Country.USA);
        financialManager.addSkiPassType("DAY", 75.0);
        financialManager.addSkiPassType("WEEK", 500.0);
    }

    @Test
    public void testAddSkiPassType() {
        assertEquals(75.0, financialManager.getSkiPassPrice("DAY"), 0.001);
        assertEquals(500.0, financialManager.getSkiPassPrice("WEEK"), 0.001);
    }

    @Test
    public void testCalculateTotalRevenueFromSkiPasses() {
        financialManager.recordSkiPassSale("DAY", 10);
        financialManager.recordSkiPassSale("WEEK", 5);
        assertEquals(10 * 75.0 + 5 * 500.0, financialManager.calculateTotalRevenueFromSkiPasses(), 0.001);
    }

    @Test
    public void testCalculateTotalCosts() {
        double expectedTotalCosts = 50000 + 3000 * Country.USA.getElectricityPrice();
        assertEquals(expectedTotalCosts, financialManager.calculateTotalCosts(), 0.001);
    }
}
