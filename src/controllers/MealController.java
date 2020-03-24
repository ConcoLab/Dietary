package controllers;

import daoFactories.ContextFactory;
import models.Meal;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MealController {
    public static Meal create(Meal meal){
        Meal newMeal = ContextFactory._MealDao().insert(meal);
        ContextFactory._MealDao().notifyObservers();
        return newMeal;
    }

    public static ArrayList<Meal> getAllMeals(LocalDateTime startDate,LocalDateTime endDate) throws SQLException {
        ArrayList<Meal>  meals =  ContextFactory._MealDao().all(startDate, endDate);
        return meals;
    }

    public static ArrayList<Meal> getInDiningAllMeals(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        ArrayList<Meal>  meals =  ContextFactory._MealDao().allInDinings(startDate, endDate);
        return meals;
    }

    public static ArrayList<Meal> getOutDinigAllMeals(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        ArrayList<Meal>  meals =  ContextFactory._MealDao().allOutDinings(startDate, endDate);
        return meals;
    }
}
