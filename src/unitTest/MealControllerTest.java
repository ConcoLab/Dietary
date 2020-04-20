package unitTest;

import controllers.FoodController;
import controllers.LocationController;
import controllers.MealController;
import daoFactories.ContextFactory;
import models.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.main.MainGUI;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests functions of MealController (and by extension, MealDao).
 * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
 */
class MealControllerTest {
    static ContextFactory context;
    static MainGUI main;
    static Food testFood;
    static long foodId;


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
        ContextFactory._UnitDao().deleteAll();
        ContextFactory._UnitDao().insert(new Unit(1, "Unit1"));
        ContextFactory._GroupDao().deleteAll();
        ContextFactory._GroupDao().insert(new Group(1, "Group1", new ArrayList<Food>()));
        ContextFactory._FoodDao().deleteAll();
        ContextFactory._FoodDao().insert(new Food(1,
                "Food1",
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                new ArrayList<Group>()));
        ContextFactory._FoodGroupDao().deleteAll();
        ContextFactory._FoodGroupDao().insert(new FoodGroup(1,1));
        ContextFactory._FoodGroupDao().insert(new FoodGroup(1,2));
        ContextFactory._MealDao().deleteAll();
        ContextFactory._MealDao().insert(new Meal(1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                LocalDateTime.of(2020,1,9, 12, 0)));
    }

    @AfterAll
    static void tearDown() {
        // TODO: Wipe the database or reset it after testing.
        ContextFactory._FoodDao().deleteAll();
        ContextFactory._FoodGroupDao().deleteAll();
        ContextFactory._UnitDao().deleteAll();
        ContextFactory._LocationDao().deleteAll();
        ContextFactory._GroupDao().deleteAll();
        ContextFactory._MealDao().deleteAll();

    }

    @Test
    void create() throws SQLException {
//        LocalDateTime mealDate = LocalDateTime.of(2020,1,9, 12, 0);
//        Meal indiningTest1 = new Meal(0, foodId, 0, 0, 2, 300, 40, 30, 20, 10, 0, mealDate);
//        LocalDateTime startDate = LocalDateTime.of(2020,1,9, 0, 0);
//        LocalDateTime endDate = LocalDateTime.of(2020,1,10, 0, 0);
//        int expectedNum = ContextFactory._MealDao().all(startDate, endDate, true, true).size() + 1;
//        MealController.create(indiningTest1);
//        int actualNum = MealController.getAllMeals(startDate, endDate, true, true).size();
//        assertEquals(expectedNum, actualNum);
        MealController.create(new Meal(2,
                1,
                2,
                1,
                2,
                2,
                2,
                2,
                2,
                2,
                1,
                LocalDateTime.of(2020,1,9, 12, 30)
        ));
        ArrayList<Meal> meals = MealController.getAllMeals(LocalDateTime.of(2020,1,9, 0, 0),
                LocalDateTime.of(2020,1,9, 23, 59),
                true,
                true);
        assertEquals(2, meals.size());
        assertEquals(1, meals.get(0).getFoodId());
        assertEquals(1, meals.get(0).getIsConsumed());
        assertEquals(1, meals.get(0).getCalories());
        assertEquals(1, meals.get(0).getSalt());
        assertEquals(1, meals.get(0).getCarbohydrate());
        assertEquals(1, meals.get(0).getProtein());
        assertEquals(LocalDateTime.of(2020,1,9, 12, 0), meals.get(0).getDateTime());
    }

    @Test
    void getAllMeals() throws SQLException{
        ArrayList<Meal> meals = MealController.getAllMeals(LocalDateTime.of(2020,1,9, 0, 0),
                LocalDateTime.of(2020,1,9, 23, 59),
                true,
                true);
        assertEquals(1, meals.size());
    }

    @Test
    void getInDiningAllMeals() throws SQLException{
        ArrayList<Meal> meals = MealController.getInDiningAllMeals(LocalDateTime.of(2020,1,9, 0, 0),
                LocalDateTime.of(2020,1,9, 23, 59),
                true,
                true);
        assertEquals(0, meals.size());
    }

    @Test
    void getOutDinigAllMeals() throws SQLException{
        ArrayList<Meal> meals = MealController.getOutDinigAllMeals(LocalDateTime.of(2020,1,9, 0, 0),
                LocalDateTime.of(2020,1,9, 23, 59),
                true,
                true);
        assertEquals(1, meals.size());
    }

    @Test
    void delete() throws SQLException{
        MealController.delete((long)1);
        ArrayList<Meal> meals = MealController.getAllMeals(LocalDateTime.of(2020,1,9, 0, 0),
                LocalDateTime.of(2020,1,9, 23, 59),
                true,
                true);
        assertEquals(0, meals.size());
    }

    @Test
    void findById() throws SQLException{
        Meal meal = MealController.findById((long)1);
        assertEquals(1, meal.getFoodId());
        assertEquals(1, meal.getIsConsumed());
        assertEquals(1, meal.getCalories());
        assertEquals(1, meal.getSalt());
        assertEquals(1, meal.getCarbohydrate());
        assertEquals(1, meal.getProtein());
        assertEquals(LocalDateTime.of(2020,1,9, 12, 0), meal.getDateTime());
    }


