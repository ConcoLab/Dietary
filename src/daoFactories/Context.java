package daoFactories;

import models.*;

import java.util.ArrayList;

public class Context {
    public ArrayList<Food> foods;
    public ArrayList<FoodGroup> foodGroups;
    public ArrayList<Group> groups;
    public ArrayList<FoodMeal> foodMeals;
    public ArrayList<Meal> meals;
    public ArrayList<Location> locations;


    public Context() {
        this.foods = new ArrayList<Food>();
        this.groups = new ArrayList<Group>();
        this.foodGroups = new ArrayList<FoodGroup>();
        this.foodMeals = new ArrayList<FoodMeal>();
        this.meals = new ArrayList<Meal>();
        this.locations = new ArrayList<Location>();
    }
}
