package unitTest;

import controllers.LocationController;
import daoFactories.ContextFactory;
import models.Location;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.main.MainGUI;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests functions of LocationController (and by extension, LocationDao).
 * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
 */
class LocationControllerTest {

    static ContextFactory context;
    static MainGUI main;

    @BeforeAll
    static void setUp() throws SQLException {
        // Necessary setup for anything to function.
        context = new ContextFactory("jdbc:sqlite:./src/db/test_GroupController.db");
        main = new MainGUI();
    }

    @AfterAll
    static void tearDown() {
        // TODO: Wipe the database or reset it after testing.
        context = null;
        main = null;
    }

    @Test
    void getAllLocations() throws SQLException {
        // Get from DAO (since no better option exists.)
        int expected = ContextFactory._LocationDao().all().size();
        // Get same value via controller and compare.
        int actual = LocationController.getAllLocations().size();
        assertEquals(expected, actual);
    }

    @Test
    void createAndGetById() throws SQLException {
        // Expect one more than what we currently have.
        int expected = LocationController.getAllLocations().size() + 1;
        Location loc = new Location(0, "TestPlace","123 Test Lane");
        // Create a new location and compare.
        LocationController.create(loc);
        int actual = LocationController.getAllLocations().size();
        assertEquals(expected, actual);
        // We have to do a more convoluted way of getting the idea to test whether getLocationById gets it correct.
        ArrayList<Location> listOfLocs = LocationController.getAllLocations();
        long locId = -1;
        for (int i = 0; i < listOfLocs.size(); i++) {
            if (listOfLocs.get(i).getName().equals("TestPlace")) {
                locId = listOfLocs.get(i).getId();
                break;
            }
        }
        // Now we can test if getLocationById works
        assertNotNull(LocationController.getLocationById(locId));
        assertEquals(loc.getName(), LocationController.getLocationById(locId).getName());
    }

    /*@Test
    void delete() {
        fail("This is not testable yet since deletion depends on data accessible through the UI.");
    }*/
}