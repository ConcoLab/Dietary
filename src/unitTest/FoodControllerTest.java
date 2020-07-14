package unitTest;

import controllers.FoodController;
import daoFactories.ContextFactory;
import daos.concrete.FoodDao;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.main.MainGUI;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests functions of FoodController (and by extension, FoodDao).
 * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
 */
class FoodControllerTest {

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
        ContextFactory._FoodDao().deleteAll();
        ContextFactory._FoodGroupDao().deleteAll();
        ContextFactory._UnitDao().deleteAll();
        ContextFactory._LocationDao().deleteAll();
        ContextFactory._GroupDao().deleteAll();
        ContextFactory._UnitDao().insert(new Unit(1, "Unit1"));
        ContextFactory._UnitDao().insert(new Unit(2, "Unit2"));
        ContextFactory._LocationDao().insert(new Location(1, "Location1", "Address1"));
        ContextFactory._LocationDao().insert(new Location(2, "Location2", "Address2"));
        ContextFactory._GroupDao().insert(new Group(1, "Group1", new ArrayList<Food>()));
        ContextFactory._GroupDao().insert(new Group(2, "Group2", new ArrayList<Food>()));
        ContextFactory._FoodDao().insert(new Food(1,
                "Food1",
                1,
                2,
                3,
                4,
                5,
                1,
                7,
                new ArrayList<Group>()));
        ContextFactory._FoodGroupDao().insert(new FoodGroup(1,1));
        ContextFactory._FoodGroupDao().insert(new FoodGroup(1,2));
        ContextFactory._FoodDao().insert(new Food(2,
                "Food2",
                10,
                9,
                8,
                7,
                6,
                2,
                1,
                new ArrayList<Group>(){}));
        ContextFactory._FoodGroupDao().insert(new FoodGroup(2,1));
        ContextFactory._FoodGroupDao().insert(new FoodGroup(2,2));
    }

    @AfterAll
    static void tearDown() {
        ContextFactory._FoodDao().deleteAll();
        ContextFactory._FoodGroupDao().deleteAll();
        ContextFactory._UnitDao().deleteAll();
        ContextFactory._LocationDao().deleteAll();
        ContextFactory._GroupDao().deleteAll();
    }

    @Test
    void getAll() throws SQLException {
        ArrayList<Food> foods = FoodController.getAll();
        assertEquals(2, foods.size());
        assertEquals("Food1", foods.get(0).getName());
        assertEquals("Food2", foods.get(1).getName());
        // TODO: Implement other lines of test to check unit, quantity, etc
    }

    @Test
    void create() throws SQLException {
        Food newFood = new Food(3,
                "Food3",
                20,
                20,
                20,
                20,
                20,
                1,
                20,
                new ArrayList<Group>(){});
        FoodController.create(newFood);
        ArrayList<Food> foods = FoodController.getAll();
        assertEquals(3, foods.size());
        assertEquals("Food3", foods.get(2).getName());
        assertEquals(3, foods.get(2).getId());
        assertEquals(1, foods.get(2).getUnitId());
          // TODO: Implement other lines of test to check unit, quantity, etc
    }

    @Test
    void delete() throws SQLException {
        FoodController.delete(1);
        ArrayList<Food> foods = FoodController.getAll();
        assertEquals(1, foods.size());
        assertEquals("Food2", foods.get(0).getName());
        assertEquals(2, foods.get(0).getId());
        assertEquals(2, foods.get(0).getUnitId());
        // TODO: Implement other lines of test to check unit, quantity, etc
    }

    @Test
    void getFoodById() throws SQLException {
        Food food = FoodController.getFoodById(2);
        assertEquals("Food2", food.getName());
        assertEquals(2, food.getId());
        assertEquals(2, food.getUnitId());
//        // TODO: Implement other lines of test to check unit, quantity, etc
    }




}