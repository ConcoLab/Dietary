package unitTest;

import controllers.FoodController;
import controllers.MealController;
import daoFactories.ContextFactory;
import models.Food;
import models.Group;
import models.Meal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
        context = new ContextFactory("jdbc:sqlite:./src/db/test_GroupController.db");
        main = new MainGUI();
        // Food item to make making meals easier.
        ArrayList<Group> GroupList = new ArrayList<>();
        GroupList.add(new Group(0, "TestGroup", new ArrayList<>()));
        testFood = new Food(0, "TestFood", 100, 50, 20, 10, 5, 0, 1, GroupList);
        foodId = FoodController.create(testFood);
    }

    @AfterAll
    static void tearDown() {
        // TODO: Wipe the database or reset it after testing.
        context = null;
        main = null;
        testFood = null;
    }

    @Test
    void create() throws SQLException {
        LocalDateTime mealDate = LocalDateTime.of(2020,1,9, 12, 0);
        Meal indiningTest1 = new Meal(0, foodId, 0, 0, 2, 300, 40, 30, 20, 10, mealDate);
        LocalDateTime startDate = LocalDateTime.of(2020,1,9, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020,1,10, 0, 0);
        int expectedNum = ContextFactory._MealDao().all(startDate, endDate).size() + 1;
        MealController.create(indiningTest1);
        int actualNum = MealController.getAllMeals(startDate, endDate).size();
        assertEquals(expectedNum, actualNum);
    }

    @Test
    void getAllMealsIndiningAndOutdining() throws SQLException {
        LocalDateTime startDate = LocalDateTime.of(2020,1,9, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020,1,10, 0, 0);
        LocalDateTime mealDate = LocalDateTime.of(2020,1,9, 12, 0);
        LocalDateTime outsideBoundsDate = LocalDateTime.of(2020,1,10, 12, 0);
        int expectedNum = ContextFactory._MealDao().all(startDate, endDate).size();
        int actualNum = MealController.getAllMeals(startDate, endDate).size();
        assertEquals(expectedNum, actualNum);

        int expectedAll = ContextFactory._MealDao().all(startDate, endDate).size() + 5;
        int expectedIndining = ContextFactory._MealDao().allInDinings(startDate, endDate).size() + 2;
        int expectedOutdining = ContextFactory._MealDao().allOutDinings(startDate, endDate).size() + 3;

        Meal indiningTest1 = new Meal(0, foodId, 0, 0, 2, 300, 40, 30, 20, 10, mealDate);
        MealController.create(indiningTest1);
        Meal indiningTest2 = new Meal(0, foodId, 1, 0, 2, 300, 40, 30, 20, 10, mealDate);
        MealController.create(indiningTest2);
        Meal outdiningTest1 = new Meal(0, foodId, 2, 1, 2, 300, 40, 30, 20, 10, mealDate);
        MealController.create(outdiningTest1);
        Meal outdiningTest2 = new Meal(0, foodId, 3, 1, 2, 300, 40, 30, 20, 10, mealDate);
        MealController.create(outdiningTest2);
        Meal outdiningTest3 = new Meal(0, foodId, 4, 1, 2, 300, 40, 30, 20, 10, mealDate);
        MealController.create(outdiningTest3);
        Meal outOfBoundsTest = new Meal(0, foodId, 4, 1, 2, 300, 40, 30, 20, 10, outsideBoundsDate);
        MealController.create(outOfBoundsTest);

        int actualAll = MealController.getAllMeals(startDate, endDate).size();
        assertEquals(expectedAll, actualAll);
        int actualIndining = MealController.getInDiningAllMeals(startDate, endDate).size();
        assertEquals(expectedIndining, actualIndining);
        int actualOutdining = MealController.getOutDinigAllMeals(startDate, endDate).size();
        assertEquals(expectedOutdining, actualOutdining);
    }
}