package unitTest;

import controllers.GroupController;
import daoFactories.ContextFactory;
import models.Group;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.main.MainGUI;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests functions of GroupController (and by extension, GroupDao).
 * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
 */
class GroupControllerTest {

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
    void getAll() throws SQLException {
        // Get from DAO (since no better option exists.)
        int expected = ContextFactory._GroupDao().all().size();
        // Get same value via controller and compare.
        int actual = GroupController.getAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void create() throws SQLException {
        // Make a new group and check it was added.
        int expectedNum = ContextFactory._GroupDao().all().size() + 1;
        Group newGroup = new Group(0, "CreatedGroup", null);
        GroupController.create(newGroup);
        int actualNum = ContextFactory._GroupDao().all().size();
        assertEquals(expectedNum, actualNum);
    }

    /*@Test
    void delete() {
        fail("This is not testable yet since deletion depends on data accessible through the UI.");
    }*/

    @Test
    void getGroupNames() {
        // Ensure an empty list is an empty string.
        String expected = "";
        ArrayList<Group> groupList = new ArrayList<>();
        String actual = GroupController.getGroupNames(groupList);
        assertEquals(expected, actual);
        // Test adding three groups.
        expected = "FirstGroup, SecondGroup, ThirdGroup, ";
        Group firstGroup = new Group(0, "FirstGroup", null);
        groupList.add(firstGroup);
        Group secondGroup = new Group(0, "SecondGroup", null);
        groupList.add(secondGroup);
        Group thirdGroup = new Group(0, "ThirdGroup", null);
        groupList.add(thirdGroup);
        actual = GroupController.getGroupNames(groupList);
        assertEquals(expected, actual);
    }
}