package controllers;

import daoFactories.ContextFactory;
import models.Food;

public class FoodController {
    public static long create (Food food){
        Food saved =ContextFactory.get_mysqlFoodDao().insert(food);
        return saved.getId();
    }
}
