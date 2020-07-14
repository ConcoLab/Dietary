package unitTest;

import controllers.GroupController;
import daoFactories.ContextFactory;
import models.Food;
import models.Group;
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
 * Tests functions of GroupController (and by extension, GroupDao).
 * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
 */
class GroupControllerTest {

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
        ContextFactory._GroupDao().deleteAll();
        ContextFactory._GroupDao().insert(new Group(1, "Group1", new ArrayList<Food>()));
        ContextFactory._GroupDao().insert(new Group(2, "Group2", new ArrayList<Food>()));
    }

    @AfterAll
    static void tearDown() {
        ContextFactory._GroupDao().deleteAll();
//        context = null;
//        main = null;
    }

    @Test
    void getAll() throws SQLException {
        ArrayList<Group> groups = GroupController.getAll();
        assertEquals(2, groups.size());
        assertEquals(1, groups.get(0).getId());
        assertEquals(2, groups.get(1).getId());
        assertEquals("Group1", groups.get(0).getName());
        assertEquals("Group2", groups.get(1).getName());
    }

    @Test
    void create() throws SQLException {
        // Make a new group and check it was added.
        GroupController.create(new Group(3, "Group3", new ArrayList<Food>()));
        ArrayList<Group> groups = GroupController.getAll();

        assertEquals(groups.size(), 3);
        assertEquals(3, groups.get(2).getId());
        assertEquals("Group3", groups.get(2).getName());
    }

    @Test
    void delete() throws SQLException {
        GroupController.delete(2);
        ArrayList<Group> groups = GroupController.getAll();
        assertEquals(1, groups.size());
        assertEquals(1, groups.get(0).getId());
        assertEquals("Group1", groups.get(0).getName());
    }

    @Test
    void getGroupNames() throws SQLException {
        ArrayList<Group> groups = GroupController.getAll();
        assertEquals("Group1, Group2, ", GroupController.getGroupNames(groups));
    }
}