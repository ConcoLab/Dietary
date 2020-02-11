package daos.interfaces;

import models.Food;
import models.FoodMeal;
import models.Location;
import models.Meal;

import java.util.ArrayList;

public interface FoodMealDao {
    FoodMeal insert(FoodMeal foodMeal);
    ArrayList<FoodMeal> all();
    int deleteAll();
    int delete(FoodMeal foodMeal);
    FoodMeal findById(long id);
    ArrayList<Food> getFoodsInMeal(long mealId);
    ArrayList<Meal> getMealsofOneFood(long foodId);
    ArrayList<Meal> getMealsofOneLocation(long locationId);
    ArrayList<Meal> getMealsOfOneFood(long foodId);
    ArrayList<Location> getLocationsOfOneFood(long foodId);
}
