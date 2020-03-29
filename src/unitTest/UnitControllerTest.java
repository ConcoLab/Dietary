package unitTest;

import controllers.UnitController;
import daoFactories.ContextFactory;
import models.Unit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.main.MainGUI;

import java.sql.SQLException;

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
    void getAllUnits() throws SQLException {
        // Get from DAO and confirm it matches the controller.
        int expected = ContextFactory._UnitDao().all().size();
        int actual = UnitController.getAllUnits().size();
        assertEquals(expected, actual);
    }

    @Test
    void create() throws SQLException {
        // Expect one more than what we currently have.
        int expectedNum = UnitController.getAllUnits().size() + 1;
        // Create a new unit and compare
        Unit unit = new Unit(0, "TestUnit");
        UnitController.create(unit);
        int actualNum = UnitController.getAllUnits().size();
        assertEquals(expectedNum, actualNum);
    }

    /*@Test
    void delete() {
        fail("This is not testable yet since deletion depends on data accessible through the UI.");
    }*/

    /*@Test
    void getById() {
        fail("This is not testable yet since the database ultimately assigns the ID, therefore we cannot know it to test it.");
    }*/
}