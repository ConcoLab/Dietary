package daos.interfaces;

import models.FoodMeal;

import java.util.ArrayList;

public interface FoodMealDao {
    FoodMeal insert(FoodMeal foodMeal);
    ArrayList<FoodMeal> all();
    int deleteAll();
    int delete(FoodMeal foodMeal);
    FoodMeal findById(long id);
}
