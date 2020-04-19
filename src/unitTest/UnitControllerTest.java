package unitTest;

import controllers.UnitController;
import daoFactories.ContextFactory;
import models.Unit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.main.MainGUI;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests functions of UnitController (and by extension, UnitDao).
 * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
 */
class UnitControllerTest {

    static ContextFactory context;
    static MainGUI main;

    @BeforeAll
    static void setUp() throws SQLException {
        context = new ContextFactory("dietaryTest");
        main = new MainGUI();
    }

    @BeforeEach
    void initEach() throws SQLException {
        ContextFactory._UnitDao().deleteAll();
        ContextFactory._UnitDao().insert(new Unit(1, "Unit1"));
        ContextFactory._UnitDao().insert(new Unit(2, "Unit2"));
    }

    @AfterAll
    static void tearDown() {
        ContextFactory._UnitDao().deleteAll();
    }

    @Test
    void getAllUnits() throws SQLException {
        // Get from DAO and confirm it matches the controller.
        ArrayList<Unit> units = UnitController.getAllUnits();
        int expected = 2;
        int actual = units.size();
        assertEquals(expected, actual);
        assertEquals(units.get(0).getId(), 1);
        assertEquals(units.get(1).getId(), 2);
        assertEquals(units.get(0).getName(), "Unit1");
        assertEquals(units.get(1).getName(), "Unit2");
    }

    @Test
    void create() throws SQLException {
        ArrayList<Unit> units = UnitController.getAllUnits();
        UnitController.create(new Unit(3, "Unit3"));
        ArrayList<Unit> unitsUpdated = UnitController.getAllUnits();
        assertEquals(units.size() + 1, unitsUpdated.size());
        assertEquals(unitsUpdated.get(unitsUpdated.size()-1).getId(), 3);
        assertEquals(unitsUpdated.get(unitsUpdated.size()-1).getName(), "Unit3");
    }

    @Test
    void delete() throws SQLException {
        ArrayList<Unit> units = UnitController.getAllUnits();
        UnitController.delete(2);
        ArrayList<Unit> unitsUpdated = UnitController.getAllUnits();
        assertEquals(units.size() - 1, unitsUpdated.size());
        assertEquals(units.get(0).getId(), 1);
        assertEquals(units.get(0).getName(), "Unit1");
    }

    /*@Test
    void getById() {
        fail("This is not testable yet since the database ultimately assigns the ID, therefore we cannot know it to test it.");
    }*/
}