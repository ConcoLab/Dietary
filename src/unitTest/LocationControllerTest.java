package unitTest;

import controllers.LocationController;
import daoFactories.ContextFactory;
import models.Food;
import models.Location;
import models.Unit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
        context = new ContextFactory("dietaryTest");
        main = new MainGUI();
    }

    @BeforeEach
    void initEach() throws SQLException {
        ContextFactory._LocationDao().deleteAll();
        LocationController.create(new Location(1, "Location1", "Address1"));
        LocationController.create(new Location(2, "Location2", "Address2"));
    }

    @AfterAll
    static void tearDown() {
        ContextFactory._LocationDao().deleteAll();
    }

    @Test
    void getAllLocations() throws SQLException {
        ArrayList<Location> locations = LocationController.getAllLocations();
        assertEquals(2, locations.size());
        assertEquals(1, locations.get(0).getId());
        assertEquals("Location1", locations.get(0).getName());
        assertEquals("Address1", locations.get(0).getAddress());
        assertEquals(2, locations.get(1).getId());
        assertEquals("Location2", locations.get(1).getName());
        assertEquals("Address2", locations.get(1).getAddress());
    }

    @Test
    void create() throws SQLException {
        LocationController.create(new Location(3, "Location3", "Address3"));
        ArrayList<Location> locations = LocationController.getAllLocations();
        assertEquals(3, locations.size());
        assertEquals(3, locations.get(2).getId());
        assertEquals("Location3", locations.get(2).getName());
        assertEquals("Address3", locations.get(2).getAddress());
    }

    @Test
    void delete() throws SQLException {
        LocationController.delete(2);
        ArrayList<Location> locations = LocationController.getAllLocations();
        assertEquals(1, locations.size());
        assertEquals(1, locations.get(0).getId());
        assertEquals("Location1", locations.get(0).getName());
        assertEquals("Address1", locations.get(0).getAddress());
    }

    @Test
    void getLocationById() throws SQLException {
        Location location = LocationController.getLocationById(2);
        assertEquals(2, location.getId());
        assertEquals("Location2", location.getName());
        assertEquals("Address2", location.getAddress());
    }

}