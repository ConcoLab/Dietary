package daoFactories;

import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public class Context {
    public ArrayList<Food> foods;
    public ArrayList<FoodGroup> foodGroups;
    public ArrayList<Group> groups;


    public Context() {
        this.foods = new ArrayList<Food>();
        this.groups = new ArrayList<Group>();
        this.foodGroups = new ArrayList<FoodGroup>();
    }
}
