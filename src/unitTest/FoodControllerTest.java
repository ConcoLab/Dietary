//package unitTest;
//
//import controllers.FoodController;
//import daoFactories.ContextFactory;
//import daos.concrete.FoodDao;
//import models.Food;
//import models.Group;
//import models.Location;
//import models.Unit;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import views.main.MainGUI;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// * Tests functions of FoodController (and by extension, FoodDao).
// * NOTE: This class must be run in isolation or else database connection issues may cause tests to fail.
// */
//class FoodControllerTest {
//
//    static ContextFactory context;
//    static MainGUI main;
//
//    @BeforeAll
//     static void setUp() throws SQLException {
//        // Necessary setup for anything to function.
//        context = new ContextFactory("dietaryTest");
//        main = new MainGUI();
//    }
//
//    @BeforeEach
//    void initEach() throws SQLException {
//        ContextFactory._FoodDao().deleteAll();
//        ContextFactory._UnitDao().insert(new Unit(1, "Unit1"));
//        ContextFactory._UnitDao().insert(new Unit(2, "Unit2"));
//        ContextFactory._LocationDao().insert(new Location(1, "Location1", "Address1"));
//        ContextFactory._LocationDao().insert(new Location(2, "Location2", "Address2"));
//        ContextFactory._GroupDao().insert(new Group(1, "Group1", new ArrayList<Food>()));
//        ContextFactory._GroupDao().insert(new Group(2, "Group2", new ArrayList<Food>()));
//        ContextFactory._FoodDao().insert(new Food(1,
//                "Food1",
//                100,
//                100,
//                100,
//                100,
//                100,
//                1,
//                100,
//                new ArrayList<Group>()));
//        ContextFactory._FoodDao().insert(new Food(2,
//                "Food2",
//                100,
//                100,
//                100,
//                100,
//                100,
//                2,
//                100,
//                new ArrayList<Group>(){}));
//    }
//
//    @AfterAll
//    static void tearDown() {
//        // TODO: Wipe the database or reset it after testing.
//        context = null;
//        main = null;
//    }
//
//    @Test
//    void getAll() throws SQLException {
//        // Get the DB size directly from the context factory. (Best access we can get.)
//        int expectedNum = ContextFactory._FoodDao().all().size();
//        // Get the same from the FoodController.
//        ArrayList<Food> foodList = FoodController.getAll();
//        int actualNum = foodList.size();
//        // Assert both sizes match.
//        assertEquals(expectedNum, actualNum);
//    }
//
//    @Test
//    void createAndGetById() throws SQLException {
//        Food newFood = new Food(0, "CreateTest", 300, 25, 40, 10, 4, 0, 2, GroupList);
//
//        // Create a new food item, getting our DB size before and after.
//        int expectedNum = ContextFactory._FoodDao().all().size() + 1;
//        long foodId = FoodController.create(newFood);
//        int actualNum = ContextFactory._FoodDao().all().size();
//        // Assert we have one more food item.
//        assertEquals(expectedNum, actualNum);
//        // Assert we can be gotten by our ID (note: can't directly compare retrieved food to our new food).
//        assertTrue(FoodController.getFoodById(foodId) != null && FoodController.getFoodById(foodId).getName().equals("CreateTest"));
//    }
//
//    @Test
//    void createAndDeleteById() throws SQLException {
//        // Create and confirm we were added.
//        Food foodToDelete = new Food(0, "DeleteMe", 300, 25, 40, 10, 4, 0, 2, GroupList);
//        int expectedNum = ContextFactory._FoodDao().all().size() + 1;
//        long foodId = FoodController.create(foodToDelete);
//        int actualNum = ContextFactory._FoodDao().all().size();
//        assertEquals(expectedNum, actualNum);
//        // Delete and confirm deletion.
//        expectedNum = ContextFactory._FoodDao().all().size() > 0 ? ContextFactory._FoodDao().all().size() - 1 : 0;
//        FoodController.delete(foodId);
//        actualNum = ContextFactory._FoodDao().all().size();
//        assertEquals(expectedNum, actualNum);
//    }
//}