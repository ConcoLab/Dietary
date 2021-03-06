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

    public static ArrayList<Meal> getAllMeals(LocalDateTime startDate,LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFoods) throws SQLException {
        ArrayList<Meal>  meals =  ContextFactory._MealDao().all(startDate, endDate, showConsumedFoods, showNotConsumedFoods);
        return meals;
    }

    public static ArrayList<Meal> getInDiningAllMeals(LocalDateTime startDate, LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFoods) throws SQLException {
        ArrayList<Meal>  meals =  ContextFactory._MealDao().allInDinings(startDate, endDate, showConsumedFoods, showNotConsumedFoods);
        return meals;
    }

    public static ArrayList<Meal> getOutDinigAllMeals(LocalDateTime startDate, LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFoods) throws SQLException {
        ArrayList<Meal>  meals =  ContextFactory._MealDao().allOutDinings(startDate, endDate, showConsumedFoods, showNotConsumedFoods);
        return meals;
    }

    public static boolean makeFoodIsConsumed(Long id, Boolean isConsumed) {
        ContextFactory._MealDao().makeFoodIsConsumed(id, isConsumed);
        ContextFactory._MealDao().notifyObservers();
        return true;
    }

    public static Meal findById(Long id) throws SQLException {
        return ContextFactory._MealDao().findById(id);
    }

    public static boolean delete(Long id) {
        ContextFactory._MealDao().delete(id);
        ContextFactory._MealDao().notifyObservers();
        return true;
    }

}
