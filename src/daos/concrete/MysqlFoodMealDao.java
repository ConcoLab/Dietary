package daos.concrete;

import daoFactories.Context;
import daos.interfaces.FoodMealDao;
import models.Food;
import models.FoodMeal;
import models.Location;
import models.Meal;

import java.util.ArrayList;

public class MysqlFoodMealDao implements FoodMealDao {
    private Context _context;

    public MysqlFoodMealDao(Context context){
        _context = context;
        FoodMeal fm0 = new FoodMeal();
        fm0.setId(0);
        fm0.setMealId(1);
        fm0.setFoodId(1);
        fm0.setLocationId(0);
        fm0.setAmount(2);
        this.insert(fm0);

    }

    @Override
    public FoodMeal insert(FoodMeal foodMeal) {
        _context.foodMeals.add(foodMeal);
        return null;
    }

    @Override
    public ArrayList<FoodMeal> all() {
        return _context.foodMeals;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int delete(FoodMeal foodMeal) {
        _context.foodMeals.remove(foodMeal);
        return 0;
    }

    @Override
    public FoodMeal findById(long id) {
        return _context.foodMeals.stream()
                .filter(foodMeal -> foodMeal.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Food> getFoodsInMeal(long mealId) {
        ArrayList<Food> foods = new ArrayList<Food>();
        _context.foodMeals.stream().forEach(foodMeal -> {
            if(foodMeal.getMealId() == mealId)
                foods.add(_context.foods.stream()
                        .filter(food -> food.getId() == foodMeal.getFoodId())
                        .findFirst()
                        .orElse(null));
        });
        return foods;
    }

    @Override
    public ArrayList<Meal> getMealsofOneFood(long foodId) {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        _context.foodMeals.stream().forEach(foodMeal -> {
            if(foodMeal.getFoodId() == foodId)
                meals.add(_context.meals.stream()
                        .filter(meal -> meal.getId() == foodMeal.getMealId())
                        .findFirst()
                        .orElse(null));
        });
        return meals;
    }

    @Override
    public ArrayList<Meal> getMealsofOneLocation(long locationId) {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        _context.foodMeals.stream().forEach(foodMeal -> {
            if(foodMeal.getLocationId() == locationId)
                meals.add(_context.meals.stream()
                        .filter(meal -> meal.getId() == foodMeal.getMealId())
                        .findFirst()
                        .orElse(null));
        });
        return meals;
    }

    @Override
    public ArrayList<Meal> getMealsOfOneFood(long foodId) {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        _context.foodMeals.stream().forEach(foodMeal -> {
            if(foodMeal.getFoodId() == foodId)
                meals.add(_context.meals.stream()
                        .filter(meal -> meal.getId() == foodMeal.getMealId())
                        .findFirst()
                        .orElse(null));
        });
        return meals;
    }

    @Override
    public ArrayList<Location> getLocationsOfOneFood(long foodId) {
        ArrayList<Location> locations = new ArrayList<Location>();
        _context.foodMeals.stream().forEach(foodMeal -> {
            if(foodMeal.getFoodId() == foodId)
                locations.add(_context.locations.stream()
                        .filter(location -> location.getId() == foodMeal.getLocationId())
                        .findFirst()
                        .orElse(null));
        });
        return locations;
    }
}
