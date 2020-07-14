package controllers;

import daoFactories.ContextFactory;
import models.Food;
import models.Meal;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodController {
    /**
     * This method creates a new row of food in the database.
     * Anywhere in the view which we want to add a new food we should this action in the
     * food controller.
     * @param food
     * @return
     */
    public static long create (Food food){
        Food saved = ContextFactory._FoodDao().insert(food);
        ContextFactory._FoodDao().notifyObservers();
        return saved.getId();
    }

    public static ArrayList<Food> getAll() throws SQLException {
        return ContextFactory._FoodDao().all();
    }

    public static void delete(long id){
        ContextFactory._FoodDao().delete(id);
        ContextFactory._FoodDao().notifyObservers();
        return;
    }

    public static Food getFoodById(long id) throws SQLException {
        Food food = ContextFactory._FoodDao().findById(id);
        return food;
    }

}
