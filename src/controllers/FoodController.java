package controllers;

import daoFactories.ContextFactory;
import models.Food;

public class FoodController {
    /**
     * This method creates a new row of food in the database.
     * Anywhere in the view which we want to add a new food we should this action in the
     * food controller.
     * @param food
     * @return
     */
    public static long create (Food food){
        Food saved =ContextFactory._FoodDao().insert(food);
        return saved.getId();
    }
}
