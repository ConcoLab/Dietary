package daoFactories;

import models.*;

import java.util.ArrayList;

public class Context {
    public ArrayList<Food> foods;
    public ArrayList<FoodGroup> foodGroups;
    public ArrayList<Group> groups;
    public ArrayList<Unit> units;
    public ArrayList<FoodMeal> foodMeals;
    public ArrayList<Meal> meals;
    public ArrayList<MealType> mealTypes;
    public ArrayList<Location> locations;




    public Context() {
        foods = new ArrayList<Food>();
        foodGroups = new ArrayList<FoodGroup>();
        groups = new ArrayList<Group>();
        units = new ArrayList<Unit>();
        foodMeals = new ArrayList<FoodMeal>();
        meals = new ArrayList<Meal>();
        mealTypes = new ArrayList<MealType>();
        locations = new ArrayList<Location>();
    }
}