//    @Test
//    void getAllMealsIndiningAndOutdining() throws SQLException {
//        LocalDateTime startDate = LocalDateTime.of(2020,1,9, 0, 0);
//        LocalDateTime endDate = LocalDateTime.of(2020,1,10, 0, 0);
//        LocalDateTime mealDate = LocalDateTime.of(2020,1,9, 12, 0);
//        LocalDateTime outsideBoundsDate = LocalDateTime.of(2020,1,10, 12, 0);
//        int expectedNum = ContextFactory._MealDao().all(startDate, endDate, true, true).size();
//        int actualNum = MealController.getAllMeals(startDate, endDate, true, true).size();
//        assertEquals(expectedNum, actualNum);
//
//        int expectedAll = ContextFactory._MealDao().all(startDate, endDate, true, true).size() + 5;
//        int expectedIndining = ContextFactory._MealDao().allInDinings(startDate, endDate, true, true).size() + 2;
//        int expectedOutdining = ContextFactory._MealDao().allOutDinings(startDate, endDate, true, true).size() + 3;
//
//        Meal indiningTest1 = new Meal(0, foodId, 0, 0, 2, 300, 40, 30, 20, 10, 0, mealDate);
//        MealController.create(indiningTest1);
//        Meal indiningTest2 = new Meal(0, foodId, 1, 0, 2, 300, 40, 30, 20, 10, 0, mealDate);
//        MealController.create(indiningTest2);
//        Meal outdiningTest1 = new Meal(0, foodId, 2, 1, 2, 300, 40, 30, 20, 10, 0, mealDate);
//        MealController.create(outdiningTest1);
//        Meal outdiningTest2 = new Meal(0, foodId, 3, 1, 2, 300, 40, 30, 20, 10, 0, mealDate);
//        MealController.create(outdiningTest2);
//        Meal outdiningTest3 = new Meal(0, foodId, 4, 1, 2, 300, 40, 30, 20, 10, 0, mealDate);
//        MealController.create(outdiningTest3);
//        Meal outOfBoundsTest = new Meal(0, foodId, 4, 1, 2, 300, 40, 30, 20, 10, 0, outsideBoundsDate);
//        MealController.create(outOfBoundsTest);
//
//        int actualAll = MealController.getAllMeals(startDate, endDate, true, true).size();
//        assertEquals(expectedAll, actualAll);
//        int actualIndining = MealController.getInDiningAllMeals(startDate, endDate, true, true).size();
//        assertEquals(expectedIndining, actualIndining);
//        int actualOutdining = MealController.getOutDinigAllMeals(startDate, endDate, true, true).size();
//        assertEquals(expectedOutdining, actualOutdining);
//    }
}