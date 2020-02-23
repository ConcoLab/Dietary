package daos.concrete;

import daos.interfaces.FoodMealDao;
import models.FoodMeal;

import java.util.ArrayList;

public class MysqlFoodMealDao implements FoodMealDao {
    public static ArrayList<FoodMeal> foodMeals = new ArrayList<FoodMeal>();

    @Override
    public FoodMeal insert(FoodMeal foodMeal) {
        foodMeals.add(foodMeal);
        return null;
    }

    @Override
    public ArrayList<FoodMeal> all() {
        return foodMeals;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int delete(FoodMeal foodMeal) {
        foodMeals.remove(foodMeal);
        return 0;
    }

    @Override
    public FoodMeal findById(long id) {
        return foodMeals.stream()
                .filter(foodMeal -> foodMeal.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
